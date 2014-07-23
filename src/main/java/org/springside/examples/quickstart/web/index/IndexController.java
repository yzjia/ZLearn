/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.index;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.examples.quickstart.service.ShiroDbRealm.ShiroUser;

/**
 * index:
 * 
 * @author yzjia
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,
			ServletRequest request) {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute("user", user);
		return "main/main";
	}

}
