package by.cources.spring.task1.spring2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
    ctx.getBean("firstWheel", Wheel.class).rotate();
    ctx.getBean(Car.class).go();
  }

  @Bean
  public Car getCar(Wheel firstWheel, Wheel secondWheel) {
    return new Car(firstWheel, secondWheel);
  }

  @Bean("firstWheel")
  public Wheel firstWheel() {
    return new Wheel();
  }

  @Bean
  public Wheel secondWheel() {
    return new Wheel();
  }
}
