package com.mtotowamkwe.lostboyz.exception;

import com.mtotowamkwe.lostboyz.model.User;
import com.mtotowamkwe.lostboyz.utils.Constants;

public class UserRegistrationFailedException extends RuntimeException {

    public UserRegistrationFailedException(User user) {
        super(Constants.USER_REGISTRATION_ERROR_MESSAGE + user);
    }
}
