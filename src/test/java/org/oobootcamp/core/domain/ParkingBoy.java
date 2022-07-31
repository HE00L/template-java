package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

import java.util.function.Predicate;

public record ParkingBoy(List<ParkingLot> parkingLots) {
    public Ticket parkingCar(Car car) {
        return parkingLots.filter(parkingLot -> !parkingLot.isFull()).getOrElseThrow(ParkCarException::new).parkingCar(car);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkingLots
                .filter(findParkingLotByTicket(ticket))
                .peekOption()
                .map(parkingLot -> parkingLot.pickUpCar(ticket))
                .getOrElseThrow(PickUpCarException::new);
    }

    private Predicate<ParkingLot> findParkingLotByTicket(Ticket ticket) {
        return parkingLot -> parkingLot.findParkingTileByTicket(ticket).isDefined();
    }
}
