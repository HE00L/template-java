//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

import java.util.function.Predicate;

public abstract class AbstractParkingBoy implements ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    protected AbstractParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket parkingCar(Car car) {
        return this.findParkingLot().map((parkingLot) -> parkingLot.parkingCar(car)).getOrElseThrow(ParkCarException::new);
    }

    public Car pickUpCar(Ticket ticket) {
        return this.parkingLots.find(findParkingLotByTicket(ticket))
                .map((parkingLot) -> parkingLot.pickUpCar(ticket))
                .getOrElseThrow(PickUpCarException::new);
    }

    abstract Option<ParkingLot> findParkingLot();

    private Predicate<ParkingLot> findParkingLotByTicket(Ticket ticket) {
        return (parkingLot) -> parkingLot.findParkingTileByTicket(ticket).isDefined();
    }
}
