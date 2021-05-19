package com.company.Solvers.Approximations;

import com.company.Solvers.Point;
import com.company.Solvers.SystemofLinearEquations.Matrix;

import java.util.ArrayList;

public class Logarithmic extends ApproximationFunction{

    public Logarithmic(ArrayList<Point> table){
        this.table = table;
        name = "логарфмическая функция";
    }
    @Override
    public void findDependence() {
        double a1 = 0, a2 = 0, a3 = 0, a4 = 0, a0 = table.size();

        for(Point value : table){
            a1 += Math.log(value.getX());
            a2 += Math.log(value.getX()) * Math.log(value.getX());
            a3 += Math.log(value.getX()) * value.getY();
            a4 += value.getY();
        }
        double[][] matrix_left = {{a2, a1}, {a1, a0}};
        double[] matrix_right = {a3, a4};
        double[] res = new Matrix(matrix_left, matrix_right, 2).findSolution();
        if (Double.isNaN(res[0])) {
            System.err.println("No solution for: " + name);
        } else {
            //f1*ln(x) + f2 approximation
            double f1 = res[0];
            double f2 = res[1];
            function = (Double x) -> f1 * Math.log(x) + f2;
            functionName = String.format("%.3fln(x)%+.3f : " + name, f1, f2);
            calculateS(table);
            calculateStandardDeviation();
        }
    }
}