package com.company.Solvers.Approximations;

import com.company.Solvers.Point;
import com.company.Solvers.SystemofLinearEquations.Matrix;
import java.util.ArrayList;

public class Linear extends ApproximationFunction {

    public Linear(ArrayList<Point> table) {
        this.table = table;
        name = "линейная";
    }

    @Override
    public void findDependence() {
        double a = 0.0, b = 0.0, c = 0.0, d = 0.0;
        for (Point value : table) {
            a += value.getX() * value.getX();
            b += value.getX();
            c += value.getX() * value.getY();
            d += value.getY();
        }
        double[][] matrix_left = {{a, b}, {b, table.size()}};
        double[] matrix_right = {c, d};
        double[] res = new Matrix(matrix_left, matrix_right, 2).findSolution();
        if (Double.isNaN(res[0])) {
            System.err.println("No solution for: " + name);
        } else {
            //f1x + f2 approximation
            double f1 = res[0];
            double f2 = res[1];
            function = (Double x) -> f1 * x + f2;
            functionName = String.format("%.3fx%+.3f : " + name, f1, f2);
            calculateS(table);
            calculateStandardDeviation();
            correlation = calculateCoefficientOfCorrelation();
        }
    }

    public double calculateCoefficientOfCorrelation() {
        double aX = 0, aY = 0;
        for (Point point : table) {
            aX += point.getX() / table.size();
            aY += point.getY() / table.size();
        }
        double answer, numeral = 0, denominator1 = 0, denominator2 = 0;
        for (Point point : table) {
            numeral += (point.getX() - aX) * (point.getY() - aY);
            denominator1 += Math.pow((point.getX() - aX), 2);
            denominator2 += Math.pow((point.getY() - aY), 2);
        }
        answer = numeral / Math.sqrt(denominator1 * denominator2);
        correlation = answer;
        return answer;
    }
}