package com.synerset.unitility.spring.serdes;

import com.synerset.unitility.unitsystem.utils.StringTransformer;
import org.springframework.lang.NonNull;

class ConverterHelpers {

    private ConverterHelpers() {
        throw new IllegalStateException("Utility class");
    }

    static String prepareInput(@NonNull String quantityInput) {
        return StringTransformer.of(quantityInput)
                .trimLowerAndClean()
                .replaceCommaForDot()
                .unifyDMSNotationSymbols()
                .toString();
    }

}
