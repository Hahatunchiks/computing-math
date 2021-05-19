package com.company.Methods;

import com.company.Methods.Calculations.Calculator;
import com.company.Methods.Calculations.Graph;
import com.company.Methods.Writers.ConsoleWorker;
import com.company.Methods.Writers.FileWorker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HalfDivision {
    static Scanner sc = new Scanner(System.in);

    public static boolean isMonotonicSection(double left, double right, int equation) {
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
    public static void solveInFile(double left, double right, int equation, double accuracy) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("/home/mike/IdeaProjects/computing-math/lab2test/files/result.txt"));
        writer.write("Процесс решения:\n");
        int count = 1;
        double x0 = (left + right) / 2;
        double f = Calculator.calculateFunction(x0, equation);
        try {
            FileWorker.printToFile(left, right, x0, f, equation, count, writer);
        } catch (IOException e){
            System.out.println("Error in solveInFile");
        }
        count++;
        while (!(Math.abs(right - left) <= accuracy || Math.abs(f) <= accuracy)) {
            if (Calculator.calculateFunction(left, equation) * Calculator.calculateFunction(x0, equation) < 0) {
                right = x0;
            } else {
                left = x0;
            }
            x0 = (left + right) / 2;
            f = Calculator.calculateFunction(x0, equation);
            try {
                FileWorker.printToFile(left, right, x0, f, equation, count, writer);
            } catch (IOException e){
                System.out.println("Error in solveInFile second");
            }
            count++;
        }
        FileWorker.printResult(x0, equation, accuracy, count - 1);

    }

    public static boolean isCorrectSection(double left, double right, int equation) {
        if ((Calculator.calculateFunction(left, equation) * Calculator.calculateFunction(right, equation) < 0)) {
            return true;
        }
        System.out.println("Не пройдена верификация существования и единственности корня на данном промежутке.");
        return false;
    }

    public static void solution(double left, double right, double accuracy, int equation) throws IOException{
        Graph graph = new Graph(left, right, equation);
        while (!isCorrectSection(left, right, equation)) {
            System.out.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        if (!isMonotonicSection(left, right, equation)) {
            System.out.println("На данном промежутке корень может быть не единственный.");
        }
        System.out.println("Выводить процесс решения в файл? [y]/[n]");
        String q = sc.next();
        while (!q.equals("y") && !q.equals("n")) {
            System.out.println("Попробуйте ещё раз.");
            q = sc.next();
        }
        if (q.equals("y")) {
            solveInFile(left, right, equation, accuracy);
            return;
        }
        System.out.println("Процесс решения:");
        int count = 1;
        double x0 = (left + right) / 2;
        double f = Calculator.calculateFunction(x0, equation);
        ConsoleWorker.print(left, right, x0, f, equation, accuracy, count);
        count++;
        while (!(Math.abs(right - left) <= accuracy || Math.abs(f) <= accuracy)) {
            if (Calculator.calculateFunction(left, equation) * Calculator.calculateFunction(x0, equation) < 0) {
                right = x0;
            } else {
                left = x0;
            }
            x0 = (left + right) / 2;
            f = Calculator.calculateFunction(x0, equation);
            ConsoleWorker.print(left, right, x0, f, equation, accuracy, count);
            count++;
        }
        FileWorker.printResult(x0, equation, accuracy, count - 1);
    }
}