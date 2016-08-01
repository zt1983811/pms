package com.iqwareinc.platform.app.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqwareinc.platform.common.annotation.RestEndpoint;
import com.iqwareinc.platform.core.model.entity.Property;
import com.iqwareinc.platform.core.service.PropertyService;

@RestEndpoint
@RequestMapping(value = "/properties")
public class PropertyController {

   @Autowired
   private PropertyService propertyService;

   @RequestMapping(method = RequestMethod.GET)
   public String list(Model model) {
      Property property = new Property("Test from controller");
      propertyService.saveProperty(property);
      return "property/list";
   }
}
