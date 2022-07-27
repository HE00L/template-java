package org.oobootcamp.core.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ParkingLot {
    private final int id;
    private final Set<ParkingSpace> parkingSpaces;
    private int remainingCount;

    public ParkingLot(int slotCount) {
        this.id = new Random().nextInt();
        this.remainingCount = slotCount;
        this.parkingSpaces = new HashSet<>(slotCount);
    }

    public Ticket parkingCar(Car car) {
        remainingCount--;
        Ticket ticket = new Ticket(car.id());
        parkingSpaces.add(new ParkingSpace(ticket.id(), car));
        return ticket;
    }

    public Car pickUpCar(Ticket ticket) {
        remainingCount++;
        return parkingSpaces.stream()
                .filter(space -> space.getTicket_id() == ticket.id())
                .map(ParkingSpace::getCar)
                .findFirst()
                .orElse(null);
    }

    public int getRemainingCount() {
        return remainingCount;
    }
}
