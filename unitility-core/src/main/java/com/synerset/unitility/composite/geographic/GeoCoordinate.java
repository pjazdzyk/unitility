package com.synerset.unitility.composite.geographic;

import com.synerset.unitility.unitsystem.EngineeringFormat;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.exceptions.UnitSystemArgumentException;

import java.util.Objects;

public final class GeoCoordinate implements EngineeringFormat {

    public static final Angle MAX_LONGITUDE = Angle.ofDegrees(90);
    public static final Angle MIN_LONGITUDE = Angle.ofDegrees(-90);
    public static final Angle MAX_LATITUDE = Angle.ofDegrees(180);
    public static final Angle MIN_LATITUDE = Angle.ofDegrees(-180);
    private final Angle longitude;
    private final Angle latitude;

    public GeoCoordinate(Angle longitude, Angle latitude) {
        validateLongitude(longitude);
        validateLatitude(latitude);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GeoCoordinate) obj;
        return Objects.equals(this.longitude, that.longitude) &&
                Objects.equals(this.latitude, that.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    @Override
    public String toString() {
        return "GeoCoordinate[" +
                "longitude=" + longitude + ", " +
                "latitude=" + latitude + ']';
    }

    @Override
    public String toEngineeringFormat() {
        return longitude.toEngineeringFormat() + " | " + latitude.toEngineeringFormat();
    }

    @Override
    public String toEngineeringFormat(String variableName) {
        return variableName + " = " + toEngineeringFormat();
    }

    @Override
    public String toEngineeringFormat(int relevantDigits) {
        return longitude.toEngineeringFormat(relevantDigits) + " | " + latitude.toEngineeringFormat(relevantDigits);
    }

    @Override
    public String toEngineeringFormat(String variableName, int relevantDigits) {
        return variableName + " = " + toEngineeringFormat(relevantDigits);
    }

    private void validateLongitude(Angle longitude) {
        if (longitude.greaterThan(MAX_LONGITUDE) || longitude.lowerThan(MIN_LONGITUDE)) {
            throw new UnitSystemArgumentException("Invalid longitude value. Allowed range: "
                    + MIN_LONGITUDE.toEngineeringFormat() + " to " + MAX_LONGITUDE.toEngineeringFormat());
        }
    }

    private void validateLatitude(Angle latitude) {
        if (latitude.greaterThan(MAX_LATITUDE) || latitude.lowerThan(MIN_LATITUDE)) {
            throw new UnitSystemArgumentException("Invalid latitude value. Allowed range: "
                    + MIN_LATITUDE.toEngineeringFormat() + " to " + MAX_LATITUDE.toEngineeringFormat());
        }
    }

    public static class Builder {
        private Angle longitude;
        private Angle latitude;

        public Builder longitude(Angle longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder latitude(Angle latitude) {
            this.latitude = latitude;
            return this;
        }

        public GeoCoordinate build() {
            return new GeoCoordinate(longitude, latitude);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Angle longitude() {
        return longitude;
    }

    public Angle latitude() {
        return latitude;
    }

}
