/*
 * (C) Copyright Spacebel, 2022
 *
 * Project: Spacebel Product
 * Product: Timeline
 */
package com.telemis.africanbowlinggame.model;

public class GameException extends RuntimeException {

    public GameException(String message) {
        super(message);
    }

    public GameException(Throwable cause) {
        super(cause);
    }
}
