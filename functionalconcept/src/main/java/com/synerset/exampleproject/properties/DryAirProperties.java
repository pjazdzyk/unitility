package com.synerset.exampleproject.properties;

import com.synerset.exampleproject.utils.PhysicsConstants;
import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.density.InvalidDensity;
import com.synerset.unitsystem.density.KiloGramPerCubicMeter;
import com.synerset.unitsystem.dynamicviscosity.DynamicViscosity;
import com.synerset.unitsystem.dynamicviscosity.InvalidDynamicViscosity;
import com.synerset.unitsystem.dynamicviscosity.KiloGramPerMeterSecond;
import com.synerset.unitsystem.kinematicviscosity.InvalidKinematicViscosity;
import com.synerset.unitsystem.kinematicviscosity.KinematicViscosity;
import com.synerset.unitsystem.kinematicviscosity.SquareMeterPerSecond;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.specificenthalpy.InvalidSpecificEnthalpy;
import com.synerset.unitsystem.specificenthalpy.KiloJoulePerKiloGram;
import com.synerset.unitsystem.specificenthalpy.SpecificEnthalpy;
import com.synerset.unitsystem.specificheat.InvalidSpecificHeat;
import com.synerset.unitsystem.specificheat.KiloJoulePerKilogramKelvin;
import com.synerset.unitsystem.specificheat.SpecificHeat;
import com.synerset.unitsystem.temperature.Temperature;
import com.synerset.unitsystem.thermalconductivity.InvalidThermalConductivity;
import com.synerset.unitsystem.thermalconductivity.ThermalConductivity;
import com.synerset.unitsystem.thermalconductivity.WattPerMeterKelvin;
import com.synerset.exampleproject.utils.MathUtils;
import io.vavr.control.Either;

public final class DryAirProperties {

    private static final DryAirProperties INSTANCE = new DryAirProperties();

    private DryAirProperties() {
    }

    public Either<InvalidKinematicViscosity, SquareMeterPerSecond> kinematicViscosity(Temperature temp, Density density) {
        double densityVal = density.toKiloGramPerCubicMeter().getValue();
        return dynamicViscosity(temp)
                .mapLeft(l -> new InvalidKinematicViscosity())
                .flatMap(dynVis -> KinematicViscosity.squareMeterPerSecond(dynVis.getValue() / densityVal));
    }

    public Either<InvalidDynamicViscosity, KiloGramPerMeterSecond> dynamicViscosity(Temperature temp) {
        double tk = temp.toKelvin().getValue();
        double dynVis = (0.40401 + 0.074582 * tk - 5.7171 * Math.pow(10, -5)
                * Math.pow(tk, 2) + 2.9928 * Math.pow(10, -8)
                * Math.pow(tk, 3) - 6.2524 * Math.pow(10, -12)
                * Math.pow(tk, 4)) * Math.pow(10, -6);
        return DynamicViscosity.kiloGramPerMeterSecond(dynVis);
    }

    public Either<InvalidThermalConductivity, WattPerMeterKelvin> thermalConductivity(Temperature temp) {
        double ta = temp.toCelsius().getValue();
        double thermalCond = 2.43714 * Math.pow(10, -2)
                + 7.83035 * Math.pow(10, -5) * ta
                - 1.94021 * Math.pow(10, -8) * Math.pow(ta, 2)
                + 2.85943 * Math.pow(10, -12) * Math.pow(ta, 3)
                - 2.61420 * Math.pow(10, -14) * Math.pow(ta, 4);
        return ThermalConductivity.wattPerMeterKelvin(thermalCond);
    }

    public Either<InvalidSpecificEnthalpy, KiloJoulePerKiloGram> specificEnthalpy(Temperature temp) {
        return specificHeat(temp)
                .mapLeft(l -> new InvalidSpecificEnthalpy())
                .map(specHeat -> {
                    double specHeatValue = specHeat.toKiloJoulePerKilogramKelvin().getValue();
                    double tempValueInC = temp.toCelsius().getValue();
                    double specEnthalpyValue = specHeatValue * tempValueInC;
                    return SpecificEnthalpy.kiloJoulePerKiloGram(specEnthalpyValue);
                });
    }

    public Either<InvalidDensity, KiloGramPerCubicMeter> density(Temperature temp, Pressure press) {
        double tk = temp.toKelvin().getValue();
        double pa = press.toPascal().getValue();
        double density = pa / (PhysicsConstants.CST_DA_RG * tk);
        return Density.kiloGramPerCubicMeter(density);
    }

    public Either<InvalidSpecificHeat, KiloJoulePerKilogramKelvin> specificHeat(Temperature temp) {
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

        double specHeat = e * Math.pow(ta, 4)
                + d * Math.pow(ta, 3)
                + c * Math.pow(ta, 2)
                + b * ta
                + a;

        return SpecificHeat.kiloJoulePerKilogramKelvin(specHeat);
    }

    public static DryAirProperties getInstance() {
        return INSTANCE;
    }

}
