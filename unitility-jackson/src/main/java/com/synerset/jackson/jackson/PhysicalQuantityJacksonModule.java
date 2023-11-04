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

public class PhysicalQuantityJacksonModule extends SimpleModule {

    public PhysicalQuantityJacksonModule() {
        super("PhysicalQuantityJacksonModule");

        /* SERIALIZERS */
        JavaType type = TypeFactory.defaultInstance().constructParametricType(PhysicalQuantity.class, Unit.class);
        // Handles all PhysicalQuantity implementations
        addSerializer(new PhysicalQuantitySerializer(type));

        /* DESERIALIZERS */
        // Common
        addDeserializer(Angle.class, new PhysicalQuantityDeserializer<>(Angle.class));
        addDeserializer(Area.class, new PhysicalQuantityDeserializer<>(Area.class));
        addDeserializer(Distance.class, new PhysicalQuantityDeserializer<>(Distance.class));
        addDeserializer(Mass.class, new PhysicalQuantityDeserializer<>(Mass.class));
        addDeserializer(Velocity.class, new PhysicalQuantityDeserializer<>(Velocity.class));
        addDeserializer(Volume.class, new PhysicalQuantityDeserializer<>(Volume.class));
        // Dimensionless
        addDeserializer(BypassFactor.class, new PhysicalQuantityDeserializer<>(BypassFactor.class));
        addDeserializer(GrashofNumber.class, new PhysicalQuantityDeserializer<>(GrashofNumber.class));
        addDeserializer(PrandtlNumber.class, new PhysicalQuantityDeserializer<>(PrandtlNumber.class));
        addDeserializer(ReynoldsNumber.class, new PhysicalQuantityDeserializer<>(ReynoldsNumber.class));
        // Flows
        addDeserializer(MassFlow.class, new PhysicalQuantityDeserializer<>(MassFlow.class));
        addDeserializer(VolumetricFlow.class, new PhysicalQuantityDeserializer<>(VolumetricFlow.class));
        // Humidity
        addDeserializer(HumidityRatio.class, new PhysicalQuantityDeserializer<>(HumidityRatio.class));
        addDeserializer(RelativeHumidity.class, new PhysicalQuantityDeserializer<>(RelativeHumidity.class));
        // Mechanical
        addDeserializer(Force.class, new PhysicalQuantityDeserializer<>(Force.class));
        addDeserializer(Momentum.class, new PhysicalQuantityDeserializer<>(Momentum.class));
        addDeserializer(Torque.class, new PhysicalQuantityDeserializer<>(Torque.class));
        // Thermodynamic
        addDeserializer(Density.class, new PhysicalQuantityDeserializer<>(Density.class));
        addDeserializer(DynamicViscosity.class, new PhysicalQuantityDeserializer<>(DynamicViscosity.class));
        addDeserializer(Energy.class, new PhysicalQuantityDeserializer<>(Energy.class));
        addDeserializer(KinematicViscosity.class, new PhysicalQuantityDeserializer<>(KinematicViscosity.class));
        addDeserializer(Power.class, new PhysicalQuantityDeserializer<>(Power.class));
        addDeserializer(Pressure.class, new PhysicalQuantityDeserializer<>(Pressure.class));
        addDeserializer(SpecificEnthalpy.class, new PhysicalQuantityDeserializer<>(SpecificEnthalpy.class));
        addDeserializer(SpecificHeat.class, new PhysicalQuantityDeserializer<>(SpecificHeat.class));
        addDeserializer(Temperature.class, new PhysicalQuantityDeserializer<>(Temperature.class));
        addDeserializer(ThermalConductivity.class, new PhysicalQuantityDeserializer<>(ThermalConductivity.class));
        addDeserializer(ThermalDiffusivity.class, new PhysicalQuantityDeserializer<>(ThermalDiffusivity.class));
    }

}