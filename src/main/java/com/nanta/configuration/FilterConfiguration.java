package com.nanta.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.nanta.filter.CredentialFilter;
import com.nanta.filter.RootFilter;

@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource =
        new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/api/**", corsConfiguration);
    CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(corsFilter);
    filterRegistrationBean.setOrder(0);
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean credentialFilter() {
    CredentialFilter credentialFilter = new CredentialFilter();
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(credentialFilter);
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }
  
  @Bean
  public FilterRegistrationBean rootFilter() {
    RootFilter rootFilter = new RootFilter();
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(rootFilter);
    filterRegistrationBean.setOrder(2);
    return filterRegistrationBean;
  }

}
