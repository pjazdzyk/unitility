package com.synerset.exampleproject.fluidproperties;

import com.synerset.unitsystem.density.Density;
import com.synerset.unitsystem.pressure.Pressure;
import com.synerset.unitsystem.temperature.Temperature;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DryAirProperties {

    private static final Logger LOGGER = LogManager.getLogger(DryAirProperties.class.getSimpleName());
    private final DryAirPropertiesEquations equations;

    private DryAirProperties(DryAirPropertiesEquations equations) {
        this.equations = equations;
    }

    public Either<InvalidProperty, Density> density(Temperature temp, Pressure press) {
        return Validation.combine(
                        FluidValidators.validateTemperature(temp),
                        FluidValidators.requireNonNull(press, "Pressure in method: density()")
                ).ap((t, p) -> equations.density(temp, press))
                .mapError(errors -> {
                    LOGGER.warn("Invalid arguments: Temperature = {}, Pressure = {}", temp, press);
                    return FluidValidators.combineInvalids(errors);
                })
                .toEither();
    }

}
