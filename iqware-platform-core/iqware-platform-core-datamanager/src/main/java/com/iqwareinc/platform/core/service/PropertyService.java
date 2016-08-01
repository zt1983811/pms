package com.iqwareinc.platform.core.service;

import com.iqwareinc.platform.core.model.entity.Property;

public interface PropertyService {
   
   public Property saveProperty(Property property);

   public Property findPropertyById(Long id);
}