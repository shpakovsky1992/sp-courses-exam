package by.cources.spring.exam.service;

import by.cources.spring.exam.model.Author;
import by.cources.spring.exam.model.Book;
import by.cources.spring.exam.model.Language;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

public interface BookService {

  List<Book> findBooksAll();

  List<Book> deleteBookById(Long id);

  @Transactional
  Author saveAuthor(Author author);

  List<Author> findAuthorsWithBookOlderThan(Long value);

  List<Book> findBooksWithBookOlderThan(Long value);

  List<Book> findBooksWithBookName(String value);

  List<Author> findAuthorsAll();

  Book saveBook(Book book);

  Language saveLanguage(Language language);

  Optional<Book> findBookById(Long id);
}
