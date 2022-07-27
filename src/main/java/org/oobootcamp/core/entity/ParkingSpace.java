package org.oobootcamp.core.entity;

import java.util.Random;

public class ParkingSpace {
    private final int id;
    private final int ticket_id;
    private final Car car;

    public ParkingSpace(int ticket_id, Car car) {
        this.id = new Random().nextInt();
        this.ticket_id = ticket_id;
        this.car = car;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public Car getCar() {
        return car;
    }
}
