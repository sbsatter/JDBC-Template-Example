package com.datasoft.config;


import com.datasoft.dao.ContactDAO;
import com.datasoft.dao.ContactDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.datasoft")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.driver");
		dataSource.setUrl("jdbc:mysql//localhost:3306/contactsdb");
		dataSource.setUsername("root");
		dataSource.setPassword("Maya1111!!");
		return dataSource;
	}
	
	@Bean
	public ContactDAO getContactDAO() {
		return new ContactDAOImpl(getDataSource());
	}
}