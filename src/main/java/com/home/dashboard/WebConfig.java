package com.home.dashboard;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration 
public class WebConfig {
	
	
	@Bean  
    public FilterRegistrationBean filterRegistrationBean(){  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new CorsFilter());  
        filterRegistrationBean.setEnabled(true);  
        filterRegistrationBean.addUrlPatterns("/*");  
        return filterRegistrationBean;  
    }   
}
