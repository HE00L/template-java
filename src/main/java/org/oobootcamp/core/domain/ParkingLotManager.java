package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.oobootcamp.core.exception.InvalidTicketException;
import org.oobootcamp.core.exception.ParkingLotFullException;

public class ParkingLotManager {

    List<ParkPickInterface> parkingBoysAndParkingLots;

    public ParkingLotManager(List<ParkPickInterface> parkingBoysAndParkingLots) {
        this.parkingBoysAndParkingLots = parkingBoysAndParkingLots;
    }

    public Ticket parkingCar(Car car) {
        return parkingBoysAndParkingLots.find(pp -> !pp.isFull()).map(pp -> pp.parkingCar(car))
                .getOrElseThrow(ParkingLotFullException::new);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkingBoysAndParkingLots.find(pp -> pp.hasCar(ticket)).map(pp -> pp.pickUpCar(ticket))
                .getOrElseThrow(InvalidTicketException::new);
    }

}
