package com.synerset.exampleproject.common;

import com.synerset.unitsystem.pressure.Pressure;

public final class Defaults {

    private Defaults() {
    }

    public final static Pressure DEF_PAT = Pressure.ofPascal(101_325);

}
