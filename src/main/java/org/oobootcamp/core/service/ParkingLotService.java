package org.oobootcamp.core.service;

import org.oobootcamp.core.entity.Car;
import org.oobootcamp.core.entity.ParkingLot;
import org.oobootcamp.core.entity.Ticket;

public record ParkingLotService(ParkingLot parkingLot) {

    public Ticket parkCar(Car car) {
        if (parkingLot.getRemainingCount() == 0)
            return null;
        return parkingLot.parkingCar(car);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkingLot.pickUpCar(ticket);
    }
}
