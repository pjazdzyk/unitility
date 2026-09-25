package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.PhysicalQuantity;
import com.synerset.unitility.unitsystem.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Every unit written with a superscript exponent must also parse when the exponent is typed as a plain digit.
 *
 * <p>Nobody types {@code ³} from a keyboard, and an agent calling an API never does: {@code "450Nm3/h"},
 * {@code "250W/m2"}. Most unit enums already normalise the two forms, and five did not (normal volume flow, mass
 * flux, heat flux, energy density, air-fuel ratio by volume), so {@code "Nm3/h"} was refused while {@code "m3/h"}
 * parsed. This test walks the whole registry rather than the five, so a new unit enum cannot bring the defect
 * back. Since 5.0.2 every canonical symbol parses (UnitSymbolRoundTripTest), so no unit is exempt.
 */
@DisplayName("Unit symbols: a plain-digit exponent parses like the superscript one")
class AsciiExponentSymbolTest {

    private static final PhysicalQuantityParsingFactory PARSING_FACTORY =
            PhysicalQuantityParsingFactory.getDefaultParsingFactory();

    @Test
    @DisplayName("every registered unit with a superscript parses from its plain-digit spelling to the same unit")
    @SuppressWarnings({"unchecked", "rawtypes"})
    void plainDigitsParse() {
        List<String> refused = new ArrayList<>();
        int checked = 0;
        for (PhysicalQuantityInfo info : SupportedQuantitiesRegistry.getInstance().findAllSupportedQuantities()) {
            for (PhysicalUnitInfo unit : info.supportedUnits()) {
                String symbol = unit.unitSymbol();
                if (!symbol.contains("²") && !symbol.contains("³")) {
                    continue;
                }
                String plain = symbol.replace("²", "2").replace("³", "3");
                if (!FACTORY_PARSES.contains(info.quantityClass())) {
                    // A GeoDistance is two coordinates, not a value and a unit, so it has nothing to parse.
                    continue;
                }
                checked++;
                try {
                    PhysicalQuantity<Unit> parsed = PARSING_FACTORY.parse((Class) info.quantityClass(), "2.5" + plain);
                    if (!parsed.getUnitSymbol().equals(symbol)) {
                        refused.add(info.quantityClass().getSimpleName() + " \"" + plain + "\" parsed as "
                                + parsed.getUnitSymbol());
                    }
                } catch (RuntimeException notParsed) {
                    refused.add(info.quantityClass().getSimpleName() + " \"" + plain + "\": " + notParsed.getMessage());
                }
            }
        }
        assertThat(checked).as("the registry has superscript units to check").isGreaterThan(60);
        assertThat(refused).as("plain-digit spellings that do not reach their unit").isEmpty();
    }

    private static final java.util.Set<?> FACTORY_PARSES = PARSING_FACTORY.findAllRegisteredClasses();

    @Test
    @DisplayName("normal and standard gas volumes parse as an agent types them")
    void normalVolumes() {
        assertThat(PARSING_FACTORY.parse(com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow.class, "450Nm3/h")
                .getUnitSymbol()).isEqualTo("Nm³/h");
        assertThat(PARSING_FACTORY.parse(com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow.class, "120Sm3/h")
                .getUnitSymbol()).isEqualTo("Sm³/h");
    }
}
