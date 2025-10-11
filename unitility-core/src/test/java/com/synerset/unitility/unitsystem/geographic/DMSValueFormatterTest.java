package com.synerset.unitility.unitsystem.geographic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DMSValueFormatterTest {

    @Test
    void testConvertDMSValueToDMSValue()
    {
        Latitude latitude = Latitude.ofDegrees(52.1);
        String string = latitude.toDMSFormat(-1);

    }

}