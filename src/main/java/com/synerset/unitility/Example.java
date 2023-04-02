package com.synerset.unitility;

import com.synerset.unitility.unitsystem.temperature.Temperature;

public class Example {
    public void main(String[] args) {
        Temperature temp = Temperature.ofCelsius(20.5);
        Temperature tempInKelvin = temp.toKelvin();
        Temperature temInFahrenheit = tempInKelvin.toFahrenheit();
        System.out.println(temp  + " | " + tempInKelvin + " | " + temInFahrenheit);
    }
}
