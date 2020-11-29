package com.mtotowamkwe.lostboyz.exception;

import com.mtotowamkwe.lostboyz.utils.Constants;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id) {
        super(Constants.USER_NOT_FOUND_ERROR_MESSAGE + id);
    }
}
