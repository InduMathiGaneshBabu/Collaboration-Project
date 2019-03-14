package com.collaboration.configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.collaboration")

public class WebAppConfig extends WebMvcConfigurerAdapter
{
     public WebAppConfig()
     {
    	 System.out.println("WebAppConfig class is loaded and instantiated..");
     }
	
}
	
	

