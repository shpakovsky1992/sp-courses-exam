package by.cources.spring.task1.javaconfig.repository;

import by.cources.spring.task1.javaconfig.Book;
import java.util.Arrays;
import java.util.List;

public class InMemoryBookRepository implements BookRepository {

  private List<Book> cache = Arrays.asList(
      new Book("book1"),
      new Book("book2")
  );

  @Override
  public List<Book> findAll() {
    return cache;
  }
}
