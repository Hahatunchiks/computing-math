package com.company.IO;

import com.company.Solvers.Approximations.ApproximationFunction;
import com.company.Solvers.Point;

import java.util.ArrayList;

public interface Writer {
    void write(ArrayList<ApproximationFunction> functions, ArrayList<Point> table);
}
