/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.examples.quickstart.entity.Enterprise;
import org.springside.examples.quickstart.entity.User;
import org.springside.examples.quickstart.repository.EnterpriseDao;
import org.springside.examples.quickstart.repository.SubjectDao;
import org.springside.examples.quickstart.repository.UserDao;
import org.springside.examples.quickstart.service.ShiroDbRealm.ShiroUser;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

/**
 * 用户管理类.
 * 
 * @author calvin
 */
// Spring Service Bean的标识.
@Component
public class AccountService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(AccountService.class);

	private EnterpriseDao enterpriseDao;
	private UserDao userDao;
	private SubjectDao subjectDao;
	private Clock clock = Clock.DEFAULT;

	public List<Enterprise> getAllEnterprise() {
		return (List<Enterprise>) enterpriseDao.findAll();
	}
	
	public List<User> getAllUser() {
		return (List<User>) userDao.findAll();
	}
	
	public List<User> getUserByEnterprise(Long enterpriseId) {
		return (List<User>) userDao.findAll();
	}

	public Enterprise getEnterprise(Long id) {
		return enterpriseDao.findOne(id);
	}
	
	public User getUser(Long id) {
		return userDao.findOne(id);
	}

	public Enterprise findEnterpriseByLoginName(String loginName) {
		return enterpriseDao.findByLoginName(loginName);
	}

	public User findUserByLoginName(String loginName) {
		return userDao.findByLoginName(loginName);
	}

	public Enterprise findEnterpriseByEmail(String email) {
		return enterpriseDao.findByEmail(email);
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public void registerEnterprise(Enterprise enterprise) {
		entryptPassword(enterprise);
		enterprise.setRoles("enterprice");
		enterprise.setRegisterDate(clock.getCurrentDate());

		enterpriseDao.save(enterprise);
	}

	public void createUser(User user) {
		entryptPassword(user);
		user.setRoles("user");
		user.setCreateDate(clock.getCurrentDate());
		userDao.save(user);
	}

	public void updateUser(User user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		userDao.save(user);
	}

	public void updateEnterprise(Enterprise enterprise) {
		if (StringUtils.isNotBlank(enterprise.getPlainPassword())) {
			entryptPassword(enterprise);
		}
		enterpriseDao.save(enterprise);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		this.userDao.delete(id);
	}
	
	public void deleteUser(Long id, Long enterpriseId) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		//只有用户的企业ID为当前企业时才删除此用户
		User user =  userDao.findOne(id);
		if (user.getEnterprise().getId().equals(enterpriseId)) {
			this.userDao.delete(id);
		}
	}

	public void deleteEnterprise(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		enterpriseDao.delete(id);
		subjectDao.deleteByEnterpriseId(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(Enterprise enterprise) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		enterprise.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(enterprise.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		enterprise.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Autowired
	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	@Autowired
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
