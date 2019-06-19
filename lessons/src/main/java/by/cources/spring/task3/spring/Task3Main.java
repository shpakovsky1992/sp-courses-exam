package by.cources.spring.task3.spring;

import by.cources.spring.task3.spring.model.Author;
import by.cources.spring.task3.spring.model.Book;
import by.cources.spring.task3.spring.service.BookService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task3Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BookConfig.class);
    BookService service = ctx.getBean(BookService.class);
    System.out.println("---------------------");
    query1(service);
    System.out.println("---------------------");
    query2(service);
    System.out.println("---------------------");
    query3(service);
    System.out.println("---------------------");
    query4(service);
    System.out.println("---------------------");
    query5(service);
  }

  private static void query1(BookService service) {
    List<Book> all = service.findBooksAll();
    for (Book book : all) {
      System.out.println("Query1: " + book);
    }
  }

  private static void query2(BookService service) {
    List<Book> all = service.findBooksWithBookOlderThan(1818L);
    for (Book book : all) {
      System.out.println("Query2: " + book);
    }
  }

  private static void query3(BookService service) {
    List<Author> authors = service.findAuthorsWithBookOlderThan(1818L);
    for (Author author : authors) {
      System.out.println("Query3: " + author);
    }
  }

  private static void query4(BookService service) {
    List<Book> books = service.findBooksWithBookName("Le Capitaine Paul");
    for (Book book : books) {
      System.out.println("Query4: " + book);
    }
  }

  private static void query5(BookService service) {
    List<Author> authors = service.findAuthorsAll();
    for (Author author : authors) {
      System.out.println("Query5: " + author);
    }
  }
}
