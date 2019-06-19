package by.cources.spring.task1.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {

  @Autowired
  private Wheel firstWheel;
  @Autowired
  private Wheel secondWheel;

  public Car() {
  }

  public Wheel getFirstWheel() {
    return firstWheel;
  }

  public void setFirstWheel(Wheel firstWheel) {
    this.firstWheel = firstWheel;
  }

  public Wheel getSecondWheel() {
    return secondWheel;
  }

  public void setSecondWheel(Wheel secondWheel) {
    this.secondWheel = secondWheel;
  }

  public void go() {
    firstWheel.rotate();
    secondWheel.rotate();
  }
} 