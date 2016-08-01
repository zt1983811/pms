package com.iqwareinc.platform.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iqwareinc.platform.core.configuration.PlatformCoreConfigruation;
import com.iqwareinc.platform.core.model.entity.Property;
import com.iqwareinc.platform.core.service.PropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PlatformCoreConfigruation.class })
@Commit
public class IntegrationTest {

   @Autowired
   PropertyService propertyService;

   @Test
   public void testSave() {
      Property p = new Property("Test Name");
      propertyService.saveProperty(p);
   }
}
