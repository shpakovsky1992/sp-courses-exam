package by.cources.spring.task1.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Wheel {

  @Autowired
  @Qualifier("car1")
  private Car owner;

  public Car getOwner() {
    return owner;
  }

  public void setOwner(Car owner) {
    this.owner = owner;
  }

  public void rotate() {
    System.out.println(owner);
    // change position of car
  }
}