package com.collaboration.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfiguration
{
	
	public DBConfiguration()
	{
		System.out.println("Inside DBConfiguration");
	}
	@Bean
	public SessionFactory sessionfactory()
	{
		System.out.println("Inside the SessionFactory()");
		
		LocalSessionFactoryBuilder lsf=new LocalSessionFactoryBuilder(getDataSource());
		Properties hibernateproperties=new Properties();
		hibernateproperties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		hibernateproperties.setProperty("hibernate.hbm2ddl.auto","update");
		hibernateproperties.setProperty("hibernate.show_sql","true");
		lsf.addProperties(hibernateproperties);
		lsf.scanPackages("com.collaboration.models");
		return lsf.buildSessionFactory();
	}
	
	@Bean
	public DataSource getDataSource()
	{
		System.out.println("Inside getDataSource()");
		
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		System.out.println("Setting Username and PassWord");
		dataSource.setUsername("SYSTEM");
		dataSource.setPassword("Indu1997");
		System.out.println(dataSource.getUsername());
		System.out.println(dataSource.getPassword());
		return dataSource;
		
	}
	
	@Bean
	public HibernateTransactionManager hibTransManagement()
	{
		System.out.println("TransactionManagerObjectCreated");
		return new HibernateTransactionManager(sessionfactory());
	}
	

}
