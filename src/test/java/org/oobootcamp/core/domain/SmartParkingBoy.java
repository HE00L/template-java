package org.oobootcamp.core.domain;

import io.vavr.collection.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket parkingCar(Car car) {
        java.util.List<ParkingLot> parkingLotList = parkingLots.toJavaList();
        parkingLotList.sort((o1, o2) -> o1.getTilesCount() > o2.getTilesCount() ? -1 : 0);
        return parkingLotList.get(0).parkingCar(car);
    }
}
