package com.synerset.exampleproject.common;

import com.synerset.unitsystem.pressure.Pascal;
import com.synerset.unitsystem.pressure.Pressure;

public final class Defaults {

    private Defaults() {
    }

    public final static Pascal DEF_PAT = Pressure.pascal(101_325);

}
