package com.company.Methods;

import com.company.Utils.Functions;

public abstract class Method {
    protected double left;
    protected double right;
    protected double accuracy;
    final int N = 4;
    final double DX = 1e-10;
    final double BROKEN_POINT = 0;

    public Method(double left, double right, double accuracy) {
        this.left = left;
        this.right = right;
        this.accuracy = accuracy;
    }

    public abstract double solve(Functions function);

    public void start(Functions functions) {
        if (functions.isBroken() && left <= BROKEN_POINT && right >= BROKEN_POINT) {
            if (!Double.isFinite(functions.getFunction().apply(BROKEN_POINT))) {
                System.out.println("Предела не существует");
            } else {
                double tmpLeft = left;
                double tmpRight = right;
                this.right = BROKEN_POINT - DX*1000000;
                double leftResult = solve(functions);
                this.right = tmpRight;
                this.left = BROKEN_POINT + DX*1000000;
                double rightResult = solve(functions);
                this.left = tmpLeft;
                print(leftResult + rightResult);
            }
        } else {
            print(solve(functions));
        }
    }

    public void print(double answer) {
        System.out.println("Ответ:");
        System.out.println("Значение интеграла:" + answer);
    }

    public void intermediatePrint(double answer, int n, double abs, boolean accuracyAchieved) {
        System.out.println("Количество разбиений: " + n + ".");
        System.out.println("Значение интеграла: " + answer + ".");
        System.out.println("Модуль разности: " + abs + ".");
        if (accuracyAchieved) {
            System.out.println("Точность достигнута.\n");
        } else {
            System.out.println("Точность не достигнута.\n");
        }
    }
}

