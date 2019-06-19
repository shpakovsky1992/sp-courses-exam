package by.cources.spring.task5.spring;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zaxxer.hikari.HikariDataSource;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@PropertySource("classpath:task5/database.properties")
@ComponentScan("by.cources.spring.task5")
@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc
public class BookConfig implements WebMvcConfigurer {

  @Autowired
  Environment environment;

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
    ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();

    objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    converters.add(jacksonMessageConverter);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
    interceptor.setEntityManagerFactory(entityManagerFactory().getObject());
    registry.addWebRequestInterceptor(interceptor);
  }

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
    factory.setJpaProperties(getProperties());
    return factory;
  }

  private Properties getProperties() {
    Properties jpaProperties = new Properties();
    jpaProperties.put("javax.persistence.schema-generation.database.action", "create");
    jpaProperties.put("javax.persistence.schema-generation.create-source", "script");
    jpaProperties.put("javax.persistence.schema-generation.create-script-source", "/task5/create.sql");
//    jpaProperties.put("javax.persistence.schema-generation.drop-source", "script");
//    jpaProperties.put("javax.persistence.schema-generation.drop-script-source", "drop.sql");
    jpaProperties.put("javax.persistence.schema-generation.drop-source", "script");
    jpaProperties.put("javax.persistence.sql-load-script-source", "/task5/data.sql");
    return jpaProperties;
  }

  @Bean
  PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return txManager;
  }
}
