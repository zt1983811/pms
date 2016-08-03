package com.iqwareinc.platform.app.restapi.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iqwareinc.platform.app.restapi.form.PropertyForm;
import com.iqwareinc.platform.common.annotation.RestEndpoint;
import com.iqwareinc.platform.common.exception.ResourceNotFoundException;
import com.iqwareinc.platform.core.model.entity.Property;
import com.iqwareinc.platform.core.service.PropertyService;

@RestEndpoint
@RequestMapping(value = "/properties")
public class PropertyRestController {

   @Inject
   private PropertyService propertyService;

   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   @ResponseStatus(HttpStatus.OK)
   public List<Property> read() {
      return propertyService.findAll();
   }

   @RequestMapping(method = RequestMethod.POST)
   public ResponseEntity<Property> create(@RequestBody PropertyForm form) {
      Property property = new Property();
      property.setName(form.getName());
      property.setType(form.getType());
      property = this.propertyService.saveProperty(property);

      String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/property/{id}").buildAndExpand(property.getId()).toString();
      HttpHeaders headers = new HttpHeaders();
      headers.add("Location", uri);
      return new ResponseEntity<>(property, headers, HttpStatus.CREATED);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   @ResponseBody
   @ResponseStatus(HttpStatus.OK)
   public Property read(@PathVariable("id") long id) {
      Property property = this.propertyService.findPropertyById(id);
      if (property == null)
         throw new ResourceNotFoundException();
      return property;
   }
}