package com.iqwareinc.platform.app.configuration;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqwareinc.platform.common.annotation.RestEndpoint;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.iqwareinc.platform.app.restapi", useDefaultFilters = false, includeFilters = @ComponentScan.Filter(RestEndpoint.class))
public class RestServletContextConfiguration extends WebMvcConfigurerAdapter {
   @Inject
   private ObjectMapper           objectMapper;

   @Inject
   private Marshaller             marshaller;

   @Inject
   private Unmarshaller           unmarshaller;

   @Inject
   private SpringValidatorAdapter validator;

   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      converters.add(new SourceHttpMessageConverter<>());

      MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
      xmlConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "xml"), new MediaType("text", "xml")));
      xmlConverter.setMarshaller(this.marshaller);
      xmlConverter.setUnmarshaller(this.unmarshaller);
      converters.add(xmlConverter);

      MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
      jsonConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json"), new MediaType("text", "json")));
      jsonConverter.setObjectMapper(this.objectMapper);
      converters.add(jsonConverter);
   }

   @Override
   public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
      configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false).defaultContentType(MediaType.APPLICATION_JSON);
   }

   @Override
   public Validator getValidator() {
      return this.validator;
   }

   @Bean
   public LocaleResolver localeResolver() {
      return new AcceptHeaderLocaleResolver();
   }
}