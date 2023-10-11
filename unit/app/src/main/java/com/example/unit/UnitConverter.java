package com.example.unit;

public class UnitConverter {

    public static double convertCentimetersToMeters(double value) {
        return value / 100;
    }

    public static double convertMetersToCentimeters(double value) {
        return value * 100;
    }

    public static double convertGramsToKilograms(double value) {
        return value / 1000;
    }

    public static double convertKilogramsToGrams(double value) {
        return value * 1000;
    }
}
