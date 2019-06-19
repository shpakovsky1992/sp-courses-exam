package by.cources.spring.task1.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Main {

  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(Main.class);
    ctx.getBean("firstWheel", Wheel.class).rotate();
    Car car2 = ctx.getBean("car2", Car.class);
    car2.go();
  }

  @Bean("car2")
  public Car getCar() {
    return new Car();
  }

  @Bean("car1")
  public Car getCar1() {
    return new Car();
  }

  @Bean("firstWheel")
  public Wheel firstWheel() {
    return new Wheel();
  }
}
