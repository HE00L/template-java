package org.oobootcamp.core.domain;

import io.vavr.collection.List;
import org.oobootcamp.core.exception.ParkCarException;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket parkingCar(Car car) {
        return parkingLots
                .maxBy(Comparator.comparingInt(ParkingLot::getUsefulTilesCount))
                .map(parkingLot -> parkingLot.parkingCar(car))
                .getOrElseThrow(ParkCarException::new);
    }
}
