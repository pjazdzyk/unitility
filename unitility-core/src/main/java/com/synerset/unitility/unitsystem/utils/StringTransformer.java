package com.synerset.unitility.unitsystem.utils;

public class StringTransformer {

    private final String inputString;

    private StringTransformer(String inputString) {
        this.inputString = inputString;
    }

    public static StringTransformer of(String inputString) {
        return new StringTransformer(inputString);
    }

    public StringTransformer trimLowerAndClean() {
        return StringTransformer.of(
                inputString.trim()
                        .toLowerCase()
                        .replace(" ", "")
        );
    }

    public StringTransformer unifyAerialAndVol() {
        return StringTransformer.of(
                inputString.replace("³", "3")
                        .replace("²", "2"));
    }

    public StringTransformer unifyMultiAndDiv() {
        return StringTransformer.of(
                inputString.replace(".", "")
                        .replace("·", "")
                        .replace("x", "")
                        .replace("/", "p"));

    }

    public StringTransformer unifySymbolsOfAngle() {
        return StringTransformer.of(
                inputString.replace("°", "o")
                        .replace("deg", "o")
                        .replace("radian", "rad")
        );
    }

    public StringTransformer dropDegreeSymbols() {
        return StringTransformer.of(
                inputString.replace("°", "")
                        .replace("o", "")
                        .replace("deg", "")
        );
    }

    public StringTransformer removeParentheses() {
        return StringTransformer.of(
                inputString.replace("(", "")
                        .replace(")", "")
                        .replace("}", "")
                        .replace("{", "")
                        .replace("<", "")
                        .replace(">", "")
        );
    }

    public StringTransformer replaceCommaForDot() {
        return StringTransformer.of(
                inputString.replace(",", ".")
        );
    }

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

    @Override
    public String toString() {
        return inputString;
    }

}