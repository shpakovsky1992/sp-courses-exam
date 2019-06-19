package by.cources.spring.task1.annotations;

import by.cources.spring.task1.annotations.repository.BookRepository;
import by.cources.spring.task1.annotations.repository.InMemoryBookRepository;
import by.cources.spring.task1.annotations.repository.MysqlBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("by.cources.spring.task1.annotations")
public class BookConfig {
}

