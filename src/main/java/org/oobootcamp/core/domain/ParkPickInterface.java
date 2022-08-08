package org.oobootcamp.core.domain;

public interface ParkPickInterface {
    public Ticket parkingCar(Car car);

    public Car pickUpCar(Ticket ticket);

    Boolean isFull();

    Boolean hasCar(Ticket ticket);
}
