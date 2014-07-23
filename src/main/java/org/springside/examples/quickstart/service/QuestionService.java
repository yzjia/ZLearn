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
import org.springside.examples.quickstart.entity.Question;
import org.springside.examples.quickstart.repository.QuestionDao;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
//@Transactional
public class QuestionService {

	private QuestionDao questionDao;

	public Question getQuestion(Long id) {
		return questionDao.findOne(id);
	}

	public void saveQuestion(Question entity) {
		questionDao.save(entity);
	}

	public void deleteQuestion(Long id) {
		questionDao.delete(id);
	}

	public List<Question> getAllQuestion() {
		return (List<Question>) questionDao.findAll();
	}
	
	public List<Question> getQuestionByEnterpriseId(Long enterpriseId) {
		return questionDao.findByEnterpriseId(enterpriseId);
	}

	public Page<Question> getUserQuestion(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Question> spec = buildSpecification(userId, searchParams);

		return questionDao.findAll(spec, pageRequest);
	}

	public Page<Question> getUserQuestion(Long userId, Long subjectId, Map<String, Object> searchParams, int pageNumber, int pageSize,
			String sortType) {
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<Question> spec = buildSpecification(userId, searchParams);

		return questionDao.findAll(spec, pageRequest);
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
	private Specification<Question> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("enterprise.id", new SearchFilter("enterprise.id", Operator.EQ, userId));
		Specification<Question> spec = DynamicSpecifications.bySearchFilter(filters.values(), Question.class);
		return spec;
	}
	
	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<Question> buildSpecification(Long userId, Long subjectId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("enterprise.id", new SearchFilter("enterprise.id", Operator.EQ, userId));
		filters.put("subject.id", new SearchFilter("subject.id", Operator.EQ, subjectId));
		Specification<Question> spec = DynamicSpecifications.bySearchFilter(filters.values(), Question.class);
		return spec;
	}

	@Autowired
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
}
