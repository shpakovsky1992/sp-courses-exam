package by.cources.spring.exam.repository;

import by.cources.spring.exam.model.Author;
import by.cources.spring.exam.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

  List<Author> findWithBookOlderThan(Long value);

  Optional<Author> findById(Long id);

  List<Author> findAll();

  Author save(Author author);

  void deleteById(Long l);
}
