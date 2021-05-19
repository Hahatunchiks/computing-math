package com.company;

import com.company.Methods.HalfDivision;
import com.company.Methods.Newtons;
import com.company.Methods.SimpleIteration;

import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] equations = {"-1,8*x^3 - 2.94*x^2 + 10.37*x + 5,38", "(23.9*x+6)^(1/2)-239", "cos(x)+1/2"};
    static int chosenEquation;
    static String[] methods = {"Метод половинного деления", "Метод Ньютона", "Метод простой итерации"};
    static int chosenMethod;
    static String q;
    static double left;
    static double right;
    static double accuracy;

    public static void readFromFile() throws IOException {
        System.out.println("Введите название файла.");
        String fileName = sc.next();
        File file = new File(fileName);
        while (!file.exists()) {
            System.out.println("Попробуйте ещё раз.");
            fileName = sc.next();
            file = new File(fileName);
        }
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);
            String left_s = reader.readLine();
            left = Double.parseDouble(left_s);
            String right_s = reader.readLine();
            right = Double.parseDouble(right_s);
            String accuracy_s = reader.readLine();
            accuracy = Double.parseDouble(accuracy_s);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в файле");
        }
    }

    public static void readFromConsole() {
        System.out.println("Введите левую границу интервала.");
        left = sc.nextDouble();
        System.out.println("Введите правую границу интервала.");
        right = sc.nextDouble();
        while (right - left <= 0) {
            System.out.println("Введите границы ещё раз.");
            System.out.println("Введите левую границу интервала.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интервала.");
            right = sc.nextDouble();
        }
        System.out.println("Введите погрешность вычисления.");
        accuracy = sc.nextDouble();
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Выберите уравнение:");
        for (int i = 0; i < equations.length; i++) {
            System.out.println(i + 1 + ") " + equations[i]);
        }

        System.out.println("Выберите номер уравнения");
        chosenEquation = sc.nextInt();
        while ((chosenEquation != 1) && (chosenEquation != 2) && (chosenEquation != 3)) {
            System.out.println(("Попробуйте выбрать номер уравнения ещё раз."));
            chosenEquation = sc.nextInt();
        }

        System.out.println("Выберите метод решения уравнения.");
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + 1 + ") " + methods[i]);
        }

        System.out.println("Введите номер выбранного метода.");
        chosenMethod = sc.nextInt();
        while ((chosenMethod != 1) && (chosenMethod != 2) && (chosenMethod != 3)) {
            System.out.println("Попробуйте выбрать метод решения уравнения ещё раз.");
            chosenMethod = sc.nextInt();
        }

        System.out.println("Читать исходные данные из файла? [y]/[n]");
        q = sc.next();
        while (!q.equals("y") && !q.equals("n")) {
            System.out.println("Попробуйте ещё раз.");
            q = sc.next();

        }

        if (q.equals("y")) {
            readFromFile();
        } else {
            readFromConsole();
        }

        if (chosenMethod == 1)
            HalfDivision.solution(left, right, accuracy, chosenEquation);
        if (chosenMethod == 2)
            Newtons.solution(left, right, accuracy, chosenEquation);
        if (chosenMethod == 3)
            SimpleIteration.solution(left, right, accuracy, chosenEquation);
    }
}
