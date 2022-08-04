package org.oobootcamp.core.domain;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.oobootcamp.core.exception.ParkCarException;
import org.oobootcamp.core.exception.PickUpCarException;

public class ParkingLot {
    public static final int NO_REMAINING = 0;
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

    public Car pickUpCar(Ticket ticket) {
        return Try.of(() -> pickUp(ticket)).getOrElseThrow(PickUpCarException::new);
    }

    public int getUsefulTilesRemaining() {
        return remainingCount;
    }

    protected Ticket ParkAndGetTicket(Car car) {
        remainingCount--;
        Ticket ticket = new Ticket();
        parkedTiles = parkedTiles.add(new ParkingTile(ticket, car));
        return ticket;
    }

    private Car pickUp(Ticket ticket) {
        if (remainingCount == capacity)
            throw new PickUpCarException();
        return findParkingTileByTicket(ticket).map(this::freeTile).getOrElseThrow(ParkCarException::new);
    }

    private Car freeTile(ParkingTile tile) {
        remainingCount++;
        parkedTiles = parkedTiles.remove(tile);
        return tile.getCar();
    }

    protected Option<ParkingTile> findParkingTileByTicket(Ticket ticket) {
        return parkedTiles.find(tile -> tile.getTicket().equals(ticket));
    }

    protected boolean isFull() {
        return remainingCount == NO_REMAINING;
    }
}
