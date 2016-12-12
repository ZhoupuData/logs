package com.zhoupu.zplogsbatch.commons;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 
 * @author tangdingyi
 *
 */
@Component
public class ServletContextImpl implements ServletContextAware {
	
	@Value("${server.context-path:}")
	private String contextPath;

	@Override
	public void setServletContext(ServletContext servletContext) {

		servletContext.setAttribute("contextPath", contextPath);

	}

}
