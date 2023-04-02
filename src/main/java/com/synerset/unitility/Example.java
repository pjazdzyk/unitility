package com.synerset.unitility;

import com.synerset.unitility.unitsystem.distance.Distance;
import com.synerset.unitility.unitsystem.dynamicviscosity.DynamicViscosity;
import com.synerset.unitility.unitsystem.temperature.Temperature;

public class Example {
    public static void main(String[] args) {
        Distance bigDistance = Distance.ofMeters(10);
        Distance smallDistance = Distance.ofMeters(0.00012345);
        System.out.println(bigDistance + " | " + smallDistance);
    }
}
