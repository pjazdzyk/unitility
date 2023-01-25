package com.synerset.exampleproject.state;

import com.synerset.unitsystem.density.KiloGramPerCubicMeter;
import com.synerset.unitsystem.pressure.Pascal;
import com.synerset.unitsystem.specificheat.KiloJoulePerKilogramKelvin;
import com.synerset.unitsystem.temperature.Celsius;

public record DryAirState(Pascal pressure,
                          Celsius temperature,
                          KiloGramPerCubicMeter density,
                          KiloJoulePerKilogramKelvin specificHeat) {
}
