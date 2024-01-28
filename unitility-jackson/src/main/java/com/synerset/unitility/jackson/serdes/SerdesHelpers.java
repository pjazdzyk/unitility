package com.synerset.unitility.jackson.serdes;

import com.synerset.unitility.unitsystem.utils.StringTransformer;

public class SerdesHelpers {

    private SerdesHelpers() {
        throw new IllegalStateException("Utility class");
    }

    public static String prepareInput(String quantityInput) {
        if (quantityInput == null) {
            return null;
        }
        return StringTransformer.of(quantityInput)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .unifyDMSNotationSymbols()
                .toString();
    }

    public static boolean containsNonDigitChars(String inputString) {
        for (char nextChar : inputString.toCharArray()) {
            if (Character.isAlphabetic(nextChar)) {
                return true;
            }
        }
        return false;
    }

}