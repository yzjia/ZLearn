/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ss_userexam")
public class UserExam extends IdEntity {
	private User user;
	private Exam exam;
	private Date begintime;
	private Date endtime;
	//评分
	private Integer grade;
	private Float score;
	//名次
	private Integer rank;

	public UserExam() {
	}

	public UserExam(Long id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	// 不持久化到数据库，也不显示在Restful接口的属性.
	@Transient
	@JsonIgnore
	public String getGradeStr() {
		switch (this.grade) {
		case 1:
			return "自动评分";
		case 2:
			return "未评分";
		case 9:
			return "已评分";
		default:
			return "未评分";
		}
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

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
}