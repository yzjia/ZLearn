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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ss_userexamanswer")
public class UserExamAnswer extends IdEntity {
	private User user;
	private Exam exam;
	private Question question;
	private String answer;
	//评分
	private Integer correctFlag;
	private Float score;

	public UserExamAnswer() {
	}

	public UserExamAnswer(Long id) {
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}
	
	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "question_id")
	public Question getQuestion() {
		return this.question;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(Integer correctFlag) {
		this.correctFlag = correctFlag;
	}
	
	@Transient
	@JsonIgnore
	public String getCorrectFlagStr() {
		switch(this.correctFlag) {
		case 0:
			return "错误";
		case 1:
			return "正确";
		default:
			return "错误";
		}
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}