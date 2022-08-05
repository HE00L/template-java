//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.oobootcamp.core.exception.ParkingLotFullException;
import org.oobootcamp.core.exception.InvalidTicketException;

public abstract class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket parkingCar(Car car) {
        return this.findAvailableParkingLot().map((parkingLot) -> parkingLot.parkingCar(car)).getOrElseThrow(ParkingLotFullException::new);
    }

    public Car pickUpCar(Ticket ticket) {
        return this.parkingLots.find((parkingLot1) -> parkingLot1.hasCarByTicket(ticket))
                .map((parkingLot) -> parkingLot.pickUpCar(ticket))
                .getOrElseThrow(InvalidTicketException::new);
    }

    abstract Option<ParkingLot> findAvailableParkingLot();

}
