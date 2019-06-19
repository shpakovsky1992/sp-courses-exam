package by.cources.spring.task2.spring;

import by.cources.spring.task2.spring.repository.BookRepository;
import by.cources.spring.task2.spring.repository.JdbcTemplateBookRepository;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:task2/database.properties")
public class BookConfig {

  @Autowired
  Environment environment;

  @Bean
  BookRepository jdbcRepository() {
    return new JdbcTemplateBookRepository(dataSource());
  }

  @Bean
  DataSource dataSource() {
    DriverManagerDataSource source = new DriverManagerDataSource();
    source.setUrl(environment.getProperty("url"));
    source.setUsername(environment.getProperty("dbuser"));
    source.setPassword(environment.getProperty("dbpassword"));
    source.setDriverClassName(environment.getProperty("driver"));
    return source;
  }
}
