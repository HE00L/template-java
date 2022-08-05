package org.oobootcamp.core.exception;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException() {
        super("停车场已满");
    }
}
