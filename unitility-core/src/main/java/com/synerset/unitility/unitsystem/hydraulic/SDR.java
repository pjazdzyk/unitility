package com.synerset.unitility.unitsystem.hydraulic;

import com.synerset.unitility.unitsystem.CalculableQuantity;
import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.common.*;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.Objects;

/**
 * Represents a Standard Dimension Ratio (SDR), a dimensionless quantity used to define
 * the ratio between the outer diameter and wall thickness of a pipe or conduit.
 * <p>
 * SDR is defined as:
 * <pre>{@code
 * SDR = D / s
 * }</pre>
 * where:
 * <ul>
 *     <li>{@code D} – outer diameter</li>
 *     <li>{@code s} – wall thickness</li>
 * </ul>
 *
 * <p>
 * When an instance is created using the constructor, the given SDR value is automatically
 * rounded **up to one decimal place** to match typical industry standards. For example,
 * a raw input value of {@code 27.24} would be rounded to {@code 27.3}.
 * </p>
 *
 * <p><strong>Note:</strong> This class assumes that catalog SDR values may use standard rounding rules,
 * and thus the internal base value reflects a normalized, rounded version.</p>
 *
 */
public class SDR implements CalculableQuantity<RatioUnit, SDR> {

    private final double value;
    private final double baseValue;
    private final RatioUnit unitType;

    public SDR(double value, RatioUnit unitType) {
        this.value = value;
        if (unitType == null) {
            unitType = RatioUnits.DECIMAL;
        }
        this.unitType = unitType;
        double sdrSpecificCeilValue = Math.ceil(value * 10) / 10;
        this.baseValue = unitType.toValueInBaseUnit(sdrSpecificCeilValue);
    }

    // Static factory methods
    public static SDR of(double value, RatioUnit unit) {
        return new SDR(value, unit);
    }

