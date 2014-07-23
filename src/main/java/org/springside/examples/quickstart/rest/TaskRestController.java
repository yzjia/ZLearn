/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.rest;

import java.net.URI;
import java.util.List;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.examples.quickstart.entity.Subject;
import org.springside.examples.quickstart.service.SubjectService;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.web.MediaTypes;

/**
 * subject的Restful API的Controller.
 * 
 * @author calvin
 */
@RestController
@RequestMapping(value = "/api/v1/subject")
public class TaskRestController {

	private static Logger logger = LoggerFactory.getLogger(TaskRestController.class);

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public List<Subject> list() {
		return subjectService.getAllSubject();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Subject get(@PathVariable("id") Long id) {
		Subject subject = subjectService.getSubject(id);
		if (subject == null) {
			String message = "任务不存在(id:" + id + ")";
			logger.warn(message);
			throw new RestException(HttpStatus.NOT_FOUND, message);
		}
		return subject;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaTypes.JSON)
	public ResponseEntity<?> create(@RequestBody Subject subject, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, subject);

		// 保存任务
		subjectService.saveSubject(subject);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		Long id = subject.getId();
		URI uri = uriBuilder.path("/api/v1/subject/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaTypes.JSON)
	// 按Restful风格约定，返回204状态码, 无内容. 也可以返回200状态码.
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody Subject subject) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, subject);

		// 保存任务
		subjectService.saveSubject(subject);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		subjectService.deleteSubject(id);
	}
}
