package com.iqwareinc.platform.core.configuration;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PlatformCoreConfigruation {

   public static final String PERSISTENCE_UNIT_NAME = "pms";

   @Inject
   Environment                environment;

   @Bean
   public DataSource dataSource() {

      BasicDataSource basicDataSource = new BasicDataSource();
      basicDataSource.setDriverClassName(environment.getProperty("db.driver"));
      basicDataSource.setUrl(environment.getProperty("db.url"));
      basicDataSource.setUsername(environment.getProperty("db.username"));
      basicDataSource.setPassword(environment.getProperty("db.password"));
      basicDataSource.setInitialSize(Integer.valueOf(environment.getProperty("db.conn.size")));
      return basicDataSource;

   }

   @Bean
   public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
      factory.setDataSource(dataSource());
      factory.setJpaVendorAdapter(jpaVendorAdapter());
      factory.setJpaProperties(jpaProperties());
      factory.setPackagesToScan("com.iqwareinc.platform.core.model.entity");
      return factory;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());
      return txManager;
   }

   private Properties jpaProperties() {
      Properties jpaProperties = new Properties();
      // jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
      // jpaProperties.put("hibernate.hbm2ddl.import_files", "init.sql");
      return jpaProperties;
   }

   private JpaVendorAdapter jpaVendorAdapter() {
      HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
      hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
      hibernateJpaVendorAdapter.setGenerateDdl(true);
      return hibernateJpaVendorAdapter;
   }
}
