package com.krustyburger.order.backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(JPAConfiguration.PACK_SCAN_REPOSITORIES)
public class JPAConfiguration {

	//TODO: Put properties on environment variables
	public static final String PACK_SCAN_REPOSITORIES = "com.krustyburger.order.backend.repository";
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/order";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "root";
	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.krustyburger.order.backend.model";
	private static final String HIBERNATE_HBM2DDL_AUTO_PROPERTY = "hibernate.hbm2ddl.auto";
	private static final String HIBERNATE_HBM2DDL_AUTO_VALUE = "update";
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaDialect jpaDialect = new HibernateJpaDialect();
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory().getObject());
		txManager.setJpaDialect(jpaDialect);
		return txManager;    	
	}	

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(getJpaProperties());
        return entityManagerFactoryBean;
    }
    
    @Bean
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(DB_DRIVER);
    	dataSource.setUrl(DB_URL);
    	dataSource.setUsername(DB_USER_NAME);
    	dataSource.setPassword(DB_PASSWORD);
    	return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabase(Database.MYSQL);
        return vendorAdapter;
    }
    
    private Properties getJpaProperties() {
        Properties properties = new Properties();
        properties.setProperty(HIBERNATE_HBM2DDL_AUTO_PROPERTY, HIBERNATE_HBM2DDL_AUTO_VALUE);
    	return properties;
    }    
	
}
