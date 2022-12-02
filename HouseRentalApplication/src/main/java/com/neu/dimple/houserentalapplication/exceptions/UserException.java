package com.neu.dimple.houserentalapplication.exceptions;

/**
 * @author Dimpleben Kanjibhai Patel
 */
public class UserException extends Exception{

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
