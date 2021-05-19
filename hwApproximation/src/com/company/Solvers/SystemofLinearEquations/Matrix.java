package com.company.Solvers.SystemofLinearEquations;

public class Matrix {
    private final double[][] left_matrix;
    private final double[] right_matrix;
    private final int size;

    public Matrix(double[][] left_matrix, double[] right_matrix, int size) {
        this.left_matrix = left_matrix;
        this.right_matrix = right_matrix;
        this.size = size;
    }

    public double[] findSolution() {
        double[] result = new double[]{};
        if (size == 2) {
            double det = left_matrix[0][0] * left_matrix[1][1] - left_matrix[0][1] * left_matrix[0][1];
            double det1 = right_matrix[0] * left_matrix[1][1] - left_matrix[0][1] * right_matrix[1];
            double det2 = left_matrix[0][0] * right_matrix[1] - left_matrix[0][1] * right_matrix[0];
            result = new double[]{det1 / det, det2 / det};

        }
        if (size == 3) {
            double SX = left_matrix[0][1];
            double SXX = left_matrix[0][2];
            double SXXX = left_matrix[1][2];
            double SXXXX = left_matrix[2][2];
            double SY = right_matrix[0];
            double SXY = right_matrix[1];
            double SXXY = right_matrix[2];
            double n = left_matrix[0][0];
            double delta = (n * SXX * SXXXX) + (SX * SXXX * SXX) + (SX * SXXX * SXX) - (SXX * SXX * SXX) - (SXXX * SXXX * n) - (SX * SX * SXXXX);
            double delta1 = (SY * SXX * SXXXX) + (SXY * SXXX * SXX) + (SX * SXXX * SXXY) - (SXXY * SXX * SXX) - (SXXX * SXXX * SY) - (SXY * SX * SXXXX);
            double delta2 = (n * SXY * SXXXX) + (SX * SXXY * SXX) + (SY * SXXX * SXX) - (SXX * SXY * SXX) - (SX * SY * SXXXX) - (SXXY * SXXX * n);
            double delta3 = (n * SXX * SXXY) + (SX * SXXX * SY) + (SX * SXY * SXX) - (SXX * SXX * SY) - (SX * SX * SXXY) - (SXXX * SXY * n);
            double a = delta1 / delta;
            double b = delta2 / delta;
            double c = delta3 / delta;
            result = new double[]{c, b, a};

        }
        return result;
    }
}