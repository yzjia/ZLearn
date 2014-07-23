/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springside.examples.quickstart.entity.Subject;
import org.springside.examples.quickstart.repository.SubjectDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
//@Transactional
public class SubjectService {

	private SubjectDao subjectDao;

	public Subject getSubject(Long id) {
		return subjectDao.findOne(id);
	}

	public void saveSubject(Subject entity) {
		subjectDao.save(entity);
	}

	public void deleteSubject(Long id) {
		subjectDao.delete(id);
	}

	public List<Subject> getAllSubject() {
		return (List<Subject>) subjectDao.findAll();
	}

	public Page<Subject> getUserSubject(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Subject> spec = buildSpecification(userId, searchParams);

		return subjectDao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Subject> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("enterprise.id", new SearchFilter("enterprise.id", Operator.EQ, userId));
		Specification<Subject> spec = DynamicSpecifications.bySearchFilter(filters.values(), Subject.class);
		return spec;
	}

	@Autowired
	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}
}
