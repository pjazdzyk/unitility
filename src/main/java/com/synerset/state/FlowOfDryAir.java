package com.synerset.state;

import com.synerset.unitsystem.density.KiloGramPerCubicMeter;
import com.synerset.unitsystem.massflow.KiloGramPerSecond;
import com.synerset.unitsystem.pressure.Pascal;
import com.synerset.unitsystem.specificheat.KiloJoulePerKilogramKelvin;
import com.synerset.unitsystem.temperature.Celsius;

public record FlowOfDryAir(DryAirState dryAir,
                           KiloGramPerSecond massFlow){
    public Pascal pressure(){
        return dryAir.pressure();
    }

    public Celsius temperature(){
        return dryAir.temperature();
    }

    public KiloGramPerCubicMeter density(){
        return dryAir.density();
    }

    public KiloJoulePerKilogramKelvin specificHeat(){
        return dryAir.specificHeat();
    }

}


