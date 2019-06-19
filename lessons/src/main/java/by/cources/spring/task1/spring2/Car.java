package by.cources.spring.task1.spring2;

public class Car {

  private final Wheel firstWheel;
  private final Wheel secondWheel;

  public Car(Wheel firstWheel, Wheel secondWheel) {
    this.firstWheel = firstWheel;
    this.secondWheel = secondWheel;
  }

  public Wheel getFirstWheel() {
    return firstWheel;
  }

  public Wheel getSecondWheel() {
    return secondWheel;
  }

  public void go() {
    firstWheel.rotate();
    secondWheel.rotate();
  }
} 