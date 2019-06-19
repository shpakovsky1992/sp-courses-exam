package by.cources.spring.exam;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import by.cources.spring.exam.model.Book;
import by.cources.spring.exam.model.Language;
import by.cources.spring.exam.repository.AuthorRepository;
import by.cources.spring.exam.repository.BookRepository;
import by.cources.spring.exam.repository.LanguageRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BookJpaConfig.class)
public class BookJpaConfigTest {

  @Autowired
  private LanguageRepository languageRepository;
  @Autowired
  private AuthorRepository authorRepository;
  @Autowired
  private BookRepository bookRepository;

  @Test
  public void test1() {
    assertEquals(toList(languageRepository.findAll()).size(), 2);
    Language language = new Language();
    language.setDescription("Italian");
    languageRepository.save(language);
    assertEquals(toList(languageRepository.findAll()).size(), 3);
    languageRepository.delete(language);
  }

  @Test
  public void test2() {
    assertFalse(languageRepository.findByName("Italian").isPresent());
    Language language = new Language();
    language.setDescription("Italian");
    languageRepository.save(language);
    assertEquals(toList(languageRepository.findAll()).size(), 3);
    assertTrue(languageRepository.findByName("Russian").isPresent());
    assertTrue(languageRepository.findByName("Italian").isPresent());
    languageRepository.delete(language);
  }

  @Test
  public void test3() {
    Optional<Book> book = bookRepository.findById(1L);
    assertEquals(toList(bookRepository.findByName("Sense and Sensibility")).size(), 1);
    bookRepository.deleteById(1L);
    assertEquals(toList(bookRepository.findByName("Sense and Sensibility")).size(), 0);
    bookRepository.save(book.get());
  }

  @Test
  public void test4() {
    assertEquals(toList(authorRepository.findWithBookOlderThan(1813L)).size(), 2);
  }

  @Test
  public void test5() {
    assertEquals(toList(authorRepository.findAll()).size(), 4);
    assertEquals(toList(bookRepository.findAll()).size(), 5);
    authorRepository.deleteById(1L);
    assertEquals(toList(authorRepository.findAll()).size(), 3);
    assertEquals(toList(bookRepository.findAll()).size(), 2);
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> result = new ArrayList<>();
    for (T i : iterable) {
      result.add(i);
    }
    return result;
  }
}