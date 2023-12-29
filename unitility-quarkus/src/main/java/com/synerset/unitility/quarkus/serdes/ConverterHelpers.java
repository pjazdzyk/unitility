package com.synerset.unitility.quarkus.serdes;

import com.synerset.unitility.unitsystem.utils.StringTransformer;

class ConverterHelpers {

    private ConverterHelpers() {
        throw new IllegalStateException("Utility class");
    }

    static String prepareInput(String quantityInput) {
        return StringTransformer.of(quantityInput)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .unifyDMSNotationSymbols()
                .toString();
    }

}
