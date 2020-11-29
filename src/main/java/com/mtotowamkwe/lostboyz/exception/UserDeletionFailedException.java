package com.mtotowamkwe.lostboyz.exception;

import com.mtotowamkwe.lostboyz.utils.Constants;

import java.util.UUID;

public class UserDeletionFailedException extends RuntimeException {

    public UserDeletionFailedException(UUID id) {
        super(Constants.USER_DELETION_FAILED_ERROR_MESSAGE + id);
    }
}
