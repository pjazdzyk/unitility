package com.synerset.physics;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.dynamicViscosity.DynamicViscosity;
import com.synerset.unitsystem.kinematicviscosity.KinematicViscosity;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.specificenthalpy.SpecificEnthalpy;
import com.synerset.unitsystem.specificheat.SpecificHeat;
import com.synerset.unitsystem.temperature.Temperature;
import com.synerset.unitsystem.thermalconductivity.ThermalConductivity;

public class PropertiesOfDryAir {

    private static final PropertiesOfDryAir propertiesOfDryAir = new PropertiesOfDryAir();

    private PropertiesOfDryAir() {
    }

    public KinematicViscosity kinematicViscosity(Temperature temp, Density density){
        double ta = temp.toCelsius().getValue();
        double dynVis = dynamicViscosity(temp).toKiloGramPerMeterSecond().getValue();
        double rho = density.toKiloGramPerCubicMeter().getValue();
        double kinVis = dynVis / rho;
        return KinematicViscosity.squareMeterPerSecond(kinVis);
    }

    public DynamicViscosity dynamicViscosity(Temperature temp) {
        double tk = temp.toKelvin().getValue();
        double dynVis = (0.40401 + 0.074582 * tk - 5.7171 * Math.pow(10, -5)
                * Math.pow(tk, 2) + 2.9928 * Math.pow(10, -8)
                * Math.pow(tk, 3) - 6.2524 * Math.pow(10, -12)
                * Math.pow(tk, 4)) * Math.pow(10, -6);
        return DynamicViscosity.kiloGramPerMeterSecond(dynVis);
    }

    public ThermalConductivity thermalConductivity(Temperature temp) {
        double ta = temp.toCelsius().getValue();
        double thermalCond = 2.43714 * Math.pow(10, -2)
                + 7.83035 * Math.pow(10, -5) * ta
                - 1.94021 * Math.pow(10, -8) * Math.pow(ta, 2)
                + 2.85943 * Math.pow(10, -12) * Math.pow(ta, 3)
                - 2.61420 * Math.pow(10, -14) * Math.pow(ta, 4);
        return ThermalConductivity.wattPerMeterKelvin(thermalCond);
    }

    public SpecificEnthalpy specificEnthalpy(Temperature temp) {
        SpecificHeat specHeat = specificHeat(temp).toKiloJoulePerKilogramKelvin();
        double specHeatValue = specHeat.getValue();
        double tempValueInC = temp.toCelsius().getValue();
        double specEnthalpy = specHeatValue * tempValueInC;
        return SpecificEnthalpy.kiloJoulePerKiloGram(specEnthalpy);
    }

    public Density density(Temperature temp, Pressure press) {
        double tk = temp.toKelvin().getValue();
        double pa = press.toPascal().getValue();
        double density = pa / (PhysicsConstants.CST_DA_RG * tk);
        return Density.kiloGramPerCubicMeter(density);
    }

    public SpecificHeat specificHeat(Temperature temp) {
        double ta = temp.toCelsius().getValue();
        if (ta <= -73.15) {
            return SpecificHeat.kiloJoulePerKilogramKelvin(1.002);
        }
        if (ta > -73.15 && ta <= -53.15) {
            double specHeat = MathUtils.linearInterpolation(-73.15, 1.002, -53.15, 1.003, ta);
            return SpecificHeat.kiloJoulePerKilogramKelvin(specHeat);
        }
        if (ta > -53.15 && ta <= -13.15) {
            return SpecificHeat.kiloJoulePerKilogramKelvin(1.003);
        }
        double a, b, c, d, e;
        if (ta > -13.15 && ta <= 86.85) {
            a = 1.0036104793123004;
            b = 5.2562229415778261e-05;
            c = 2.9091167529181888e-07;
            d = -1.3405671294850166e-08;
            e = 1.3020833332371173e-10;
        } else {
            a = 1.0065876262557212;
            b = -2.9062712816134989E-05;
            c = 7.4445335877306371E-07;
            d = -8.4171864437938596E-10;
            e = 3.0582028042912701E-13;
        }

        double specHeat = e * ta * ta * ta * ta
                + d * ta * ta * ta
                + c * ta * ta
                + b * ta
                + a;

        return SpecificHeat.kiloJoulePerKilogramKelvin(specHeat);
    }

    static PropertiesOfDryAir getInstance(){
        return propertiesOfDryAir;
    }

}
