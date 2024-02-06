package com.synerset.unitility.validation;

final class ValidationHelpers {
    private ValidationHelpers() {
        throw new IllegalStateException("Utility class");
    }

    static boolean isEmpty(String inputString){
        return inputString == null || inputString.isBlank();
    }

    static boolean isNotEmpty(String inputString){
        return !isEmpty(inputString);
    }

}