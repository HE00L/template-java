package org.oobootcamp.core.exception;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {
        super("票无效");
    }
}
