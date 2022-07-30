package org.oobootcamp.core.exception;

import static org.oobootcamp.core.exception.constant.ExceptionConstants.ERROR_MESSAGE_PARK_CAR_FAILED;

public class ParkCarException extends RuntimeException {
    public ParkCarException() {
        super(ERROR_MESSAGE_PARK_CAR_FAILED);
    }
}
