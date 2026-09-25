package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import com.synerset.unitility.unitsystem.electric.Capacitance;
import com.synerset.unitility.unitsystem.electric.Charge;
import com.synerset.unitility.unitsystem.electric.Resistance;
import com.synerset.unitility.unitsystem.electric.ResistanceUnits;
import com.synerset.unitility.unitsystem.electric.Voltage;
import com.synerset.unitility.unitsystem.flow.MassFlux;
import com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow;
import com.synerset.unitility.unitsystem.thermodynamic.Energy;
import com.synerset.unitility.unitsystem.thermodynamic.EnergyUnits;
import com.synerset.unitility.unitsystem.thermodynamic.IsothermalCompressibility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

/**
 * Every unit's own symbol must parse back to that unit, through the factory and through its enum.
 *
 * <p>Until 5.0.2 the factory lower-cased the whole input and dropped its parentheses before any unit enum saw it,
 * so 61 of the registry's own symbols did not round-trip: {@code MV} came back as millivolts, {@code MF} and
 * {@code MC} likewise, every ohm failed, and compound symbols such as {@code kg/(m²·s)} matched nothing.
 * {@code EnergyUnits} resolved {@code MJ} as a millijoule even when called directly. Each is a factor of up to a
 * billion that parses without complaint, which is why this test walks the whole registry rather than a sample.
 */
@DisplayName("Unit symbols: every unit's own symbol parses back to that unit")
class UnitSymbolRoundTripTest {

    private static final PhysicalQuantityParsingFactory FACTORY = PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static String parsedSymbol(Class quantityClass, String input) {
        PhysicalQuantity<Unit> parsed = FACTORY.parse(quantityClass, input);
        return parsed.getUnitSymbol();
    }

    @Test
    @DisplayName("through the parsing factory, with and without a space before the symbol")
    void throughTheFactory() {
        Set<Class<PhysicalQuantity<Unit>>> parseable = FACTORY.findAllRegisteredClasses();
        List<String> failures = new ArrayList<>();
        int checked = 0;
        for (PhysicalQuantityInfo info : SupportedQuantitiesRegistry.getInstance().findAllSupportedQuantities()) {
            if (!parseable.contains(info.quantityClass())) {
                // A GeoDistance is two coordinates, not a value and a unit, so it has nothing to parse.
                continue;
            }
            for (PhysicalUnitInfo unit : info.supportedUnits()) {
                String symbol = unit.unitSymbol();
                if (symbol.isBlank()) {
                    // A blank symbol is the default unit, which a bare number already parses to.
                    continue;
                }
                List<String> inputs = new ArrayList<>(List.of("8 " + symbol));
                if (!Character.isDigit(symbol.charAt(0))) {
                    inputs.add("8" + symbol);
                }
                for (String input : inputs) {
                    checked++;
                    try {
                        String got = parsedSymbol(info.quantityClass(), input);
                        if (!got.equals(symbol)) {
                            failures.add(info.quantityClass().getSimpleName() + " \"" + input + "\" -> " + got);
                        }
                    } catch (RuntimeException refused) {
                        failures.add(info.quantityClass().getSimpleName() + " \"" + input + "\": " + refused.getMessage());
                    }
                }
            }
        }
        assertThat(checked).isGreaterThan(800);
        assertThat(failures).as("symbols that do not come back as themselves").isEmpty();
    }

    @Test
    @DisplayName("through each unit enum's own fromSymbol")
    void throughEachEnum() throws IllegalAccessException {
        List<String> failures = new ArrayList<>();
        for (PhysicalQuantityInfo info : SupportedQuantitiesRegistry.getInstance().findAllSupportedQuantities()) {
            for (Unit unit : SupportedQuantitiesRegistry.getInstance().findUnitsByClass(info.quantityClass()).orElseThrow()) {
                Method fromSymbol;
                try {
                    fromSymbol = unit.getClass().getMethod("fromSymbol", String.class);
                } catch (NoSuchMethodException none) {
                    continue;
                }
                try {
                    Object resolved = fromSymbol.invoke(null, unit.getSymbol());
                    if (resolved != unit) {
                        failures.add(unit.getClass().getSimpleName() + " \"" + unit.getSymbol() + "\" -> " + resolved);
                    }
                } catch (InvocationTargetException refused) {
                    failures.add(unit.getClass().getSimpleName() + " \"" + unit.getSymbol() + "\": "
                            + refused.getCause().getMessage());
                }
            }
        }
        assertThat(failures).isEmpty();
    }

