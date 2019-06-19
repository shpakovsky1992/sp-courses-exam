package by.cources.spring.task1.javaconfig.repository;

import by.cources.spring.task1.javaconfig.Book;
import java.util.List;

public interface BookRepository {

  List<Book> findAll();
}
