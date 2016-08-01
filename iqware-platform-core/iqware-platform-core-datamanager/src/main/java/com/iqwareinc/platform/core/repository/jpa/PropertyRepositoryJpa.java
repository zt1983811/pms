package com.iqwareinc.platform.core.repository.jpa;

import org.springframework.stereotype.Repository;

import com.iqwareinc.platform.core.model.entity.Property;
import com.iqwareinc.platform.core.repository.PropertyRepository;

@Repository
public class PropertyRepositoryJpa extends GenericRepositoryJpa<Long, Property> implements PropertyRepository {

}
