package com.synerset.unitility.validation;

/**
 * Internal utility class providing common validation helper methods for physical quantity validators.
 */
final class ValidationHelpers {

    /**
     * Maximum allowed length for annotation string values passed to the parser.
     */
    static final int MAX_INPUT_LENGTH = 200;

    private ValidationHelpers() {
        throw new IllegalStateException("Utility class");
    }

    static boolean isEmpty(String inputString){
        return inputString == null || inputString.isBlank();
    }

    static boolean isNotEmpty(String inputString){
        return !isEmpty(inputString);
    }

    static void validateInputLength(String inputString) {
        if (inputString != null && inputString.length() > MAX_INPUT_LENGTH) {
            throw new IllegalArgumentException(
                    "Input exceeds maximum allowed length of " + MAX_INPUT_LENGTH + " characters: length=" + inputString.length()
            );
        }
    }

}