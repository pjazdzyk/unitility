package com.synerset.unitsystem.temperature;

public record InvalidTemperature(double value, Class<? extends Temperature> unitClass){}
