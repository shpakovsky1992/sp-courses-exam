package by.cources.spring.task1.simply;

public class Car {

  private Wheel firstWheel;
  private Wheel secondWheel;

  public Car() {
    firstWheel = new Wheel(this);
    secondWheel = new Wheel(this);
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