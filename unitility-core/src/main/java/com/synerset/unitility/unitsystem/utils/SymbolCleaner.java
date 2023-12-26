package com.synerset.unitility.unitsystem.utils;

public class SymbolCleaner {

    private final String inputString;

    private SymbolCleaner(String inputString) {
        this.inputString = inputString;
    }

    public static SymbolCleaner of(String inputString) {
        return new SymbolCleaner(inputString);
    }

    public SymbolCleaner trimAndClean() {
        return SymbolCleaner.of(
                inputString.trim()
                        .toLowerCase()
                        .replace(" ", "")
        );
    }

    public SymbolCleaner unifyAerialAndVol() {
        return SymbolCleaner.of(
                inputString.replace("³", "3")
                        .replace("²", "2"));
    }

    public SymbolCleaner unifyMultiAndDiv() {
        return SymbolCleaner.of(
                inputString.replace(".", "")
                        .replace("·", "")
                        .replace("x", "")
                        .replace("/", "p"));

    }

    public SymbolCleaner unifySymbolsOfAngle() {
        return SymbolCleaner.of(
                inputString.replace("°", "")
                        .replace("o", "")
                        .replace("deg", "")
                        .replace("radian", "rad")
        );
    }

    public SymbolCleaner removeParentheses() {
        return SymbolCleaner.of(
                inputString.replace("(", "")
                        .replace(")", "")
                        .replace("}", "")
                        .replace("{", "")
                        .replace("<", "")
                        .replace(">", "")
        );
    }

    @Override
    public String toString() {
        return inputString;
    }

}