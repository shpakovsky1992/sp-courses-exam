package by.cources.spring.task1.javaconfig;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BookConfig.class);
    BookService service = ctx.getBean("bookService", BookService.class);
    List<Book> all = service.findAll();
    for (Book book : all) {
      System.out.println(book);
    }
  }
}
