package com.iqwareinc.platform.app.restapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.iqwareinc.platform.common.annotation.WebController;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.iqwareinc.platform.app.restapi", useDefaultFilters = false, includeFilters = @ComponentScan.Filter(WebController.class))
public class WebServletContextConfiguration extends WebMvcConfigurerAdapter {

   @Bean
   public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setPrefix("/WEB-INF/views/");
      viewResolver.setSuffix(".jsp");
      return viewResolver;
   }
}