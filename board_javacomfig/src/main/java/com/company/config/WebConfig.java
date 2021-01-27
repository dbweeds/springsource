package com.company.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml 대체하는 클래스

@EnableWebMvc
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*
	 * <context-param> <param-name>contextConfigLocation</param-name>
	 * <param-value>/WEB-INF/spring/root-context.xml /WEB-INF/spring/task-config.xml
	 * </param-value> </context-param>
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class };
	}

	/*
	 * <servlet-name>appServlet</servlet-name>
	 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-
	 * class> <init-param> <param-name>contextConfigLocation</param-name>
	 * <param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
	 * </init-param>
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletConfig.class };
	}

	/*
	 * <servlet-mapping> <servlet-name>appServlet</servlet-name>
	 * <url-pattern>/</url-pattern> </servlet-mapping>
	 */
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	/*
	 * <init-param> <param-name>throwExceptionIfNoHandlerFound</param-name>
	 * <param-value>true</param-value> </init-param>
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	/*
	 * <filter> <filter-name>encodingFilter</filter-name> <filter-class>
	 * org.springframework.web.filter.CharacterEncodingFilter </filter-class>
	 * <init-param> <param-name>encoding</param-name>
	 * <param-value>UTF-8</param-value> </init-param> </filter> <filter-mapping>
	 * <filter-name>encodingFilter</filter-name> <url-pattern>/*</url-pattern>
	 * </filter-mapping>
	 */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("utf-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] { encodingFilter };
	}
}
