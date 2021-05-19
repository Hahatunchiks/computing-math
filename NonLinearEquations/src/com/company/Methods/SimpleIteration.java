package com.company.Methods;

import com.company.Methods.Calculations.Calculator;
import com.company.Methods.Calculations.Graph;
import com.company.Methods.Writers.ConsoleWorker;
import  com.company.Methods.Writers.FileWorker;

import java.io.IOException;
import java.util.Scanner;

public class SimpleIteration {
    static Scanner sc = new Scanner(System.in);
    static double lambda;

    public static boolean verifyConvergence(double left, double right, int equation) {
        boolean flag = true;
        double step = (right - left) / 50;
        double x = left;
        lambda = -1 / Math.max(Calculator.calculateDerivative(left, equation), Calculator.calculateDerivative(right, equation));
        System.out.println(Math.abs(Calculator.calculatePhiFunctionDerivative(left, equation, lambda)));
        System.out.println(Math.abs(Calculator.calculatePhiFunctionDerivative(right, equation, lambda)));
        while (x < right) {
            x += step;
            if (Math.abs(Calculator.calculatePhiFunctionDerivative(x, equation, lambda)) >= 1) {
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

    public static void solution(double left, double right, double accuracy,  int equation) throws IOException {
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
        while (!verifyConvergence(left, right, equation)) {
            System.out.println("Условие сходимости с данным интервалом не выполнено.");
            System.out.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        System.out.println("Процесс решения:");
        double prev = left;
        int count = 1;
        double x = Calculator.calculatePhiFunction(prev, equation, lambda);
      //  ConsoleWorker.print(count, accuracy, prev, Calculator.calculateFunction(prev, equation), x, Math.abs(x - prev));
        count++;
        double q = Math.abs(Calculator.calculatePhiFunctionDerivative(x, equation, lambda));
        //System.out.println(q);
        while (!((q <= 0.5) && (Math.abs(x - prev) <= accuracy) || (q >= 0.5) && (Math.abs(x - prev) <= (1 - q) * accuracy / q))) {
            prev = x;
            x = Calculator.calculatePhiFunction(prev, equation, lambda);
          //  ConsoleWorker.print(count, accuracy, prev, Calculator.calculateFunction(prev, equation), x, Math.abs(x - prev));
            count++;

        }
        FileWorker.printResult(x, equation, accuracy, count - 1);
    }
}
