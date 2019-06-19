package by.cources.spring.task1.annotations.repository;

import by.cources.spring.task1.annotations.Book;
import java.util.List;

public interface BookRepository {

  List<Book> findAll();
}
