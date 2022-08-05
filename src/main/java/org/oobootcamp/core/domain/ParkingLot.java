package org.oobootcamp.core.domain;

import org.oobootcamp.core.exception.InvalidTicketException;
import org.oobootcamp.core.exception.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int NO_USED = 0;
    private final Map<Ticket, Car> ticketCarMap;
    private final int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.ticketCarMap = new HashMap<>(capacity);
    }

    public Ticket parkingCar(Car car) {
        if (isFull())
            throw new ParkingLotFullException();
        Ticket ticket = new Ticket();
        ticketCarMap.put(ticket, car);
        return ticket;
    }

    public Car pickUpCar(Ticket ticket) {
        if (isEmpty() || !findCarByTicket(ticket))
            throw new InvalidTicketException();
        return ticketCarMap.remove(ticket);
    }

    public int getRemainingCount() {
        return capacity - ticketCarMap.size();
    }

    protected boolean findCarByTicket(Ticket ticket) {
        return ticketCarMap.containsKey(ticket);
    }

    protected boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    private boolean isEmpty() {
        return ticketCarMap.size() == NO_USED;
    }
}
