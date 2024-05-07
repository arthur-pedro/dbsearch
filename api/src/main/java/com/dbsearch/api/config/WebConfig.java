package com.dbsearch.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean<ShallowEtagHeaderFilter> etagFilter() {
      FilterRegistrationBean<ShallowEtagHeaderFilter> filterRegistrationBean =
              new FilterRegistrationBean<>(new ShallowEtagHeaderFilter());
      filterRegistrationBean.addUrlPatterns("/*");
      return filterRegistrationBean;
  }

  @Override
  public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedMethods("GET", "POST");
  }
}