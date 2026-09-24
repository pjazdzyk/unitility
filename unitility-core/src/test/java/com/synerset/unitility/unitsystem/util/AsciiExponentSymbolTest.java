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
 * back. It holds every unit whose superscript spelling parses to the same rule for its plain-digit spelling.
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
                if (!parses((Class) info.quantityClass(), symbol)) {
                    // The canonical spelling itself does not parse (compound symbols in parentheses, and case
                    // lost to lower-casing). That is a different defect, outside what this test pins.
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
        assertThat(checked).as("the registry has superscript units to check").isGreaterThan(40);
        assertThat(refused).as("plain-digit spellings that do not reach their unit").isEmpty();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean parses(Class quantityClass, String symbol) {
        try {
            PhysicalQuantity<Unit> parsed = PARSING_FACTORY.parse(quantityClass, "2.5" + symbol);
            return parsed.getUnitSymbol().equals(symbol);
        } catch (RuntimeException notParsed) {
            return false;
        }
    }

    @Test
    @DisplayName("normal and standard gas volumes parse as an agent types them")
    void normalVolumes() {
        assertThat(PARSING_FACTORY.parse(com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow.class, "450Nm3/h")
                .getUnitSymbol()).isEqualTo("Nm³/h");
        assertThat(PARSING_FACTORY.parse(com.synerset.unitility.unitsystem.flow.NormalVolumetricFlow.class, "120Sm3/h")
                .getUnitSymbol()).isEqualTo("Sm³/h");
    }
}
