package com.iqwareinc.platform.app.restful.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/properties")
public class PropertyController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "property/list";
	}
}
