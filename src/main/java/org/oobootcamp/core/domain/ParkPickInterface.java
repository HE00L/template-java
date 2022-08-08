package org.oobootcamp.core.domain;

public interface ParkPickInterface {
    Ticket parkingCar(Car car);

    Car pickUpCar(Ticket ticket);

    Boolean isFull();

    Boolean hasCar(Ticket ticket);
}
