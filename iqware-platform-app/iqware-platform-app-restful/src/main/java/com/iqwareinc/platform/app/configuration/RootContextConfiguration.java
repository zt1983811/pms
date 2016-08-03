package com.iqwareinc.platform.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.iqwareinc.platform.core.configuration.PlatformCoreConfigruation;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.iqwareinc.platform.core.repository", "com.iqwareinc.platform.core.service" })
@PropertySource({ "/WEB-INF/config/persistence.properties" })
@Import(PlatformCoreConfigruation.class)
public class RootContextConfiguration {

   @Bean
   public LocalValidatorFactoryBean localValidatorFactoryBean() {
      LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
      return validator;
   }

   @Bean
   public MethodValidationPostProcessor methodValidationPostProcessor() {
      MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
      processor.setValidator(this.localValidatorFactoryBean());
      return processor;
   }

   @Bean
   public ObjectMapper objectMapper() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.findAndRegisterModules();
      mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
      mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
      return mapper;
   }

   @Bean
   public Jaxb2Marshaller jaxb2Marshaller() {
      Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
      marshaller.setPackagesToScan(new String[] { "com.wrox.site" });
      return marshaller;
   }
}
