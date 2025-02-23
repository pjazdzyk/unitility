package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.dimensionless.*;
import com.synerset.unitility.unitsystem.flow.MassFlow;
import com.synerset.unitility.unitsystem.flow.MassFlowUnits;
import com.synerset.unitility.unitsystem.flow.VolumetricFlow;
import com.synerset.unitility.unitsystem.flow.VolumetricFlowUnits;
import com.synerset.unitility.unitsystem.geographic.Bearing;
import com.synerset.unitility.unitsystem.geographic.GeoDistance;
import com.synerset.unitility.unitsystem.geographic.Latitude;
import com.synerset.unitility.unitsystem.geographic.Longitude;
import com.synerset.unitility.unitsystem.humidity.HumidityRatio;
import com.synerset.unitility.unitsystem.humidity.HumidityRatioUnits;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidity;
import com.synerset.unitility.unitsystem.humidity.RelativeHumidityUnits;
import com.synerset.unitility.unitsystem.mechanical.*;
import com.synerset.unitility.unitsystem.thermodynamic.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * The {@link SupportedQuantitiesRegistry} class provides a registry for physical quantities and their supported units.
 * It allows retrieval of unit information for various physical quantities that are registered within the system.
 */
public class SupportedQuantitiesRegistry {

    private static final SupportedQuantitiesRegistry UNIT_REGISTRY = new SupportedQuantitiesRegistry();

    private final Map<Class<?>, Supplier<List<? extends Unit>>> immutableRegistry;

    private SupportedQuantitiesRegistry() {
        // Initializing immutable registry
        this.immutableRegistry = Map.ofEntries(
                // Common
                Map.entry(Angle.class, () -> Arrays.asList(AngleUnits.values())),
                Map.entry(Area.class, () -> Arrays.asList(AreaUnits.values())),
                Map.entry(Distance.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Length.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Width.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Height.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Diameter.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Perimeter.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Mass.class, () -> Arrays.asList(MassUnits.values())),
                Map.entry(Velocity.class, () -> Arrays.asList(VelocityUnits.values())),
                Map.entry(Volume.class, () -> Arrays.asList(VolumeUnits.values())),
                Map.entry(Ratio.class, () -> Arrays.asList(RatioUnits.values())),
                // Dimensionless
                Map.entry(BypassFactor.class, Collections::emptyList),
                Map.entry(GrashofNumber.class, Collections::emptyList),
                Map.entry(PrandtlNumber.class, Collections::emptyList),
                Map.entry(ReynoldsNumber.class, Collections::emptyList),
                Map.entry(FrictionFactor.class, Collections::emptyList),
                // Flows
                Map.entry(MassFlow.class, () -> Arrays.asList(MassFlowUnits.values())),
                Map.entry(VolumetricFlow.class, () -> Arrays.asList(VolumetricFlowUnits.values())),
                // Humidity
                Map.entry(HumidityRatio.class, () -> Arrays.asList(HumidityRatioUnits.values())),
                Map.entry(RelativeHumidity.class, () -> Arrays.asList(RelativeHumidityUnits.values())),
                // Mechanical
                Map.entry(Force.class, () -> Arrays.asList(ForceUnits.values())),
                Map.entry(Momentum.class, () -> Arrays.asList(MomentumUnits.values())),
                Map.entry(Torque.class, () -> Arrays.asList(TorqueUnits.values())),
                // Thermodynamic
                Map.entry(Density.class, () -> Arrays.asList(DensityUnits.values())),
                Map.entry(DynamicViscosity.class, () -> Arrays.asList(DynamicViscosityUnits.values())),
                Map.entry(Energy.class, () -> Arrays.asList(EnergyUnits.values())),
                Map.entry(KinematicViscosity.class, () -> Arrays.asList(KinematicViscosityUnits.values())),
                Map.entry(Power.class, () -> Arrays.asList(PowerUnits.values())),
                Map.entry(Pressure.class, () -> Arrays.asList(PressureUnits.values())),
                Map.entry(SpecificEnthalpy.class, () -> Arrays.asList(SpecificEnthalpyUnits.values())),
                Map.entry(SpecificHeat.class, () -> Arrays.asList(SpecificHeatUnits.values())),
                Map.entry(Temperature.class, () -> Arrays.asList(TemperatureUnits.values())),
                Map.entry(ThermalConductivity.class, () -> Arrays.asList(ThermalConductivityUnits.values())),
                Map.entry(ThermalDiffusivity.class, () -> Arrays.asList(ThermalDiffusivityUnits.values())),
                // Geographic
                Map.entry(Latitude.class, () -> Arrays.asList(AngleUnits.values())),
                Map.entry(Longitude.class, () -> Arrays.asList(AngleUnits.values())),
                Map.entry(GeoDistance.class, () -> Arrays.asList(DistanceUnits.values())),
                Map.entry(Bearing.class, () -> Arrays.asList(AngleUnits.values()))
        );
    }

    /**
     * Retrieves a set of {@link PhysicalQuantityInfo}, representing supported registered physical quantities and associated
     * units including their symbols.
     *
     * @return A set {@link PhysicalQuantityInfo} representing supported physical quantities.
     */
    public Set<PhysicalQuantityInfo> findAllSupportedQuantities() {
        return immutableRegistry.entrySet().stream()
                .map(entry -> {
                    Class<?> quantityClass = entry.getKey();
                    List<PhysicalUnitInfo> supportedUnits = entry.getValue().get().stream()
                            .map(unit -> {
                                String name = getEnumName(unit);
                                String symbol = unit.getSymbol();
                                return new PhysicalUnitInfo(name, symbol);
                            })
                            .toList();
                    return new PhysicalQuantityInfo(quantityClass, supportedUnits);
                })
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves {@link PhysicalQuantityInfo} for a specific class.
     *
     * @param clazz The class of the physical quantity to retrieve.
     * @return The {@link PhysicalQuantityInfo} for the given class, or an empty Optional if not found.
     */
    public Optional<PhysicalQuantityInfo> findQuantityInfoByClass(Class<?> clazz) {
        return Optional.ofNullable(immutableRegistry.get(clazz))
                .map(supplier -> {
                    List<PhysicalUnitInfo> supportedUnits = supplier.get().stream()
                            .map(unit -> {
                                String name = getEnumName(unit);
                                String symbol = unit.getSymbol();
                                return new PhysicalUnitInfo(name, symbol);
                            })
                            .toList();
                    return new PhysicalQuantityInfo(clazz, supportedUnits);
                });
    }

    /**
     * Retrieves a set of all registered physical quantity classes.
     *
     * @param <U> The type of unit associated with the physical quantity.
     * @param <Q> The type of physical quantity.
     * @return A set containing all registered physical quantity classes.
     */
    @SuppressWarnings("unchecked")
    public <U extends Unit, Q extends PhysicalQuantity<U>> Set<Class<Q>> findAllRegisteredClasses() {
        Set<Class<Q>> quantityClasses = new HashSet<>();
        immutableRegistry.keySet().forEach(quantityClass -> quantityClasses.add((Class<Q>) quantityClass));
        return quantityClasses;
    }

    private String getEnumName(Unit unit) {
        // Check if the unit is an instance of Enum
        if (unit instanceof Enum<?>) {
            return ((Enum<?>) unit).name();
        }
        // Handle case where unit is not an Enum
        return "Unknown";
    }

    public static SupportedQuantitiesRegistry getInstance() {
        return UNIT_REGISTRY;
    }

}