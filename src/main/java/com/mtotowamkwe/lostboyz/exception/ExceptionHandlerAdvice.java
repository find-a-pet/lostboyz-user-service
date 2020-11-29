package com.mtotowamkwe.lostboyz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseBody
    @ExceptionHandler(UserDeletionFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String userDeletionFailedHandler(UserDeletionFailedException udfx) {
        return udfx.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException unfx) {
        return unfx.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserRegistrationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String userRegistrationFailedHandler(UserRegistrationFailedException urfx) {
        return urfx.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserUpdateFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String userUpdateFailedHandler(UserUpdateFailedException upfx) {
        return upfx.getMessage();
    }
}
