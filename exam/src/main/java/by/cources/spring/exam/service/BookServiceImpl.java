package by.cources.spring.exam.service;

import by.cources.spring.exam.model.Author;
import by.cources.spring.exam.model.Book;
import by.cources.spring.exam.model.Language;
import by.cources.spring.exam.repository.AuthorRepository;
import by.cources.spring.exam.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.cources.spring.exam.repository.LanguageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final LanguageRepository languageRepository;

  public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, LanguageRepository languageRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.languageRepository = languageRepository;
  }

  @Transactional
  @Override
  public List<Book> findBooksAll() {
    return toList(bookRepository.findAll());
  }

  @Override
  public List<Book> deleteBookById(Long id) {
    bookRepository.deleteById(id);
    return toList(bookRepository.findAll());
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @Override
  public Author saveAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Override
  @Transactional
  public List<Author> findAuthorsWithBookOlderThan(Long value) {
    return authorRepository.findWithBookOlderThan(value);
  }

  @Override
  @Transactional
  public List<Book> findBooksWithBookOlderThan(Long value) {
    return bookRepository.findOlderThan(value);
  }

  @Override
  @Transactional
  public List<Book> findBooksWithBookName(String value) {
    return bookRepository.findByName(value);
  }

  @Override
  @Transactional
  public List<Author> findAuthorsAll() {
    return authorRepository.findAll();
  }

  @Override
  public Book saveBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Language saveLanguage(Language language) {
    return languageRepository.save(language);
  }

  @Override
  public Optional<Book> findBookById(Long id) {
    return bookRepository.findById(id);
  }

  private <T> List<T> toList(Iterable<T> items) {
    List<T> list = new ArrayList<>();
    for (T t : items) {
      list.add(t);
    }
    return list;
  }
}
