package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;

public class GraduateParkingBoy extends AbstractParkingBoy {

    protected GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    Option<ParkingLot> findParkingLot() {
        return this.parkingLots.find(this::findNotFullParkingLot);
    }

    private Boolean findNotFullParkingLot(ParkingLot parkingLot) {
        return !parkingLot.isFull();
    }
}
