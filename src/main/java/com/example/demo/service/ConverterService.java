package com.example.demo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    public double convert(String category, String from, String to, double value) {
        switch (category.toLowerCase()) {
            case "length":
                return convertLength(from, to, value);
            case "weight":
                return convertWeight(from, to, value);
            case "temperature":
                return convertTemperature(from, to, value);
            default:
                throw new IllegalArgumentException("Invalid category");
        }
    }

    // Example length conversion using meter as base
    private double convertLength(String from, String to, double value) {
        Map<String, Double> units = Map.of(
                "millimeter", 0.001, "centimeter", 0.01, "meter", 1.0,
                "kilometer", 1000.0, "inch", 0.0254, "foot", 0.3048,
                "yard", 0.9144, "mile", 1609.34);

        return value * units.get(from) / units.get(to);
    }

    // Weight conversion using gram as base
    private double convertWeight(String from, String to, double value) {
        Map<String, Double> units = Map.of(
                "milligram", 0.001, "gram", 1.0, "kilogram", 1000.0,
                "ounce", 28.3495, "pound", 453.592);

        return value * units.get(from) / units.get(to);
    }

    // Temperature conversion
    private double convertTemperature(String from, String to, double value) {
        if (from.equals(to))
            return value;

        // Convert from -> Celsius
        double celsius = switch (from.toLowerCase()) {
            case "fahrenheit" -> (value - 32) * 5 / 9;
            case "kelvin" -> value - 273.15;
            default -> value;
        };

        // Convert Celsius -> to
        return switch (to.toLowerCase()) {
            case "fahrenheit" -> celsius * 9 / 5 + 32;
            case "kelvin" -> celsius + 273.15;
            default -> celsius;
        };
    }
}
