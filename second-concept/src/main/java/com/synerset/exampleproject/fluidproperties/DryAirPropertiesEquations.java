package com.synerset.exampleproject.fluidproperties;

import com.synerset.exampleproject.utils.MathUtils;
import com.synerset.exampleproject.utils.PhysicsConstants;
import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.dynamicviscosity.DynamicViscosity;
import com.synerset.unitsystem.kinematicviscosity.KinematicViscosity;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.specificenthalpy.SpecificEnthalpy;
import com.synerset.unitsystem.specificheat.SpecificHeat;
import com.synerset.unitsystem.temperature.Temperature;
import com.synerset.unitsystem.thermalconductivity.ThermalConductivity;

final class DryAirPropertiesEquations {

    private static final DryAirPropertiesEquations INSTANCE = new DryAirPropertiesEquations();

    private DryAirPropertiesEquations() {
    }

    Density density(Temperature temp, Pressure press) {
        double tk = temp.toKelvin().getValue();
        double pa = press.toPascal().getValue();
        double rho = pa / (PhysicsConstants.CST_DA_RG * tk);
        return Density.ofKilogramPerCubicMeter(rho);
    }

    DynamicViscosity dynamicViscosity(Temperature temp) {
        double tk = temp.toKelvin().getValue();
        double dynVis = (0.40401 + 0.074582 * tk - 5.7171 * Math.pow(10, -5)
                * Math.pow(tk, 2) + 2.9928 * Math.pow(10, -8)
                * Math.pow(tk, 3) - 6.2524 * Math.pow(10, -12)
                * Math.pow(tk, 4)) * Math.pow(10, -6);
        return DynamicViscosity.ofKiloGramPerMeterSecond(dynVis);
    }

    ThermalConductivity thermalConductivity(Temperature temp) {
        double ta = temp.toCelsius().getValue();
        double k = 2.43714 * Math.pow(10, -2)
                + 7.83035 * Math.pow(10, -5) * ta
                - 1.94021 * Math.pow(10, -8) * Math.pow(ta, 2)
                + 2.85943 * Math.pow(10, -12) * Math.pow(ta, 3)
                - 2.61420 * Math.pow(10, -14) * Math.pow(ta, 4);
        return ThermalConductivity.ofWattsPerMeterKelvin(k);
    }

    SpecificHeat specificHeat(Temperature temp) {
        double ta = temp.toCelsius().getValue();
        if (ta <= -73.15) {
            return SpecificHeat.ofKiloJoulePerKiloGramKelvin(1.002);
        }
        if (ta > -73.15 && ta <= -53.15) {
            double specHeat = MathUtils.linearInterpolation(-73.15, 1.002, -53.15, 1.003, ta);
            return SpecificHeat.ofKiloJoulePerKiloGramKelvin(specHeat);
        }
        if (ta > -53.15 && ta <= -13.15) {
            return SpecificHeat.ofKiloJoulePerKiloGramKelvin(1.003);
        }
        double a, b, c, d, e; // Polynomial coefficients
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

        double specHeat = e * Math.pow(ta, 4)
                + d * Math.pow(ta, 3)
                + c * Math.pow(ta, 2)
                + b * ta
                + a;

        return SpecificHeat.ofKiloJoulePerKiloGramKelvin(specHeat);
    }

    SpecificEnthalpy specificEnthalpy(Temperature temp) {
        SpecificHeat specHeat = specificHeat(temp);
        double cp = specHeat.toJoulePerKiloGramKelvin().getValue();
        double ta = temp.toCelsius().getValue();
        double i = cp * ta;
        return SpecificEnthalpy.ofKiloJoulePerKiloGram(i);
    }

    KinematicViscosity kinematicViscosity(Temperature temp, Density density) {
        double densityVal = density.toKilogramPerCubicMeter().getValue();
        DynamicViscosity dynVis = dynamicViscosity(temp).toKiloGramPerMeterSecond();
        double kinVis = dynVis.getValue() / densityVal;
        return KinematicViscosity.ofSquareMeterPerSecond(kinVis);
    }

    static DryAirPropertiesEquations getInstance() {
        return INSTANCE;
    }

}
