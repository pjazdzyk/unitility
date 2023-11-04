package com.synerset.quarkus.jakarta;

import com.synerset.jackson.exceptions.ModulesClassNotSupportedException;
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
import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Provider
public class PhysicalQuantityJakartaProvider implements ParamConverterProvider {

    private final Map<Class<?>, ParamConverter<?>> convertersByClass = new HashMap<>();

    public PhysicalQuantityJakartaProvider() {
        // Common
        convertersByClass.put(Angle.class, new PhysicalQuantityParamJakartaConverter<>(Angle.class));
        convertersByClass.put(Area.class, new PhysicalQuantityParamJakartaConverter<>(Area.class));
        convertersByClass.put(Distance.class, new PhysicalQuantityParamJakartaConverter<>(Distance.class));
        convertersByClass.put(Mass.class, new PhysicalQuantityParamJakartaConverter<>(Mass.class));
        convertersByClass.put(Velocity.class, new PhysicalQuantityParamJakartaConverter<>(Velocity.class));
        convertersByClass.put(Volume.class, new PhysicalQuantityParamJakartaConverter<>(Volume.class));
        // Dimensionless
        convertersByClass.put(BypassFactor.class, new PhysicalQuantityParamJakartaConverter<>(BypassFactor.class));
        convertersByClass.put(GrashofNumber.class, new PhysicalQuantityParamJakartaConverter<>(GrashofNumber.class));
        convertersByClass.put(PrandtlNumber.class, new PhysicalQuantityParamJakartaConverter<>(PrandtlNumber.class));
        convertersByClass.put(ReynoldsNumber.class, new PhysicalQuantityParamJakartaConverter<>(ReynoldsNumber.class));
        // Flows
        convertersByClass.put(MassFlow.class, new PhysicalQuantityParamJakartaConverter<>(MassFlow.class));
        convertersByClass.put(VolumetricFlow.class, new PhysicalQuantityParamJakartaConverter<>(VolumetricFlow.class));
        // Humidity
        convertersByClass.put(HumidityRatio.class, new PhysicalQuantityParamJakartaConverter<>(HumidityRatio.class));
        convertersByClass.put(RelativeHumidity.class, new PhysicalQuantityParamJakartaConverter<>(RelativeHumidity.class));
        // Mechanical
        convertersByClass.put(Force.class, new PhysicalQuantityParamJakartaConverter<>(Force.class));
        convertersByClass.put(Momentum.class, new PhysicalQuantityParamJakartaConverter<>(Momentum.class));
        convertersByClass.put(Torque.class, new PhysicalQuantityParamJakartaConverter<>(Torque.class));
        // Thermodynamic
        convertersByClass.put(Density.class, new PhysicalQuantityParamJakartaConverter<>(Density.class));
        convertersByClass.put(DynamicViscosity.class, new PhysicalQuantityParamJakartaConverter<>(DynamicViscosity.class));
        convertersByClass.put(Energy.class, new PhysicalQuantityParamJakartaConverter<>(Energy.class));
        convertersByClass.put(KinematicViscosity.class, new PhysicalQuantityParamJakartaConverter<>(KinematicViscosity.class));
        convertersByClass.put(Power.class, new PhysicalQuantityParamJakartaConverter<>(Power.class));
        convertersByClass.put(Pressure.class, new PhysicalQuantityParamJakartaConverter<>(Pressure.class));
        convertersByClass.put(SpecificEnthalpy.class, new PhysicalQuantityParamJakartaConverter<>(SpecificEnthalpy.class));
        convertersByClass.put(SpecificHeat.class, new PhysicalQuantityParamJakartaConverter<>(SpecificHeat.class));
        convertersByClass.put(Temperature.class, new PhysicalQuantityParamJakartaConverter<>(Temperature.class));
        convertersByClass.put(ThermalConductivity.class, new PhysicalQuantityParamJakartaConverter<>(ThermalConductivity.class));
        convertersByClass.put(ThermalDiffusivity.class, new PhysicalQuantityParamJakartaConverter<>(ThermalDiffusivity.class));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(Class<T> targetClass, Type parametrizedType, Annotation[] annotations) {
        if (!PhysicalQuantity.class.isAssignableFrom(targetClass)) {
            throw new ModulesClassNotSupportedException("Target class does not implements interface: " + PhysicalQuantity.class +
                    ". Target class: " + targetClass);
        }
        if (!convertersByClass.containsKey(targetClass)) {
            throw new ModulesClassNotSupportedException("Target class not found in converters registry. " +
                    "Target class: " + targetClass);
        }
        return (ParamConverter<T>) convertersByClass.get(targetClass);
    }

}