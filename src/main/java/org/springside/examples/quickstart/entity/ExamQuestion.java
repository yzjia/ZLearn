/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ss_examquestion")
public class ExamQuestion extends IdEntity {
	private Exam exam;
	private Question question;

	public ExamQuestion() {
	}

	public ExamQuestion(Long id) {
		this.id = id;
	}

	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "exam_id")
	public Exam getExam() {
		return this.exam;
	}
	
	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "question_id")
	public Question getQuestion() {
		return this.question;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}