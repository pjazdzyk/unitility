package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.dynamicviscosity.DynamicViscosity;
import com.synerset.unitsystem.kinematicviscosity.KinematicViscosity;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.specificenthalpy.SpecificEnthalpy;
import com.synerset.unitsystem.specificheat.SpecificHeat;
import com.synerset.unitsystem.temperature.Temperature;
import com.synerset.unitsystem.thermalconductivity.ThermalConductivity;
import io.vavr.control.Either;
import io.vavr.control.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DryAirPropertiesFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DryAirPropertiesFactory.class);
    private final DryAirPropertiesEquations equations;

    private DryAirPropertiesFactory(DryAirPropertiesEquations equations) {
        this.equations = equations;
    }

    public Either<InvalidProperty, Density> density(Temperature temp, Pressure press) {
        LOGGER.debug("Invoking density() with params: Temperature = {}, Pressure = {}", temp, press);
        return Validation.combine(
                        FluidValidators.validateTemperature(temp),
                        FluidValidators.requireNonNull(press, "Source: Pressure argument in density() method.")
                ).ap((t, p) -> equations.density(temp, press))
                .mapError(FluidValidators::combineSeqOfInvalids)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Density could not be created for arguments: " + temp + ", " + press);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    Either<InvalidProperty, DynamicViscosity> dynamicViscosity(Temperature temp) {
        LOGGER.debug("Invoking dynamicViscosity() with params: Temperature = {}", temp);
        return FluidValidators.validateTemperature(temp)
                .map(equations::dynamicViscosity)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Dynamic viscosity could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    Either<InvalidProperty, ThermalConductivity> thermalConductivity(Temperature temp) {
        LOGGER.debug("Invoking thermalConductivity() with params: Temperature = {}", temp);
        return FluidValidators.validateTemperature(temp)
                .map(equations::thermalConductivity)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Thermal conductivity could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    Either<InvalidProperty, SpecificHeat> specificHeat(Temperature temp) {
        LOGGER.debug("Invoking specificHeat() with params: Temperature = {}", temp);
        return FluidValidators.validateTemperature(temp)
                .map(equations::specificHeat)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Specific heat could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    Either<InvalidProperty, SpecificEnthalpy> specificEnthalpy(Temperature temp) {
        LOGGER.debug("Invoking specificEnthalpy() with params: Temperature = {}", temp);
        return FluidValidators.validateTemperature(temp)
                .map(equations::specificEnthalpy)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Specific enthalpy could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    public Either<InvalidProperty, KinematicViscosity> kinematicViscosity(Temperature temp, Density density) {
        LOGGER.debug("Invoking kinematicViscosity() with params: Temperature = {}, Density = {}", temp, density);
        return Validation.combine(
                        FluidValidators.validateTemperature(temp),
                        FluidValidators.requireNonNull(density, "Source: Density argument in kinematicViscosity() method.")
                ).ap((t, p) -> equations.kinematicViscosity(temp, density))
                .mapError(FluidValidators::combineSeqOfInvalids)
                .toEither()
                .mapLeft(invalid -> {
                    String errorMsg = String.format("Kinematic Viscosity could not be created for arguments: " + temp + ", " + density);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(invalid, new InvalidProperty(errorMsg));
                });
    }

    public static DryAirPropertiesFactory ofDefaultEquations() {
        return new DryAirPropertiesFactory(DryAirPropertiesEquations.getInstance());
    }

    public static DryAirPropertiesFactory of(DryAirPropertiesEquations dryAirEquations) {
        return new DryAirPropertiesFactory(dryAirEquations);
    }

}
