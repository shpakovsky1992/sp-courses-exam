package by.cources.spring.task5.spring.repository;

import by.cources.spring.task5.spring.model.Author;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

  List<Author> findWithBookOlderThan(Long value);

  Optional<Author> findById(Long id);

  List<Author> findAll();

  Author save(Author author);
}