    public static SDR of(double value, String unitSymbol) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(unitSymbol);
        return new SDR(value, resolvedUnit);
    }

    public static SDR of(double value) {
        return new SDR(value, RatioUnits.DECIMAL);
    }

    public static SDR from(double outerDiameter, double nominalMinWallThickness) {
        if (nominalMinWallThickness == 0.0) {
            throw new UnitSystemArgumentException("Second quantity at base unit cannot be 0 to calculate actual SDR. Second value: " + nominalMinWallThickness);
        }
        return SDR.of(outerDiameter / nominalMinWallThickness);
    }

    public static <Q extends Distance> SDR from(Diameter outerDiameter, Q nominalWallThickness) {
        if (outerDiameter == null || nominalWallThickness == null) {
            throw new UnitSystemArgumentException("Both nominal outer diameter and nominal wall thickness must be provided to calculate actual SDR.");
        }
        if (nominalWallThickness.getValue() <= 0.0) {
            throw new UnitSystemArgumentException("Nominal wall thickness cannot be 0 or negative to calculate actual SDR. Nominal wall thickness: " + nominalWallThickness);
        }
        return SDR.of(
                outerDiameter.getBaseValue() / nominalWallThickness.getBaseValue()
        );
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    /**
     * Calculates the nominal outer diameter based on the SDR value and provided wall thickness.
     *
     * @param nominalWallThickness The nominal wall thickness (must be > 0)
     * @return Calculated outer diameter as a {@link Diameter}
     * @throws UnitSystemArgumentException if wall thickness is null or non-positive
     *
     * @param <U> Distance unit (e.g., millimeter, meter)
     * @param <Q> Quantity type extending {@link PhysicalQuantity}
     *
     * @apiNote The result is computed based on the formula: {@code D = SDR * s}.
     *          In real-world catalogs, actual wall thicknesses are often rounded, which means
     *          the calculated diameter may slightly differ from standard dimensions.
     */
    public <U extends DistanceUnit, Q extends PhysicalQuantity<U>> Diameter getNominalOuterDiameter(Q nominalWallThickness) {
        if (nominalWallThickness == null) {
            throw new UnitSystemArgumentException("Wall thickness must be provided to calculate outer diameter from SDR.");
        }
        if (nominalWallThickness.getValue() <= 0.0) {
            throw new UnitSystemArgumentException("Nominal wall thickness cannot be 0 or negative to calculate outer diameter from SDR. Nominal wall thickness: " + nominalWallThickness);
        }
        double diameterValueInBaseUnit = this.multiply(nominalWallThickness);
        return Diameter.of(diameterValueInBaseUnit, DistanceUnits.METER);
    }

    /**
     * Calculates the nominal minimum wall thickness based on SDR and provided outer diameter.
     *
     * @param outerDiameter The outer diameter (must be > 0)
     * @return Calculated wall thickness as {@link Height}
     * @throws UnitSystemArgumentException if outer diameter is null or non-positive
     *
     * @param <U> Distance unit (e.g., millimeter, meter)
     * @param <Q> Quantity type extending {@link PhysicalQuantity}
     *
     * @apiNote The result is computed using: {@code s = D / SDR}.
     *          In actual industry catalogs, the listed wall thicknesses may be rounded,
     *          so this result may slightly deviate from real product data.
     */
    public <U extends DistanceUnit, Q extends PhysicalQuantity<U>> Height getNominalMinWallThickness(Q outerDiameter) {
        if (outerDiameter == null) {
            throw new UnitSystemArgumentException("Outside diameter must be provided to calculate actual min nominal wall thickness.");
        }
        if (outerDiameter.getValue() <= 0.0) {
            throw new UnitSystemArgumentException("Outside diameter cannot be 0 or negative to calculate actual min nominal wall thickness. Outer diameter: " + outerDiameter);
        }
        double sdr = this.getValue();
        double wallThicknessInMeters = outerDiameter.getBaseValue() / sdr;
        return Height.of(wallThicknessInMeters, DistanceUnits.METER);
    }

    /**
     * Calculates the nominal outer diameter from SDR and given wall thickness (in double).
     *
     * @param nominalWallThickness The nominal wall thickness in base units (e.g., meters), must be > 0
     * @return Nominal outer diameter in base units
     * @throws UnitSystemArgumentException if wall thickness is non-positive
     *
     * @apiNote Uses the formula: {@code D = SDR * s}. Real catalog data may use rounded thicknesses.
     */
    public double getNominalOuterDiameter(double nominalWallThickness) {
        if (nominalWallThickness <= 0.0) {
            throw new UnitSystemArgumentException("Nominal wall thickness cannot be 0 or negative to calculate outer diameter from SDR. Nominal wall thickness: " + nominalWallThickness);
        }
        double SDR = this.getInUnit(RatioUnits.DECIMAL);
        return SDR * nominalWallThickness;
    }

    /**
     * Calculates the nominal minimum wall thickness from SDR and given outer diameter (in double).
     *
     * @param outerDiameter The outer diameter in base units (e.g., meters), must be > 0
     * @return Nominal minimum wall thickness in base units
     * @throws UnitSystemArgumentException if outer diameter is non-positive
     *
     * @apiNote Uses the formula: {@code s = D / SDR}. Actual catalog values may slightly differ
     *          due to rounding or standardized production sizes.
     */
    public double getNominalMinWallThickness(double outerDiameter) {
        if (outerDiameter <= 0.0) {
            throw new UnitSystemArgumentException("Outside diameter cannot be 0 or negative to calculate actual min nominal wall thickness. Outer diameter: " + outerDiameter);
        }
        double SDR = this.getInUnit(RatioUnits.DECIMAL);
        return SDR / outerDiameter;
    }


    @Override
    public RatioUnit getUnit() {
        return unitType;
    }

    @Override
    public SDR toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public SDR toUnit(RatioUnit targetUnit) {
        double valueInPascal = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInPascal);
        return SDR.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public SDR toUnit(String targetUnit) {
        RatioUnit resolvedUnit = RatioUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public SDR withValue(double value) {
        return SDR.of(value, unitType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SDR inputQuantity = (SDR) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "SDR{" + value + " " + unitType.getSymbol() + '}';
    }

}
