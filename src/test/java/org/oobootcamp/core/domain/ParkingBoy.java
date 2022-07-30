package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.oobootcamp.core.exception.ParkCarException;

public record ParkingBoy(List<ParkingLot> parkingLots) {
    public Ticket parkingCar(Car car) {
        return parkingLots.filter(parkingLot -> !parkingLot.isFull()).getOrElseThrow(ParkCarException::new).parkingCar(car);
    }
}
