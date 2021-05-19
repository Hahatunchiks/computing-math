package com.company.Solvers.Approximations;
import com.company.Solvers.Point;
import java.util.ArrayList;
import java.util.function.UnaryOperator;

public abstract class ApproximationFunction {
    ArrayList<Point> table;
    String name;
    UnaryOperator<Double> function;
    double deviationMeasure;
    double standardDeviation;
    String functionName;
    double correlation;

    public abstract void findDependence();


    public void calculateS(ArrayList<Point> points) {
        double deviation = 0;
        for (Point point : points) {
            deviation += Math.pow(point.getY() - function.apply(point.getX()), 2);
        }
        this.deviationMeasure = deviation;
    }

    public void calculateStandardDeviation() {
        this.standardDeviation = Math.sqrt(this.deviationMeasure / this.table.size());
    }

    public double getDeviationMeasure() {
        return deviationMeasure;
    }

    public UnaryOperator<Double> getFunction() {
        return function;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public double getCorrelation() {
        return correlation;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getName(){
        return this.name;
    }

}