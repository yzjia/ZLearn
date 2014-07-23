/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.Enterprise;
import org.springside.examples.quickstart.service.AccountService;

/**
 * 用户注册的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String registerForm() {
		return "account/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid Enterprise enterprise, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!checkLoginNameExists(enterprise.getLoginName())) {
			enterprise.setLoginName(null);
			request.setAttribute("enterprise", enterprise);
			return "account/register";
		}
		if (!checkEmailExists(enterprise.getEmail())) {
			enterprise.setEmail(null);
			request.setAttribute("enterprise", enterprise);
			return "account/register";
		}
		accountService.registerEnterprise(enterprise);
		redirectAttributes.addFlashAttribute("username", enterprise.getLoginName());
		return "redirect:/login";
	}

	/**
	 * Ajax请求校验loginName是否唯一。
	 */
	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (checkLoginNameExists(loginName)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	/**
	 * Ajax请求校验email是否唯一。
	 */
	@RequestMapping(value = "checkEmail")
	@ResponseBody
	public String checkEmail(@RequestParam("email") String email) {
		if (checkEmailExists(email)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	private boolean checkLoginNameExists(String loginName) {
		if (accountService.findUserByLoginName(loginName) == null && accountService.findEnterpriseByLoginName(loginName) == null) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkEmailExists(String email) {
		if (accountService.findUserByEmail(email) == null && accountService.findEnterpriseByEmail(email) == null) {
			return true;
		} else {
			return false;
		}
	}
}
