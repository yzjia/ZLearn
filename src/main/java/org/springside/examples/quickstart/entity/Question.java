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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ss_question")
public class Question extends IdEntity {
	private Integer qtype;
	private String question;
	private String options;
	private Integer optionsnum;
	private String answer;
	private String describe;
	private Date createtime;
	private Integer level;
	private Enterprise enterprise;
	private Subject subject;

	public Question() {
	}

	public Question(Long id) {
		this.id = id;
	}

	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "subject_id")
	public Subject getSubject() {
		return subject;
	}

	@NotBlank
	public Integer getQtype() {
		return qtype;
	}

	public void setQtype(Integer qtype) {
		this.qtype = qtype;
	}

	@Transient
	@JsonIgnore
	public String getQtypeStr() {
		switch (qtype) {
			case 1:
				return "单项选择";
			case 2:
				return "多项选择";
			case 3:
				return "判断";
			case 4:
				return "填空";
			case 5:
				return "问答";
			default :
				return "";
		}
	}
	
	@Transient
	@JsonIgnore
	public String getLevelStr() {
		switch (level) {
			case 1:
				return "简单";
			case 2:
				return "普通";
			case 3:
				return "较难";
			default :
				return "普通";
		}
	}

	@NotBlank
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Integer getOptionsnum() {
		return optionsnum;
	}

	public void setOptionsnum(Integer optionsnum) {
		this.optionsnum = optionsnum;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Transient
	@JsonIgnore
	public void setSubjectId(Long subjectId) {
		Subject newSubject = new Subject();
		newSubject.setId(subjectId);
		this.subject = newSubject;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}