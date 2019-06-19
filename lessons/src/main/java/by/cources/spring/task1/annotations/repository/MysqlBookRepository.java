package by.cources.spring.task1.annotations.repository;

import by.cources.spring.task1.annotations.Book;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MysqlBookRepository implements BookRepository {

  private List<Book> cache = Arrays.asList(
      new Book("mysq-book1"),
      new Book("mysq-book2")
  );

  @Override
  public List<Book> findAll() {
    return cache;
  }
}
