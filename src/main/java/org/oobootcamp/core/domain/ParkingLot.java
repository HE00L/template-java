package org.oobootcamp.core.domain;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Try;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

public class ParkingLot {
    private Set<ParkingTile> parkedTiles;
    private int remainingCount;
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.remainingCount = capacity;
        this.parkedTiles = HashSet.empty();
    }

    public Ticket parkingCar(Car car) {
        if (isFull())
            throw new ParkCarException();
        return ParkAndGetTicket(car);
    }

    private Ticket ParkAndGetTicket(Car car) {
        remainingCount--;
        Ticket ticket = new Ticket(car.id());
        parkedTiles = parkedTiles.add(new ParkingTile(ticket, car));
        return ticket;
    }

    public Car pickUpCar(Ticket ticket) {
        return Try.of(() -> pickUp(ticket)).getOrElseThrow(PickUpCarException::new);
    }

    private Car pickUp(Ticket ticket) {
        if (remainingCount == capacity)
            throw new PickUpCarException();
        ParkingTile tile = findParkingTileByTicket(ticket);
        return freeTile(tile);
    }

    private Car freeTile(ParkingTile tile) {
        remainingCount++;
        parkedTiles = parkedTiles.remove(tile);
        return tile.getCar();
    }

    private ParkingTile findParkingTileByTicket(Ticket ticket) {
        return parkedTiles.filter(tile -> tile.getTicket().id() == ticket.id()).get();
    }

    private boolean isFull() {
        return remainingCount == 0;
    }
}
