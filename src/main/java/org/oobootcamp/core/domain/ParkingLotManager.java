package org.oobootcamp.core.domain;

import org.oobootcamp.core.exception.InvalidTicketException;
import org.oobootcamp.core.exception.ParkingLotFullException;

import io.vavr.collection.List;

public class ParkingLotManager {

    List<ParkPickInterface> parkpickList;

    public ParkingLotManager(List<ParkPickInterface> parkpickList) {
        this.parkpickList = parkpickList;
    }

    public Ticket parkingCar(Car car) {
        return parkpickList.find(pp -> !pp.isFull()).map(pp -> pp.parkingCar(car))
                .getOrElseThrow(ParkingLotFullException::new);
    }

    public Car pickUpCar(Ticket ticket) {
        return parkpickList.find(pp -> pp.hasCar(ticket)).map(pp -> pp.pickUpCar(ticket))
                .getOrElseThrow(InvalidTicketException::new);
    }

}
