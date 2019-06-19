package by.cources.spring.exam;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("by.cources.spring.exam")
@Import({
    BookJpaConfig.class,
    BookWebConfig.class,
    BookSecurityConfig.class
})
public class BookConfig {

}
