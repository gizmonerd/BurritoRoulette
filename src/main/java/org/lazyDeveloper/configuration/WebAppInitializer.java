package org.lazyDeveloper.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

	private static Logger logger = LogManager.getLogger(WebAppInitializer.class);
	
	  @Override
	    public void onStartup(ServletContext servletContext) throws ServletException {
	        WebApplicationContext context = getContext();
	        servletContext.addListener(new ContextLoaderListener(context));
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/*");
	        logger.info("Burrito has been wrapped");
	    }

	    private AnnotationConfigWebApplicationContext getContext() {
	        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	        context.setConfigLocation("org.lazyDeveloper.config");
	        return context;
	    }

}
