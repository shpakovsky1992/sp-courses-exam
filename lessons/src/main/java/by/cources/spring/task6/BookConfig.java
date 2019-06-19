package by.cources.spring.task6;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:task6/database.properties")
@ComponentScan("by.cources.spring.task6")
@Import({
    BookJpaConfig.class,
    BookWebConfig.class,
    BookSecurityConfig.class
})
public class BookConfig {

}
