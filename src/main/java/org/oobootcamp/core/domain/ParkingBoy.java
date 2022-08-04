package org.oobootcamp.core.domain;

public interface ParkingBoy {
    Ticket parkingCar(Car car);

    Car pickUpCar(Ticket ticket);
}
