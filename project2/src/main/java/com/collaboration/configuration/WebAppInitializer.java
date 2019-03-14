package com.collaboration.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	public WebAppInitializer()
	{
		System.out.println("WebAppInitializer is loaded and instantiated...");
	}
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("In getRootConfigClassesMethod");
		
		return new Class[] {WebAppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("In getSrvletConfigClasses Method");
		return null;
	}

	@Override
	protected String[] getServletMappings() {
	System.out.println("In getServletMapping method");
	
		return new String[] {"/"};//it returns the request to dispatcherservlet
	}

}
