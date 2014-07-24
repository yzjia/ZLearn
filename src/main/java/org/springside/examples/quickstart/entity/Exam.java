/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableList;

@Entity
@Table(name = "ss_exam")
public class Exam extends IdEntity {
	private Integer type;
	private String name;
	private Date createtime;
	private Date begintime;
	private Date endtime;
	private Enterprise enterprise;
	private String subject;
	private Float totalscore;
	
	public Exam() {
	}

	public Exam(Long id) {
		this.id = id;
	}


	@Transient
	@JsonIgnore
	public String getTypeStr() {
		switch (type) {
			case 1:
				return "单次考试";
			case 2:
				return "可重复考试";
			default :
				return "单次考试";
		}
	}
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Transient
	@JsonIgnore
	public List<String> getSubjectList() {
		// 角色列表在数据库中实际以逗号分隔字符串存储，因此返回不能修改的List.
		return ImmutableList.copyOf(StringUtils.split(subject, ","));
	}
	
	@Transient
	@JsonIgnore
	public void setSubjectList(String[] subjectArray) {
		if (subjectArray == null || subjectArray.length <= 0) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (String s : subjectArray) {
			sb.append(s).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		this.subject = sb.toString();
	}

	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public Float getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(Float totalscore) {
		this.totalscore = totalscore;
	}
}