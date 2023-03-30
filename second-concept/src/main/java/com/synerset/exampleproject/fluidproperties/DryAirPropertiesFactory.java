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

    public Either<InvalidQuantity, Density> density(Temperature temp, Pressure press) {
        LOGGER.debug("Invoking density() with params: Temperature = {}, Pressure = {}", temp, press);
        return FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT)
                .map(tx -> equations.density((Temperature) tx, press))
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Density could not be created for arguments: " + temp + ", " + press);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg, temp, press));
                });
    }

    Either<InvalidQuantity, DynamicViscosity> dynamicViscosity(Temperature temp) {
        LOGGER.debug("Invoking dynamicViscosity() with params: Temperature = {}", temp);
        return FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT)
                .map(tx -> equations.dynamicViscosity((Temperature) tx))
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Dynamic viscosity could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg));
                });
    }

    Either<InvalidQuantity, ThermalConductivity> thermalConductivity(Temperature temp) {
        LOGGER.debug("Invoking thermalConductivity() with params: Temperature = {}", temp);
        return FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT)
                .map(tx -> equations.thermalConductivity((Temperature) tx))
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Thermal conductivity could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg));
                });
    }

    Either<InvalidQuantity, SpecificHeat> specificHeat(Temperature temp) {
        LOGGER.debug("Invoking specificHeat() with params: Temperature = {}", temp);
        return FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT)
                .map(tx -> equations.specificHeat((Temperature) tx))
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Specific heat could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg));
                });
    }

    Either<InvalidQuantity, SpecificEnthalpy> specificEnthalpy(Temperature temp) {
        LOGGER.debug("Invoking specificEnthalpy() with params: Temperature = {}", temp);
        return FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT)
                .map(tx -> equations.specificEnthalpy((Temperature) tx))
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Specific enthalpy could not be created for arguments: " + temp);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg));
                });
    }

    public Either<InvalidQuantity, KinematicViscosity> kinematicViscosity(Temperature temp, Density density) {
        LOGGER.debug("Invoking kinematicViscosity() with params: Temperature = {}, Density = {}", temp, density);
        return Validation.combine(
                        FluidValidators.requireNotExceedMinimalLimit(temp, Temperature.PHYSICAL_MIN_LIMIT),
                        FluidValidators.requirePositiveValue(density)
                ).ap((t, p) -> equations.kinematicViscosity(temp, density))
                .mapError(FluidValidators::combineSeqOfInvalids)
                .toEither()
                .mapLeft(combinedInvalid -> {
                    String errorMsg = String.format("Kinematic Viscosity could not be created for arguments: " + temp + ", " + density);
                    LOGGER.warn(errorMsg);
                    return FluidValidators.combineInvalids(combinedInvalid, new InvalidQuantity(errorMsg));
                });
    }

    public static DryAirPropertiesFactory ofDefaultEquations() {
        return new DryAirPropertiesFactory(DryAirPropertiesEquations.getInstance());
    }

    public static DryAirPropertiesFactory of(DryAirPropertiesEquations dryAirEquations) {
        return new DryAirPropertiesFactory(dryAirEquations);
    }

}
