package com.synerset.unitility.unitsystem.util;

import com.synerset.unitility.unitsystem.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Finds the unit a symbol names among a quantity's units, the same way for every quantity.
 *
 * <p>Unit symbols are case-sensitive: {@code MJ} is a megajoule and {@code mJ} a millijoule, a factor of a billion.
 * Each unit enum resolves symbols with its own normalisation, and most of them lower-case both sides, so a symbol
 * that differs from another only by case resolves to whichever comes first in the enum. The default parsing
 * factory also lower-cased the whole input and dropped its parentheses before any enum saw it, which made
 * {@code MV}, {@code MF}, {@code MC} and {@code MΩ} unreachable and left compound symbols such as
 * {@code kg/(m²·s)} unmatchable. This resolver runs first, on the symbol as it was written:
 *
 * <ol>
 *   <li>the exact symbol;</li>
 *   <li>the symbol with spelling differences removed, case kept: whitespace, underscores, parentheses and brackets,
 *       multiplication dots, superscript digits written as plain ones, {@code µ} or {@code μ} written as
 *       {@code u}, and the ohm sign written as the Greek omega it is drawn with;</li>
 *   <li>the same, ignoring case, when exactly one unit matches, or when exactly one of several shares the first
 *       character's case, which is the SI prefix that case decides ({@code mv} is milli, {@code Mv} mega).</li>
 * </ol>
 *
 * <p>Anything else is left to the unit enum, which knows its own aliases ({@code degC}, {@code cfm}, {@code ohm}).
 * A pure kernel: units in, a unit or nothing out.
 */
public final class UnitSymbolResolver {

    private UnitSymbolResolver() {
    }

    /**
     * @param units  the quantity's units
     * @param symbol the symbol as written
     * @return the unit it names, or empty when only the unit enum's own aliases could say
     */
    public static <U extends Unit> Optional<U> resolve(List<U> units, String symbol) {
        if (symbol == null || units == null || units.isEmpty()) {
            return Optional.empty();
        }
        String written = symbol.trim();
        if (written.isEmpty()) {
            return Optional.empty();
        }
        for (U unit : units) {
            if (written.equals(unit.getSymbol())) {
                return Optional.of(unit);
            }
        }

        String normalised = normalise(written);
        if (normalised.isEmpty()) {
            return Optional.empty();
        }
        List<U> sameCase = new ArrayList<>();
        List<U> anyCase = new ArrayList<>();
        for (U unit : units) {
            String candidate = normalise(unit.getSymbol());
            if (candidate.equals(normalised)) {
                sameCase.add(unit);
            }
            if (candidate.equalsIgnoreCase(normalised)) {
                anyCase.add(unit);
            }
        }
        if (sameCase.size() == 1) {
            return Optional.of(sameCase.get(0));
        }
        if (!sameCase.isEmpty()) {
            return Optional.empty();
        }
        if (anyCase.size() == 1) {
            return Optional.of(anyCase.get(0));
        }
        List<U> samePrefix = new ArrayList<>();
        for (U unit : anyCase) {
            if (normalise(unit.getSymbol()).charAt(0) == normalised.charAt(0)) {
                samePrefix.add(unit);
            }
        }
        return samePrefix.size() == 1 ? Optional.of(samePrefix.get(0)) : Optional.empty();
    }

    /** The spelling of a symbol without the differences that never change which unit it names. */
    static String normalise(String symbol) {
        StringBuilder out = new StringBuilder(symbol.length());
        for (int i = 0; i < symbol.length(); i++) {
            char c = symbol.charAt(i);
            switch (c) {
                case ' ', '\t', '_', '(', ')', '[', ']', '{', '}', '·', '⋅', '*' -> {
                    // spelling, not meaning
                }
                case '¹' -> out.append('1');
                case '²' -> out.append('2');
                case '³' -> out.append('3');
                case '⁴' -> out.append('4');
                case '⁻' -> out.append('-');
                case 'µ', 'μ' -> out.append('u');
                // The ohm sign and the Greek capital omega look the same and are typed interchangeably.
                case 'Ω' -> out.append('Ω');
                default -> out.append(c);
            }
        }
        return out.toString();
    }
}
