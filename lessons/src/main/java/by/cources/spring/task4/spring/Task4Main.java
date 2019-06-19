package by.cources.spring.task4.spring;

import by.cources.spring.task4.spring.model.Author;
import by.cources.spring.task4.spring.model.Book;
import by.cources.spring.task4.spring.service.BookService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task4Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BookConfig.class);
    BookService service = ctx.getBean(BookService.class);
    System.out.println("---------------------");
    save(service);
    save(service);
    save(service);
    save(service);
    save(service);
    System.out.println("---------------------");
    query1(service);
    System.out.println("---------------------");
    query5(service);
  }

  private static void save(BookService service) {
    Author author = new Author();
    author.setLastName("lastName");
    author.setFirstName("firstName");
    author.setDateOfBirth(LocalDate.now());
    service.saveAuthor(author);

    Book book = new Book();
    book.setAuthor(author);
    book.setName("name");
    book.setPublishedIn(1922L);

    service.saveBook(book);
    System.out.println("Saved author with id = " + author.getId());
  }

  private static void query1(BookService service) {
    List< Book> all = service.findBooksAll();
    for ( Book book : all) {
      System.out.println("Query1: " + book);
    }
  }
  
  private static void query5(BookService service) {
    List<Author> authors = service.findAuthorsAll();
    for (Author author : authors) {
      System.out.println("Query5: " + author);
    }
  }
}
