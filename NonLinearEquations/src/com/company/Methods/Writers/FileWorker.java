package com.company.Methods.Writers;

import com.company.Methods.Calculations.Calculator;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWorker {

    public static int decimalPlaces(double accuracy) {
        int count = 1;
        double q = 0.1;
        while (q >= accuracy) {
            q = q / 10;
            count++;
        }
        return count;
    }

    public static void printToFile(double left, double right, double x0, double f, int EQUATION_IDENTIFIER, int count, BufferedWriter writer) throws IOException {
        writer.write(count + ") a=");
        writer.write(String.valueOf(left));
        writer.write(", b=");
        writer.write(String.valueOf(right));
        writer.write(", x=");
        writer.write(String.valueOf(x0));
        writer.write(", f(a)=");
        writer.write(String.valueOf(Calculator.calculateFunction(left, EQUATION_IDENTIFIER)));
        writer.write(", f(b)=");
        writer.write(String.valueOf(Calculator.calculateFunction(right, EQUATION_IDENTIFIER)));
        writer.write(", f(x)=");
        writer.write(String.valueOf(f));
        writer.write(", |a-b|=");
        writer.write((Math.abs(right - left)) + "\n");
        writer.flush();
    }

    public static void printResult(double x0, int EQUATION_IDENTIFIER, double accuracy, int count) {
        int decimal = decimalPlaces(accuracy);
        System.out.print("Результат:\nНайденный корень уравнения: ");
        System.out.printf("%." + decimal + "f\n", x0);
        System.out.print("Значение функции в корне: ");
        System.out.printf("%." + decimal + "f\n", Calculator.calculateFunction(x0, EQUATION_IDENTIFIER));
        System.out.println("Число итераций: " + count);
    }

}