    @Test
    @DisplayName("case decides the SI prefix: MJ is a megajoule, mJ a millijoule, whichever way they arrive")
    void caseDecidesThePrefix() {
        assertThat(FACTORY.parse(Energy.class, "5 MJ").getInJoules()).isCloseTo(5.0e6, within(1e-6));
        assertThat(FACTORY.parse(Energy.class, "5 mJ").getInJoules()).isCloseTo(5.0e-3, within(1e-12));
        assertThat(EnergyUnits.fromSymbol("MJ")).isEqualTo(EnergyUnits.MEGAJOULE);
        assertThat(Energy.of(5, "MJ").getInJoules()).isCloseTo(5.0e6, within(1e-6));

        assertThat(FACTORY.parse(Voltage.class, "2 MV").getUnitSymbol()).isEqualTo("MV");
        assertThat(FACTORY.parse(Voltage.class, "2 mV").getUnitSymbol()).isEqualTo("mV");
        assertThat(FACTORY.parse(Capacitance.class, "2 MF").getUnitSymbol()).isEqualTo("MF");
        assertThat(FACTORY.parse(Charge.class, "2 MC").getUnitSymbol()).isEqualTo("MC");
        // Typed with the ohm sign (U+2126) or with the Greek capital omega (U+03A9), which look identical.
        for (String ohm : List.of("Ω", "Ω")) {
            assertThat(FACTORY.parse(Resistance.class, "10 M" + ohm).getUnitSymbol())
                    .isEqualTo(ResistanceUnits.MEGAOHM.getSymbol());
            assertThat(FACTORY.parse(Resistance.class, "10 m" + ohm).getUnitSymbol())
                    .isEqualTo(ResistanceUnits.MILLIOHM.getSymbol());
        }
    }

    @Test
    @DisplayName("a symbol typed in the wrong case keeps the prefix its first letter names")
    void wrongCaseKeepsThePrefix() {
        assertThat(FACTORY.parse(Voltage.class, "2 Mv").getUnitSymbol()).isEqualTo("MV");
        assertThat(FACTORY.parse(Voltage.class, "2 mv").getUnitSymbol()).isEqualTo("mV");
        assertThat(FACTORY.parse(Energy.class, "5 kwh").getUnitSymbol()).isEqualTo("kWh");
    }

    @Test
    @DisplayName("compound symbols parse with or without their parentheses, and with plain-digit exponents")
    void compoundSymbols() {
        for (String input : List.of("2 kg/(m²·s)", "2 kg/(m2·s)", "2kg/(m2*s)")) {
            assertThat(FACTORY.parse(MassFlux.class, input).getUnitSymbol()).as(input).isEqualTo("kg/(m²·s)");
        }
        assertThat(FACTORY.parse(NormalVolumetricFlow.class, "450Nm3/h").getUnitSymbol()).isEqualTo("Nm³/h");
    }

    @Test
    @DisplayName("a value then a reciprocal unit keeps the space between them")
    void reciprocalUnits() {
        IsothermalCompressibility parsed = FACTORY.parse(IsothermalCompressibility.class, "4.5e-10 1/Pa");
        assertThat(parsed.getValue()).isCloseTo(4.5e-10, within(1e-20));
        assertThat(parsed.getUnitSymbol()).isEqualTo("1/Pa");
    }

    @Test
    @DisplayName("the enum's own aliases still reach it: the resolver only decides what it can")
    void aliasesStillWork() {
        assertThat(FACTORY.parse(com.synerset.unitility.unitsystem.thermodynamic.Temperature.class, "20degC")
                .getUnitSymbol()).isEqualTo("°C");
        assertThat(FACTORY.parse(com.synerset.unitility.unitsystem.thermodynamic.Temperature.class, "20oC")
                .getUnitSymbol()).isEqualTo("°C");
        assertThat(FACTORY.parse(com.synerset.unitility.unitsystem.thermodynamic.Pressure.class, "2.5bar")
                .getUnitSymbol()).isEqualTo("bar");
    }
}
