package com.mtotowamkwe.lostboyz.exception;

import com.mtotowamkwe.lostboyz.model.User;
import com.mtotowamkwe.lostboyz.utils.Constants;

public class UserUpdateFailedException extends RuntimeException {

    public UserUpdateFailedException(User user) {
        super(Constants.USER_UPDATE_FAILED_ERROR_MESSAGE + user);
    }
}
