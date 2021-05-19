package com.company.Methods.Calculations;

public class Calculator {
    public static double calculateFunction(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> -1.8 * Math.pow(x, 3) - 2.94 * Math.pow(x, 2) + 10.37 * x + 5.38;
            case (2) -> Math.pow(23.9 * x + 6, 0.5) - 239;
            case (3) -> Math.cos(x) + 0.5;
            default -> 0;
        };
    }

    public static double calculateDerivative(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> -5.4 * Math.pow(x, 2) - 5.88 * x + 10.37;
            case (2) -> 11.95 / (Math.pow(23.9 * x + 6, 0.5));
            case (3) -> -1 * Math.sin(x);
            default -> 0;
        };
    }

    public static double calculateSecondDerivative(double x, int chosenEquation) {
        return switch (chosenEquation) {
            case (1) -> -10.8 * x - 5.88;
            case (2) -> -1 * (142.802) / Math.pow(23.9 * x + 6, 1.5);
            case (3) -> -1 * Math.cos(x);
            default -> 0;
        };
    }

    public static double calculatePhiFunction(double x, int chosenEquation, double lambda) {
        return switch (chosenEquation) {
            case (1) -> (-1.8 * Math.pow(x, 3) - 2.94 * Math.pow(x, 2) + 10.37 * x + 5.38) * lambda + x;
            case (2) -> (Math.pow(23.9 * x + 6, 0.5) - 239) * lambda + x;
            case (3) -> (Math.cos(x) + 0.5) * lambda + x;
            default -> 0;
        };
    }

    public static double calculatePhiFunctionDerivative(double x, int chosenEquation, double lambda) {
        return switch (chosenEquation) {
            case (1) -> -5.4  * (x * x + 1.08888 * x - 1.92037) * lambda + 1;
            case (2) -> 11.95 * lambda / (Math.pow(23.9 * x + 6, 0.5)) + 1;
            case (3) -> 1 - lambda * Math.sin(x);
            default -> 0;
        };
    }
}

