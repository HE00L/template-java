package org.oobootcamp.core.exception;

import static org.oobootcamp.core.exception.constant.ExceptionConstants.ERROR_MESSAGE_PICK_UP_CAR_FAILED;

public class PickUpCarException extends RuntimeException {
    public PickUpCarException() {
        super(ERROR_MESSAGE_PICK_UP_CAR_FAILED);
    }
}
