package com.iqwareinc.platform.core.service.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iqwareinc.platform.core.model.entity.Property;
import com.iqwareinc.platform.core.repository.PropertyRepository;
import com.iqwareinc.platform.core.service.PropertyService;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {

   @Autowired
   private PropertyRepository propertyRepository;

   @Override
   public Property saveProperty(Property property) {
      return propertyRepository.save(property);
   }

   @Override
   @Transactional(readOnly = true)
   public Property findPropertyById(Long id) {
      return propertyRepository.find(id);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Property> findAll() {
      return propertyRepository.findAll();
   }

}
