package com.synerset.unitility.quarkus.jakarta;

import com.synerset.jackson.exceptions.UnitSystemClassNotSupportedException;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.BypassFactor;
import com.synerset.unitility.unitsystem.dimensionless.GrashofNumber;
import com.synerset.unitility.unitsystem.dimensionless.PrandtlNumber;
import com.synerset.unitility.unitsystem.dimensionless.ReynoldsNumber;
import com.synerset.unitility.unitsystem.flows.MassFlow;
import com.synerset.unitility.unitsystem.flows.VolumetricFlow;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.mechanical.Force;
import com.synerset.unitility.unitsystem.mechanical.Momentum;
import com.synerset.unitility.unitsystem.mechanical.Torque;
import com.synerset.unitility.unitsystem.thermodynamic.*;
import com.synerset.unitility.unitsystem.utils.PhysicalQuantityParsingRegistry;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Provider
@Priority(1000)
@ApplicationScoped
public class PhysicalQuantityJakartaProvider implements ParamConverterProvider {

    private final Map<Class<?>, ParamConverter<?>> immutableConverterRegistry;

    public PhysicalQuantityJakartaProvider(PhysicalQuantityParsingRegistry parsingRegistry) {
        // Registry initialization
        Map<Class<?>, ParamConverter<?>> registry = new ConcurrentHashMap<>();
        // Common
        registry.put(Angle.class, new PhysicalQuantityParamJakartaConverter<>(Angle.class, parsingRegistry));
        registry.put(Area.class, new PhysicalQuantityParamJakartaConverter<>(Area.class, parsingRegistry));
        registry.put(Distance.class, new PhysicalQuantityParamJakartaConverter<>(Distance.class, parsingRegistry));
        registry.put(Mass.class, new PhysicalQuantityParamJakartaConverter<>(Mass.class, parsingRegistry));
        registry.put(Velocity.class, new PhysicalQuantityParamJakartaConverter<>(Velocity.class, parsingRegistry));
        registry.put(Volume.class, new PhysicalQuantityParamJakartaConverter<>(Volume.class, parsingRegistry));
        // Dimensionless
        registry.put(BypassFactor.class, new PhysicalQuantityParamJakartaConverter<>(BypassFactor.class, parsingRegistry));
        registry.put(GrashofNumber.class, new PhysicalQuantityParamJakartaConverter<>(GrashofNumber.class, parsingRegistry));
        registry.put(PrandtlNumber.class, new PhysicalQuantityParamJakartaConverter<>(PrandtlNumber.class, parsingRegistry));
        registry.put(ReynoldsNumber.class, new PhysicalQuantityParamJakartaConverter<>(ReynoldsNumber.class, parsingRegistry));
        // Flows
        registry.put(MassFlow.class, new PhysicalQuantityParamJakartaConverter<>(MassFlow.class, parsingRegistry));
        registry.put(VolumetricFlow.class, new PhysicalQuantityParamJakartaConverter<>(VolumetricFlow.class, parsingRegistry));
        // Humidity
        registry.put(HumidityRatio.class, new PhysicalQuantityParamJakartaConverter<>(HumidityRatio.class, parsingRegistry));
        registry.put(RelativeHumidity.class, new PhysicalQuantityParamJakartaConverter<>(RelativeHumidity.class, parsingRegistry));
        // Mechanical
        registry.put(Force.class, new PhysicalQuantityParamJakartaConverter<>(Force.class, parsingRegistry));
        registry.put(Momentum.class, new PhysicalQuantityParamJakartaConverter<>(Momentum.class, parsingRegistry));
        registry.put(Torque.class, new PhysicalQuantityParamJakartaConverter<>(Torque.class, parsingRegistry));
        // Thermodynamic
        registry.put(Density.class, new PhysicalQuantityParamJakartaConverter<>(Density.class, parsingRegistry));
        registry.put(DynamicViscosity.class, new PhysicalQuantityParamJakartaConverter<>(DynamicViscosity.class, parsingRegistry));
        registry.put(Energy.class, new PhysicalQuantityParamJakartaConverter<>(Energy.class, parsingRegistry));
        registry.put(KinematicViscosity.class, new PhysicalQuantityParamJakartaConverter<>(KinematicViscosity.class, parsingRegistry));
        registry.put(Power.class, new PhysicalQuantityParamJakartaConverter<>(Power.class, parsingRegistry));
        registry.put(Pressure.class, new PhysicalQuantityParamJakartaConverter<>(Pressure.class, parsingRegistry));
        registry.put(SpecificEnthalpy.class, new PhysicalQuantityParamJakartaConverter<>(SpecificEnthalpy.class, parsingRegistry));
        registry.put(SpecificHeat.class, new PhysicalQuantityParamJakartaConverter<>(SpecificHeat.class, parsingRegistry));
        registry.put(Temperature.class, new PhysicalQuantityParamJakartaConverter<>(Temperature.class, parsingRegistry));
        registry.put(ThermalConductivity.class, new PhysicalQuantityParamJakartaConverter<>(ThermalConductivity.class, parsingRegistry));
        registry.put(ThermalDiffusivity.class, new PhysicalQuantityParamJakartaConverter<>(ThermalDiffusivity.class, parsingRegistry));
        // Create immutable registry
        this.immutableConverterRegistry = Collections.unmodifiableMap(registry);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> targetClass, Type parametrizedType, Annotation[] annotations) {
        validateTargetClass(targetClass);
        return (ParamConverter<T>) immutableConverterRegistry.get(targetClass);
    }


    public void validateTargetClass(Class<?> targetClass) {
        if (!PhysicalQuantity.class.isAssignableFrom(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Target class does not implements interface: " +
                    PhysicalQuantity.class + ". Target class: " + targetClass);
        }
        if (!immutableConverterRegistry.containsKey(targetClass)) {
            throw new UnitSystemClassNotSupportedException("Target class not found in converters registry. " +
                    "Target class: " + targetClass);
        }
    }

}