package by.cources.spring.task1.annotations;

import by.cources.spring.task1.annotations.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BookService {

  private BookRepository repository;

  @Autowired
  public BookService(@Qualifier("mysqlBookRepository") BookRepository repository) {
    this.repository = repository;
  }

  public List<Book> findAll() {
    return repository.findAll();
  }
}
