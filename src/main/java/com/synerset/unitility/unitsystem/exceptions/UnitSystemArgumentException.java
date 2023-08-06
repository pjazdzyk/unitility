package com.synerset.unitility.unitsystem.exceptions;

public class UnitSystemArgumentException extends RuntimeException{
    public UnitSystemArgumentException() {
    }

    public UnitSystemArgumentException(String message) {
        super(message);
    }

    public UnitSystemArgumentException(String message, Exception e) {
        super(message, e);
    }

}
