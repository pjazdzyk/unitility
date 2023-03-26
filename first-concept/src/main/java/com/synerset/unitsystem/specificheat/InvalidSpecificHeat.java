package com.synerset.unitsystem.specificheat;

public record InvalidSpecificHeat(double value, Class<? extends SpecificHeat> unitClass) {
}
