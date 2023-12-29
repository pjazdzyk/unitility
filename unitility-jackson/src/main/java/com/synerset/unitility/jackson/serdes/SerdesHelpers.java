package com.synerset.unitility.jackson.serdes;

import com.synerset.unitility.unitsystem.utils.StringTransformer;

class SerdesHelpers {

    private SerdesHelpers() {
        throw new IllegalStateException("Utility class");
    }

    static String prepareInput(String quantityInput) {
        if (quantityInput == null) {
            return null;
        }
        return StringTransformer.of(quantityInput)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .unifyDMSNotationSymbols()
                .toString();
    }

}