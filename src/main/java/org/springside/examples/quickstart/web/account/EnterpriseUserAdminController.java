/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.Enterprise;
import org.springside.examples.quickstart.entity.Subject;
import org.springside.examples.quickstart.entity.User;
import org.springside.examples.quickstart.service.AccountService;
import org.springside.examples.quickstart.service.ShiroDbRealm.ShiroUser;
import org.springside.examples.quickstart.service.SubjectService;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/enterprise/user")
public class EnterpriseUserAdminController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		Long userId = getCurrentUserId();
		List<User> users = accountService.getUserByEnterprise(userId);
		model.addAttribute("users", users);

		return "enterprise/adminUserList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("action", "create");
		return "enterprise/adminUserForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid User newUser, RedirectAttributes redirectAttributes) {
		Enterprise enterprise = new Enterprise(getCurrentUserId());
		newUser.setEnterprise(enterprise);
		accountService.registerEnterprise(enterprise);
		redirectAttributes.addFlashAttribute("message", "创建用户成功");
		return "redirect:/enterprise/user";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		model.addAttribute("action", "update");
		return "enterprise/adminUserForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		accountService.updateUser(user);
		redirectAttributes.addFlashAttribute("message", "更新用户" + user.getLoginName() + "成功");
		return "redirect:/enterprise/user";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		User user = accountService.getUser(id);
		accountService.deleteUser(id, getCurrentUserId());
		redirectAttributes.addFlashAttribute("message", "删除用户" + user.getLoginName() + "成功");
		return "redirect:/enterprise/user";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("user", accountService.getUser(id));
		}
		List<Subject> subjectList = subjectService.getSubjectByEnterpriseId(getCurrentUserId());
		Map subjectMap = new HashMap();
		for (Subject s : subjectList) {
			subjectMap.put(s.getId(), s.getName());
		}
		model.addAttribute("subjectMap", subjectMap);
	}

	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
