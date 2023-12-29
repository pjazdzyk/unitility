package com.synerset.unitility.unitsystem.geographic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DMSValidator {

    private DMSValidator() {
        throw new IllegalStateException("Utility class");
    }

    private static final String DMS_PATTERN = "^-?\\d{1,3}(o|°|deg)(\\d{1,2}('|min|m)((\\d{1,2}(\\.\\d+)?)(\"|sec|s))?)?([NnSsWwEe])?$";

    public static boolean isValidDMSFormat(String input) {
        Pattern regex = Pattern.compile(DMS_PATTERN);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }

}
