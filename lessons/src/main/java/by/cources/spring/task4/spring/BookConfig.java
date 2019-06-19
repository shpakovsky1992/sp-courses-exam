package by.cources.spring.task4.spring;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:task4/database.properties")
@ComponentScan("by.cources.spring.task4")
@EnableJpaRepositories
@EnableTransactionManagement
public class BookConfig {

  @Autowired
  Environment environment;

  @Bean
  DataSource dataSource() {
    HikariDataSource source = new HikariDataSource();
    source.setJdbcUrl(environment.getProperty("url"));
    source.setUsername(environment.getProperty("dbuser"));
    source.setPassword(environment.getProperty("dbpassword"));
    source.setDriverClassName(environment.getProperty("driver"));
    source.setMaximumPoolSize(5);
    source.setMinimumIdle(2);
    source.setIdleTimeout(20000);
    source.setMaxLifetime(30000);
    source.setConnectionTimeout(30000);
    source.addDataSourceProperty("cachePrepStmts", true);
    source.addDataSourceProperty("prepStmtCacheSize", 250);
    source.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    return source;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.H2);
    vendorAdapter.setShowSql(true);
//    vendorAdapter.setGenerateDdl(true);


    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan(getClass().getPackage().getName());
    factory.setDataSource(dataSource());

    Properties jpaProperties = new Properties();
    jpaProperties.put("javax.persistence.schema-generation.database.action", "create");
    jpaProperties.put("javax.persistence.schema-generation.create-source", "script");
    jpaProperties.put("javax.persistence.schema-generation.create-script-source", "/task4/create.sql");
//    jpaProperties.put("javax.persistence.schema-generation.drop-source", "script");
//    jpaProperties.put("javax.persistence.schema-generation.drop-script-source", "drop.sql");
    jpaProperties.put("javax.persistence.schema-generation.drop-source", "script");
    jpaProperties.put("javax.persistence.sql-load-script-source", "/task4/data.sql");
    factory.setJpaProperties(jpaProperties);
    return factory;
  }

  @Bean
  PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return txManager;
  }
}
