package by.cources.spring.task1.javaconfig;

import by.cources.spring.task1.javaconfig.repository.BookRepository;
import java.util.List;

public class BookService {

  private BookRepository repository;

  public BookService(BookRepository repository) {
    this.repository = repository;
  }

  public List<Book> findAll() {
    return repository.findAll();
  }
}
