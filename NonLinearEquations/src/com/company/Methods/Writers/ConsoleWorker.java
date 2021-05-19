package com.company.Methods.Writers;

import com.company.Methods.Calculations.Calculator;

import static com.company.Methods.Writers.FileWorker.decimalPlaces;

public class ConsoleWorker {
    public static void print(double left, double right, double x0, double f, int EQUATION_IDENTIFIER, double accuracy, int count) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") a=");
        System.out.printf("%." + decimal + "f", left);
        System.out.print(", b=");
        System.out.printf("%." + decimal + "f", right);
        System.out.print(", x=");
        System.out.printf("%." + decimal + "f", x0);
        System.out.print(", f(a)=");
        System.out.printf("%." + decimal + "f", Calculator.calculateFunction(left, EQUATION_IDENTIFIER));
        System.out.print(", f(b)=");
        System.out.printf("%." + decimal + "f", Calculator.calculateFunction(right, EQUATION_IDENTIFIER));
        System.out.print(", f(x)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", |a-b|=");
        System.out.printf("%." + decimal + "f\n", Math.abs(right - left));
    }

    public static void print(int count, double accuracy, double prev, double f, double ff, double x, double r) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") xk=");
        System.out.printf("%." + decimal + "f", prev);
        System.out.print(", f(xk)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", f'(xk)=");
        System.out.printf("%." + decimal + "f", ff);
        System.out.print(", xk+1=");
        System.out.printf("%." + decimal + "f", x);
        System.out.print(", |xk-xk+1|=");
        System.out.printf("%." + decimal + "f\n", r);
    }

    public static void print(int count, double accuracy, double prev, double f, double x, double r) {
        int decimal = decimalPlaces(accuracy);
        System.out.print(count + ") xk=");
        System.out.printf("%." + decimal + "f", prev);
        System.out.print(", f(xk)=");
        System.out.printf("%." + decimal + "f", f);
        System.out.print(", xk+1 = \uD835\uDF11(\uD835\uDC65\uD835\uDC58)");
        System.out.printf("%." + decimal + "f", x);
        System.out.print(", |xk-xk+1|=");
        System.out.printf("%." + decimal + "f\n", r);
    }
}
