package com.synerset.unitility.unitsystem.geographic;

import com.synerset.unitility.unitsystem.common.Angle;

public class BearingMapper {

    private BearingMapper() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Converts a signed bearing (-180 to +180 degrees) to a true bearing (0 to 360 degrees).
     *
     * @param signedBearing the signed bearing angle (-180 to +180 degrees)
     * @return the equivalent true bearing (0 to 360 degrees)
     */
    static Angle toTrueBearing(Angle signedBearing) {
        double trueBearingValue = BearingMapper.toTrueBearing(signedBearing.getValue());
        return Angle.ofDegrees(trueBearingValue);
    }

    /**
     * Converts a signed bearing value in degrees to a true bearing.
     *
     * @param signedBearingValueDeg the signed bearing value (-180 to +180 degrees)
     * @return the equivalent true bearing value (0 to 360 degrees)
     */
    static double toTrueBearing(double signedBearingValueDeg) {
        return (signedBearingValueDeg + 360) % 360;
    }

    /**
     * Converts a true bearing (0 to 360 degrees) to a signed bearing (-180 to +180 degrees).
     *
     * @param trueBearing the true bearing angle (0 to 360 degrees)
     * @return the equivalent signed bearing (-180 to +180 degrees)
     */
    static Angle toSignedBearing(Angle trueBearing) {
        double signedBearingValue = BearingMapper.toSignedBearing(trueBearing.getValue());
        return Angle.ofDegrees(signedBearingValue);
    }

    /**
     * Converts a true bearing value in degrees to a signed bearing.
     *
     * @param trueBearingValueDeg the true bearing value (0 to 360 degrees)
     * @return the equivalent signed bearing value (-180 to +180 degrees)
     */
    static double toSignedBearing(double trueBearingValueDeg) {
        return (trueBearingValueDeg > 180) ? trueBearingValueDeg - 360 : trueBearingValueDeg;
    }
}
