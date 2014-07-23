/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.springside.examples.quickstart.web.account;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.examples.quickstart.entity.Enterprise;
import org.springside.examples.quickstart.service.AccountService;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/admin/enterprise")
public class EnterpriseAdminController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<Enterprise> enterprises = accountService.getAllEnterprise();
		model.addAttribute("enterprises", enterprises);

		return "account/adminEnterpriseList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("enterprise", accountService.getEnterprise(id));
		return "account/adminEnterpriseForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("enterprise") Enterprise enterprise, RedirectAttributes redirectAttributes) {
		accountService.updateEnterprise(enterprise);
		redirectAttributes.addFlashAttribute("message", "更新用户" + enterprise.getLoginName() + "成功");
		return "redirect:/admin/enterprise";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		Enterprise enterprise = accountService.getEnterprise(id);
		accountService.deleteEnterprise(id);
		redirectAttributes.addFlashAttribute("message", "删除用户" + enterprise.getLoginName() + "成功");
		return "redirect:/admin/enterprise";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出Enterprise对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getEnterprise(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("enterprise", accountService.getEnterprise(id));
		}
	}
}
