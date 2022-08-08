package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;

public class GraduateParkingBoy extends ParkingBoy {

    protected GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    Option<ParkingLot> findAvailableParkingLot() {
        return this.parkingLots.find(parkingLot -> !parkingLot.isFull());
    }
}
