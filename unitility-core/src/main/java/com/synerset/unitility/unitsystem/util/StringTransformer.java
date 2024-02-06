package com.synerset.unitility.unitsystem.util;

/**
 * The StringTransformer class provides utility methods for transforming strings, particularly
 * those related to unit symbols and notations. It allows various operations like trimming, lowercasing,
 * replacing symbols, and cleaning up string representations of units.
 */
public class StringTransformer {

    private final String inputString;

    /**
     * Private constructor to create a StringTransformer instance with the specified input string.
     *
     * @param inputString The input string to be transformed.
     */
    private StringTransformer(String inputString) {
        this.inputString = inputString;
    }

    /**
     * Static factory method to create a StringTransformer instance with the specified input string.
     *
     * @param inputString The input string to be transformed.
     * @return A new instance of StringTransformer.
     */
    public static StringTransformer of(String inputString) {
        return new StringTransformer(inputString);
    }

    /**
     * Trims the input string, converts to lowercase, and removes spaces.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer trimLowerAndClean() {
        return StringTransformer.of(
                inputString.trim()
                        .toLowerCase()
                        .replace(" ", "")
                        .replace("_", "")
        );
    }

    /**
     * Replaces superscript symbols for cubic and square units with numeric equivalents.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer unifyAerialAndVol() {
        return StringTransformer.of(
                inputString.replace("³", "3")
                        .replace("²", "2"));
    }

    /**
     * Replaces various multiplication and division symbols with a common representation.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer unifyMultiAndDiv() {
        return StringTransformer.of(
                inputString.replace(".", "")
                        .replace("·", "")
                        .replace("x", "")
                        .replace("/", "p"));
    }

    /**
     * Replaces symbols representing angles with standardized representations.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer unifySymbolsOfAngle() {
        return StringTransformer.of(
                inputString.replace("°", "o")
                        .replace("deg", "o")
                        .replace("radian", "rad")
        );
    }

    /**
     * Removes degree symbols and notations from the input string.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer dropDegreeSymbols() {
        return StringTransformer.of(
                inputString.replace("°", "")
                        .replace("o", "")
                        .replace("deg", "")
        );
    }

    /**
     * Removes parentheses from the input string.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer dropParentheses() {
        return StringTransformer.of(
                inputString.replaceAll("[()\\[\\]{}<>]", "")
        );
    }

    /**
     * Removes square brackets from the input string.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer dropSquareBrackets() {
        return StringTransformer.of(
                inputString.replace("[", "")
        );
    }

    /**
     * Replaces commas with dots in the input string.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer replaceCommaForDot() {
        return StringTransformer.of(
                inputString.replace(",", ".")
        );
    }

    /**
     * Unifies symbols for Degrees, Minutes, and Seconds notation.
     *
     * @return A new StringTransformer instance with the transformed string.
     */
    public StringTransformer unifyDMSNotationSymbols() {
        String transformedString = inputString.replace("°", "o")
                .replace("deg", "o")
                .replace("d", "o")
                .replace("min", "'")
                .replace("m", "'")
                .replace("sec", "\"");

        if (!transformedString.contains("\"")) {
            transformedString = transformedString.replaceFirst("s", "\"");
        }

        return StringTransformer.of(transformedString);
    }

    /**
     * Returns the original input string without any transformations.
     *
     * @return The original input string.
     */
    @Override
    public String toString() {
        return inputString;
    }

}