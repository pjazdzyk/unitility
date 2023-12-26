package com.synerset.unitility.unitsystem.utils;

public class StringCleaner {

    private final String inputString;

    private StringCleaner(String inputString) {
        this.inputString = inputString;
    }

    public static StringCleaner of(String inputString) {
        return new StringCleaner(inputString);
    }

    public StringCleaner trimAndClean() {
        return StringCleaner.of(
                inputString.trim()
                        .toLowerCase()
                        .replace(" ", "")
        );
    }

    public StringCleaner unifyAerialAndVol() {
        return StringCleaner.of(
                inputString.replace("³", "3")
                        .replace("²", "2"));
    }

    public StringCleaner unifyMultiAndDiv() {
        return StringCleaner.of(
                inputString.replace(".", "")
                        .replace("·", "")
                        .replace("x", "")
                        .replace("/", "p"));

    }

    public StringCleaner unifySymbolsOfAngle() {
        return StringCleaner.of(
                inputString.replace("°", "")
                        .replace("o", "")
                        .replace("deg", "")
                        .replace("radian", "rad")
        );
    }

    public StringCleaner removeParentheses() {
        return StringCleaner.of(
                inputString.replace("(", "")
                        .replace(")", "")
                        .replace("}", "")
                        .replace("{", "")
                        .replace("<", "")
                        .replace(">", "")
        );
    }

    public StringCleaner replaceCommaForDot() {
        return StringCleaner.of(
                inputString.replace(",", ".")
        );
    }

    @Override
    public String toString() {
        return inputString;
    }

}