package com.company.Solvers.Approximations;

import com.company.Solvers.Point;
import com.company.Solvers.SystemofLinearEquations.Matrix;

import java.util.ArrayList;

public class Polynomial extends ApproximationFunction {

    public Polynomial(ArrayList<Point> table) {
        this.table = table;
        name = "полиномиальная функция  2 порядка";
    }

    @Override
    public void findDependence() {
        double a1 = 0.0, a2 = 0.0, a3 = 0.0, a4 = 0.0, a5 = 0.0, a6 = 0.0, a7 = 0.0, a0 = table.size();
        for (Point point : table) {
            a1 += point.getX();
            a2 += point.getX() * point.getX();
            a3 += Math.pow(point.getX(), 3);
            a4 += Math.pow(point.getX(), 4);
            a5 += point.getY();
            a6 += point.getX() * point.getY();
            a7 += point.getX() * point.getX() * point.getY();
        }
        double[][] matrix_left = new double[][]{{a0, a1, a2}, {a1, a2, a3}, {a2, a3, a4}};
        double[] matrix_right = new double[]{a5, a6, a7};
        double[] res = new Matrix(matrix_left, matrix_right, 3).findSolution();
        if (Double.isNaN(res[0])) {
            System.err.println("No solution for: " + name);
        } else {
            //f1x^2 + f2x + f3 approximation
            double f1 = res[0];
            double f2 = res[1];
            double f3 = res[2];
            function = (Double x) -> f1 * x * x + f2 * x + f3;
            functionName = String.format("%.3fx²%+.3fx%+.3f : " + name, f1, f2, f3);
            calculateS(table);
            calculateStandardDeviation();
        }
    }
}