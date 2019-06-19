package by.cources.spring.task1.simply;

public class Wheel {

  private Car owner;

  public Wheel(Car car) {
    setOwner(car);
  }

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