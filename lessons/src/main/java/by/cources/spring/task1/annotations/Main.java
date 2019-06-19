package by.cources.spring.task1.annotations;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BookConfig.class);
    BookService service = ctx.getBean("bookService", BookService.class);
    List<Book> all = service.findAll();
    for (Book book : all) {
      System.out.println("BOOK " + book);
    }
    StoreService storeService = ctx.getBean(StoreService.class);
    List<Book> storeServiceall = storeService.findAll();
    for (Book book : storeServiceall) {
      System.out.println("STORE " + book);
    }
  }
}
