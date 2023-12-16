package com.synerset.unitility.unitsystem;

public interface EngineeringFormat {

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * for example, 20.123456789[°C].
     *
     * @return The representation in engineering format.
     */
    String toEngineeringFormat();

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * with variable name, for example, t_atm = 20.123456789[°C].
     *
     * @param variableName The name of the variable for which the engineering format is generated.
     * @return The representation in engineering format.
     */
    String toEngineeringFormat(String variableName);

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * truncated to relevantDigits: for example, 120.123[F]
     *
     * @param relevantDigits The number of rounded relevant digits to show.
     * @return The representation in engineering format.
     */
    String toEngineeringFormat(int relevantDigits);

    /**
     * Converts the physical quantity to an engineering format where the unit is placed in rectangular braces,
     * with variable name, truncated to relevantDigits: for example, t_atm = 120.123[F]
     *
     * @param variableName   The name of the variable for which the engineering format is generated.
     * @param relevantDigits The number of rounded relevant digits to show.
     * @return The representation in engineering format.
     */
    String toEngineeringFormat(String variableName, int relevantDigits);

}