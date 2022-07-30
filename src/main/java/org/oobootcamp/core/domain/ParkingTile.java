package org.oobootcamp.core.domain;

public record ParkingTile(Ticket ticket, Car car) {

    public Ticket getTicket() {
        return ticket;
    }

    public Car getCar() {
        return car;
    }
}
