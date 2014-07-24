/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springside.examples.quickstart.entity.Question;

public interface QuestionDao extends PagingAndSortingRepository<Question, Long>, JpaSpecificationExecutor<Question> {
	
	List<Question> findByEnterpriseId(Long id);
	
	@Query("delete from Question question where question.enterprise.id=?1 and subjectId=?2")
	List<Question> findByEnterpriseIdSubjectId(Long enterpriseId, Long subjectId);

	Page<Question> findByEnterpriseId(Long id, Pageable pageRequest);

	@Modifying
	@Query("delete from Question question where question.enterprise.id=?1")
	void deleteByEnterpriseId(Long id);
	
	@Modifying
	@Query("delete from Question question where question.subject.id=?1")
	void deleteBySubjectId(Long id);
}
