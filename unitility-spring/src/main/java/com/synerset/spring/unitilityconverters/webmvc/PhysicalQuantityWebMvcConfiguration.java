package com.synerset.spring.unitilityconverters.webmvc;

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
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
class PhysicalQuantityWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Common
        registry.addConverter(String.class, Angle.class, new PhysicalQuantityWebMvcConverter<>(Angle.class));
        registry.addConverter(String.class, Area.class, new PhysicalQuantityWebMvcConverter<>(Area.class));
        registry.addConverter(String.class, Distance.class, new PhysicalQuantityWebMvcConverter<>(Distance.class));
        registry.addConverter(String.class, Mass.class, new PhysicalQuantityWebMvcConverter<>(Mass.class));
        registry.addConverter(String.class, Velocity.class, new PhysicalQuantityWebMvcConverter<>(Velocity.class));
        registry.addConverter(String.class, Volume.class, new PhysicalQuantityWebMvcConverter<>(Volume.class));
        // DimensionlessString.class,
        registry.addConverter(String.class, BypassFactor.class, new PhysicalQuantityWebMvcConverter<>(BypassFactor.class));
        registry.addConverter(String.class, GrashofNumber.class, new PhysicalQuantityWebMvcConverter<>(GrashofNumber.class));
        registry.addConverter(String.class, PrandtlNumber.class, new PhysicalQuantityWebMvcConverter<>(PrandtlNumber.class));
        registry.addConverter(String.class, ReynoldsNumber.class, new PhysicalQuantityWebMvcConverter<>(ReynoldsNumber.class));
        // FlowsString.class,
        registry.addConverter(String.class, MassFlow.class, new PhysicalQuantityWebMvcConverter<>(MassFlow.class));
        registry.addConverter(String.class, VolumetricFlow.class, new PhysicalQuantityWebMvcConverter<>(VolumetricFlow.class));
        // HumidityString.class,
        registry.addConverter(String.class, HumidityRatio.class, new PhysicalQuantityWebMvcConverter<>(HumidityRatio.class));
        registry.addConverter(String.class, RelativeHumidity.class, new PhysicalQuantityWebMvcConverter<>(RelativeHumidity.class));
        // MechanicalString.class,
        registry.addConverter(String.class, Force.class, new PhysicalQuantityWebMvcConverter<>(Force.class));
        registry.addConverter(String.class, Momentum.class, new PhysicalQuantityWebMvcConverter<>(Momentum.class));
        registry.addConverter(String.class, Torque.class, new PhysicalQuantityWebMvcConverter<>(Torque.class));
        // ThermodynamicString.class,
        registry.addConverter(String.class, Density.class, new PhysicalQuantityWebMvcConverter<>(Density.class));
        registry.addConverter(String.class, DynamicViscosity.class, new PhysicalQuantityWebMvcConverter<>(DynamicViscosity.class));
        registry.addConverter(String.class, Energy.class, new PhysicalQuantityWebMvcConverter<>(Energy.class));
        registry.addConverter(String.class, KinematicViscosity.class, new PhysicalQuantityWebMvcConverter<>(KinematicViscosity.class));
        registry.addConverter(String.class, Power.class, new PhysicalQuantityWebMvcConverter<>(Power.class));
        registry.addConverter(String.class, Pressure.class, new PhysicalQuantityWebMvcConverter<>(Pressure.class));
        registry.addConverter(String.class, SpecificEnthalpy.class, new PhysicalQuantityWebMvcConverter<>(SpecificEnthalpy.class));
        registry.addConverter(String.class, SpecificHeat.class, new PhysicalQuantityWebMvcConverter<>(SpecificHeat.class));
        registry.addConverter(String.class, Temperature.class, new PhysicalQuantityWebMvcConverter<>(Temperature.class));
        registry.addConverter(String.class, ThermalConductivity.class, new PhysicalQuantityWebMvcConverter<>(ThermalConductivity.class));
        registry.addConverter(String.class, ThermalDiffusivity.class, new PhysicalQuantityWebMvcConverter<>(ThermalDiffusivity.class));
    }

}