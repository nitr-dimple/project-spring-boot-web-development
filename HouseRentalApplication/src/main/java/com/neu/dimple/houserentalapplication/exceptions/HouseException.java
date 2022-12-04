package com.neu.dimple.houserentalapplication.exceptions;

/**
 * @author Dimpleben Kanjibhai Patel
 */
public class HouseException extends Exception{

    public HouseException(String message) {
        super(message);
    }

    public HouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
