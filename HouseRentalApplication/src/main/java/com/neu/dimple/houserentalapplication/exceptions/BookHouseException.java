package com.neu.dimple.houserentalapplication.exceptions;

/**
 * @author Dimpleben Kanjibhai Patel
 */
public class BookHouseException extends Exception{

    public BookHouseException(String message) {
        super(message);
    }

    public BookHouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
