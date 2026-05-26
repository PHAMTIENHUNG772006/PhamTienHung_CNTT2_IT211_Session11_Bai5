package com.re.session11_bai1.service;


public class ShippingFeeCalculator {

    public double calculateFee(double weightKg, double distanceKm) {
        if (weightKg <= 0 || distanceKm <= 0) {
            throw new IllegalArgumentException("Weight and distance must be positive");
        }

        double weightFee = 0;
        if (weightKg <= 1) {
            weightFee = 50000;
        } else {

            weightFee = 50000 + (Math.ceil(weightKg - 1) * 10000);
        }

        double distanceFee = 0;
        if (distanceKm <= 10) {
            distanceFee = 0;
        } else if (distanceKm < 50) {
            distanceFee = (distanceKm - 10) * 5000;
        } else {
            distanceFee = (40 * 5000) + ((distanceKm - 50) * 4000);
        }

        return weightFee + distanceFee;
    }
}
