package com.company.Methods;

import com.company.Methods.Calculations.Calculator;
import com.company.Methods.Calculations.Graph;

import java.io.IOException;
import java.util.Scanner;

import  com.company.Methods.Writers.FileWorker;

public class Newtons {
    static Scanner sc = new Scanner(System.in);

    public static boolean checkSecondDerivative(double left, double right, int equation) {
        boolean flag = true;
        double step = (right - left) / 100;
        double x = left;
        double sign = Math.signum(Calculator.calculateSecondDerivative(x, equation));
        while (x < right) {
            x += step;
            if (Math.signum(Calculator.calculateSecondDerivative(x, equation)) != sign) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean functionMonotone(double left, double right, int equation) {
        boolean flag = true;
        double step = (right - left) / 100;
        double x = left;
        double sign = Math.signum(Calculator.calculateDerivative(x, equation));
        while (x < right) {
            x += step;
            if (Math.signum(Calculator.calculateDerivative(x, equation)) != sign) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static boolean verifyExisting(double left, double right, int equation) {
        if ((Calculator.calculateFunction(left, equation) * Calculator.calculateFunction(right, equation) < 0)) {
            return true;
        }
        System.out.println("Не пройдена верификация существования и единственности корня на данном промежутке.");
        return false;
    }

    public static double chooseInitialX(double left, double right, int equation) {
        if (Calculator.calculateFunction(left, equation) * Calculator.calculateSecondDerivative(left, equation) > 0) {
            return left;
        } else {
            return right;
        }
    }


    public static void solution(double left, double right, double accuracy, int equation) throws IOException {
        Graph graph = new Graph(left, right, equation);
        while (!verifyExisting(left, right, equation)) {
            System.out.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        if (!functionMonotone(left, right, equation)) {
            System.out.println("На данном промежутке корень может быть не единственный.");
        }
        while (!checkSecondDerivative(left, right, equation)) {
            System.out.println("Не пройдена проверка на сохранение знака второй производной на заданном промежутке.");
            System.out.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        System.out.println("Процесс решения:");
        double prev = chooseInitialX(left, right, equation);
        int count = 1;
        double h0 = Calculator.calculateFunction(prev, equation) / Calculator.calculateDerivative(prev, equation);
        double x = prev - h0;
        //ConsoleWorker.print(count, accuracy, prev, Calculator.calculateFunction(prev, equation), Calculator.calculateDerivative(prev, equation), x, Math.abs(prev - x));
        count++;
        while (!(Math.abs(x - prev) <= accuracy || Math.abs(Calculator.calculateFunction(x, equation) / Calculator.calculateDerivative(x, equation)) <= accuracy || Math.abs(Calculator.calculateFunction(x, equation)) <= accuracy)) {
            prev = x;
            h0 = Calculator.calculateFunction(prev, equation) / Calculator.calculateDerivative(prev, equation);
            x = prev - h0;
          //  ConsoleWorker.print(count, accuracy, prev, Calculator.calculateFunction(prev, equation), Calculator.calculateDerivative(prev, equation), x, Math.abs(prev - x));
            count++;
        }
        FileWorker.printResult(x, equation, accuracy, count - 1);
    }
}
