package com.synerset.jackson.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
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

public class PhysicalQuantityJacksonModule extends SimpleModule {

    public PhysicalQuantityJacksonModule(PhysicalQuantityParsingRegistry parsingRegistry) {
        super("PhysicalQuantityJacksonModule");

        /* SERIALIZERS */
        JavaType type = TypeFactory.defaultInstance().constructParametricType(PhysicalQuantity.class, Unit.class);
        // Handles all PhysicalQuantity implementations
        addSerializer(new PhysicalQuantitySerializer(type));

        /* DESERIALIZERS */
        // Common
        addDeserializer(Angle.class, new PhysicalQuantityDeserializer<>(Angle.class, parsingRegistry));
        addDeserializer(Area.class, new PhysicalQuantityDeserializer<>(Area.class, parsingRegistry));
        addDeserializer(Distance.class, new PhysicalQuantityDeserializer<>(Distance.class, parsingRegistry));
        addDeserializer(Mass.class, new PhysicalQuantityDeserializer<>(Mass.class, parsingRegistry));
        addDeserializer(Velocity.class, new PhysicalQuantityDeserializer<>(Velocity.class, parsingRegistry));
        addDeserializer(Volume.class, new PhysicalQuantityDeserializer<>(Volume.class, parsingRegistry));
        // Dimensionless
        addDeserializer(BypassFactor.class, new PhysicalQuantityDeserializer<>(BypassFactor.class, parsingRegistry));
        addDeserializer(GrashofNumber.class, new PhysicalQuantityDeserializer<>(GrashofNumber.class, parsingRegistry));
        addDeserializer(PrandtlNumber.class, new PhysicalQuantityDeserializer<>(PrandtlNumber.class, parsingRegistry));
        addDeserializer(ReynoldsNumber.class, new PhysicalQuantityDeserializer<>(ReynoldsNumber.class, parsingRegistry));
        // Flows
        addDeserializer(MassFlow.class, new PhysicalQuantityDeserializer<>(MassFlow.class, parsingRegistry));
        addDeserializer(VolumetricFlow.class, new PhysicalQuantityDeserializer<>(VolumetricFlow.class, parsingRegistry));
        // Humidity
        addDeserializer(HumidityRatio.class, new PhysicalQuantityDeserializer<>(HumidityRatio.class, parsingRegistry));
        addDeserializer(RelativeHumidity.class, new PhysicalQuantityDeserializer<>(RelativeHumidity.class, parsingRegistry));
        // Mechanical
        addDeserializer(Force.class, new PhysicalQuantityDeserializer<>(Force.class, parsingRegistry));
        addDeserializer(Momentum.class, new PhysicalQuantityDeserializer<>(Momentum.class, parsingRegistry));
        addDeserializer(Torque.class, new PhysicalQuantityDeserializer<>(Torque.class, parsingRegistry));
        // Thermodynamic
        addDeserializer(Density.class, new PhysicalQuantityDeserializer<>(Density.class, parsingRegistry));
        addDeserializer(DynamicViscosity.class, new PhysicalQuantityDeserializer<>(DynamicViscosity.class, parsingRegistry));
        addDeserializer(Energy.class, new PhysicalQuantityDeserializer<>(Energy.class, parsingRegistry));
        addDeserializer(KinematicViscosity.class, new PhysicalQuantityDeserializer<>(KinematicViscosity.class, parsingRegistry));
        addDeserializer(Power.class, new PhysicalQuantityDeserializer<>(Power.class, parsingRegistry));
        addDeserializer(Pressure.class, new PhysicalQuantityDeserializer<>(Pressure.class, parsingRegistry));
        addDeserializer(SpecificEnthalpy.class, new PhysicalQuantityDeserializer<>(SpecificEnthalpy.class, parsingRegistry));
        addDeserializer(SpecificHeat.class, new PhysicalQuantityDeserializer<>(SpecificHeat.class, parsingRegistry));
        addDeserializer(Temperature.class, new PhysicalQuantityDeserializer<>(Temperature.class, parsingRegistry));
        addDeserializer(ThermalConductivity.class, new PhysicalQuantityDeserializer<>(ThermalConductivity.class, parsingRegistry));
        addDeserializer(ThermalDiffusivity.class, new PhysicalQuantityDeserializer<>(ThermalDiffusivity.class, parsingRegistry));
    }

}