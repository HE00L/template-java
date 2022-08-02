package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

import java.util.function.Predicate;

public class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket parkingCar(Car car) {
        return parkingLots
                .find(this::findNotFullParkingLot)
                .map(parkingLot -> parkingLot.parkingCar(car))
                .getOrElseThrow(ParkCarException::new);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkingLots
                .find(findParkingLotByTicket(ticket))
                .map(parkingLot -> parkingLot.pickUpCar(ticket))
                .getOrElseThrow(PickUpCarException::new);
    }

    private Boolean findNotFullParkingLot(ParkingLot parkingLot) {
        return !parkingLot.isFull();
    }

    private Predicate<ParkingLot> findParkingLotByTicket(Ticket ticket) {
        return parkingLot -> parkingLot.findParkingTileByTicket(ticket).isDefined();
    }
}
