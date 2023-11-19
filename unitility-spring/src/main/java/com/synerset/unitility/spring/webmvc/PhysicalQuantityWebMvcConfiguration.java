package com.synerset.unitility.spring.webmvc;

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
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
class PhysicalQuantityWebMvcConfiguration implements WebMvcConfigurer {

    private final PhysicalQuantityParsingRegistry parsingFactory;

    PhysicalQuantityWebMvcConfiguration(PhysicalQuantityParsingRegistry parsingFactory) {
        this.parsingFactory = parsingFactory;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Common
        registry.addConverter(String.class, Angle.class, new PhysicalQuantityWebMvcConverter<>(Angle.class, parsingFactory));
        registry.addConverter(String.class, Area.class, new PhysicalQuantityWebMvcConverter<>(Area.class, parsingFactory));
        registry.addConverter(String.class, Distance.class, new PhysicalQuantityWebMvcConverter<>(Distance.class, parsingFactory));
        registry.addConverter(String.class, Mass.class, new PhysicalQuantityWebMvcConverter<>(Mass.class, parsingFactory));
        registry.addConverter(String.class, Velocity.class, new PhysicalQuantityWebMvcConverter<>(Velocity.class, parsingFactory));
        registry.addConverter(String.class, Volume.class, new PhysicalQuantityWebMvcConverter<>(Volume.class, parsingFactory));
        // DimensionlessString.class,
        registry.addConverter(String.class, BypassFactor.class, new PhysicalQuantityWebMvcConverter<>(BypassFactor.class, parsingFactory));
        registry.addConverter(String.class, GrashofNumber.class, new PhysicalQuantityWebMvcConverter<>(GrashofNumber.class, parsingFactory));
        registry.addConverter(String.class, PrandtlNumber.class, new PhysicalQuantityWebMvcConverter<>(PrandtlNumber.class, parsingFactory));
        registry.addConverter(String.class, ReynoldsNumber.class, new PhysicalQuantityWebMvcConverter<>(ReynoldsNumber.class, parsingFactory));
        // FlowsString.class,
        registry.addConverter(String.class, MassFlow.class, new PhysicalQuantityWebMvcConverter<>(MassFlow.class, parsingFactory));
        registry.addConverter(String.class, VolumetricFlow.class, new PhysicalQuantityWebMvcConverter<>(VolumetricFlow.class, parsingFactory));
        // HumidityString.class,
        registry.addConverter(String.class, HumidityRatio.class, new PhysicalQuantityWebMvcConverter<>(HumidityRatio.class, parsingFactory));
        registry.addConverter(String.class, RelativeHumidity.class, new PhysicalQuantityWebMvcConverter<>(RelativeHumidity.class, parsingFactory));
        // MechanicalString.class,
        registry.addConverter(String.class, Force.class, new PhysicalQuantityWebMvcConverter<>(Force.class, parsingFactory));
        registry.addConverter(String.class, Momentum.class, new PhysicalQuantityWebMvcConverter<>(Momentum.class, parsingFactory));
        registry.addConverter(String.class, Torque.class, new PhysicalQuantityWebMvcConverter<>(Torque.class, parsingFactory));
        // ThermodynamicString.class,
        registry.addConverter(String.class, Density.class, new PhysicalQuantityWebMvcConverter<>(Density.class, parsingFactory));
        registry.addConverter(String.class, DynamicViscosity.class, new PhysicalQuantityWebMvcConverter<>(DynamicViscosity.class, parsingFactory));
        registry.addConverter(String.class, Energy.class, new PhysicalQuantityWebMvcConverter<>(Energy.class, parsingFactory));
        registry.addConverter(String.class, KinematicViscosity.class, new PhysicalQuantityWebMvcConverter<>(KinematicViscosity.class, parsingFactory));
        registry.addConverter(String.class, Power.class, new PhysicalQuantityWebMvcConverter<>(Power.class, parsingFactory));
        registry.addConverter(String.class, Pressure.class, new PhysicalQuantityWebMvcConverter<>(Pressure.class, parsingFactory));
        registry.addConverter(String.class, SpecificEnthalpy.class, new PhysicalQuantityWebMvcConverter<>(SpecificEnthalpy.class, parsingFactory));
        registry.addConverter(String.class, SpecificHeat.class, new PhysicalQuantityWebMvcConverter<>(SpecificHeat.class, parsingFactory));
        registry.addConverter(String.class, Temperature.class, new PhysicalQuantityWebMvcConverter<>(Temperature.class, parsingFactory));
        registry.addConverter(String.class, ThermalConductivity.class, new PhysicalQuantityWebMvcConverter<>(ThermalConductivity.class, parsingFactory));
        registry.addConverter(String.class, ThermalDiffusivity.class, new PhysicalQuantityWebMvcConverter<>(ThermalDiffusivity.class, parsingFactory));
    }

}