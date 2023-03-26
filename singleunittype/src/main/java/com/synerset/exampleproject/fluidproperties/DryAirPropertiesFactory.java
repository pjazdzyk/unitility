package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.temperature.Temperature;
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

    public static DryAirPropertiesFactory ofDefaultEquations(){
        return new DryAirPropertiesFactory(DryAirPropertiesEquations.getInstance());
    }

    public static DryAirPropertiesFactory of(DryAirPropertiesEquations dryAirEquations){
        return new DryAirPropertiesFactory(dryAirEquations);
    }

}
