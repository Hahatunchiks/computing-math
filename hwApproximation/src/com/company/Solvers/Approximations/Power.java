package com.company.Solvers.Approximations;
import com.company.Solvers.Point;
import com.company.Solvers.SystemofLinearEquations.Matrix;
import java.util.ArrayList;

public class Power extends ApproximationFunction{

    public Power(ArrayList<Point> table){
        this.table = table;
        name = "Степенная функция";
    }
    @Override
    public void findDependence() {
        double a0 = table.size(), a1 = 0, a2 = 0, a3 = 0, a4 = 0;
        for(Point value : table){
            a1 += Math.log(value.getX());
            a2 += Math.log(value.getX()) * Math.log(value.getX());
            a3 += Math.log(value.getX()) * Math.log(value.getY());
            a4 += Math.log(value.getY());
        }

        double[][] matrix_left = {{a1, a2}, {a0, a1}};
        double[] matrix_right = {a3, a4};
        double[] res = new Matrix(matrix_left, matrix_right, 2).findSolution();
        if (Double.isNaN(res[0])) {
            System.err.println("No solution for: " + name);
        } else {
            //f1*ln(x) + f2 approximation
            double f1 = Math.exp(res[0]);
            double f2 = res[1];
            function = (Double x) -> f1 * Math.pow(x, f2);
            functionName = String.format("%.3fx^(%.3f) : " + name, f1, f2);
            calculateS(table);
            calculateStandardDeviation();
        }
    }
}