package com.iqwareinc.platform.app.web.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqwareinc.platform.common.annotation.WebController;
import com.iqwareinc.platform.core.service.PropertyService;

@WebController
@RequestMapping(value = "/properties")
public class PropertyController {
   
   @Inject
   private PropertyService propertyService;

   @RequestMapping(method = RequestMethod.GET)
   public String list(Model model) {
      return "property/list";
   }
}
