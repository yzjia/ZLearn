/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.question;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.Enterprise;
import org.springside.examples.quickstart.entity.Question;
import org.springside.examples.quickstart.entity.Subject;
import org.springside.examples.quickstart.service.ShiroDbRealm.ShiroUser;
import org.springside.examples.quickstart.service.QuestionService;
import org.springside.examples.quickstart.service.SubjectService;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;

/**
 * question管理的Controller, 使用Restful风格的Urls:
 * 
 * List page : GET /question/subject/{subjectId}
 * Create page : GET /question/create
 * Create action : POST /question/create
 * Update page : GET /question/update/{id}
 * Update action : POST /question/update
 * Delete action : GET /question/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/question")
public class QuestionController {

	private static final String PAGE_SIZE = "20";

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();

		Page<Question> questions = questionService.getUserQuestion(userId, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("questions", questions);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "question/questionList";
	}

	@RequestMapping(value = "/subject/{subjectId}", method = RequestMethod.GET)
	public String subjectList(@PathVariable("subjectId") Long subjectId, @RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();

		Page<Question> questions = questionService.getUserQuestion(userId, subjectId, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("questions", questions);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "question/questionList";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("action", "create");
		return "question/questionForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Question newQuestion, RedirectAttributes redirectAttributes) {
		Enterprise enterprise = new Enterprise(getCurrentUserId());
		newQuestion.setEnterprise(enterprise);

		questionService.saveQuestion(newQuestion);
		redirectAttributes.addFlashAttribute("message", "创建试题成功");
		return "redirect:/question/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("question", questionService.getQuestion(id));
		model.addAttribute("action", "update");
		return "question/questionForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("question") Question question, RedirectAttributes redirectAttributes) {
		questionService.saveQuestion(question);
		redirectAttributes.addFlashAttribute("message", "更新试题成功");
		return "redirect:/question/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		questionService.deleteQuestion(id);
		redirectAttributes.addFlashAttribute("message", "删除试题成功");
		return "redirect:/question/";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出question对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getQuestion(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("question", questionService.getQuestion(id));
		}
		List<Subject> subjectList = subjectService.getSubjectByEnterpriseId(getCurrentUserId());
		model.addAttribute("subjectList", subjectList);
	}

	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}
