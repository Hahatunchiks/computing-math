package com.company;

import com.company.Methods.*;
import com.company.Utils.Functions;
import com.company.Utils.MethodType;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static Double left;
    private static Double right;
    private static Double accuracy;
    private static Method method;

    public static void setMethod(int number) {
        switch (number) {
            case 1 -> method = new RectangleLeftMethod(left, right, accuracy);
            case 2 -> method = new RectangleMiddleMethod(left, right, accuracy);
            case 3 -> method = new RectangleRightMethod(left, right, accuracy);
            case 4 -> method = new SimpsonMethod(left, right, accuracy);
            case 5 -> method = new TrapeziumMethod(left, right, accuracy);
        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            System.out.println(i + 1 + ") " + Functions.values()[i].getText());
        }
        int functionNumber = sc.nextInt();
        while ((functionNumber != 1) && (functionNumber != 2) && (functionNumber != 3)) {
            System.out.println("Неправильно введён номер функции!");
            functionNumber = sc.nextInt();
        }
        Functions function = Functions.values()[functionNumber - 1];
        try {
            System.out.println("Введите левую границу интегрирования.");
            left = sc.nextDouble();
            System.out.println("Введите правую границу интегрирования.");
            right = sc.nextDouble();
            System.out.println("Введите точность вычисления.");
            accuracy = sc.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Проблемы с вводимыми значениями!");
            System.exit(0);
        }
        System.out.println("Выберите метод решения.");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + 1 + ") " + MethodType.values()[i].getName());
        }
        int methodNumber = sc.nextInt();
        while ((methodNumber != 1) && (methodNumber != 2) && (methodNumber != 3) && (methodNumber != 4) && (methodNumber != 5)) {
            System.out.println("Неправильно введён номер метода!");
            methodNumber = sc.nextInt();
        }
        setMethod(methodNumber);

        method.start(function);
    }



}