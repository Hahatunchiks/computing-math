package com.company.IO;
import com.company.Solvers.Approximations.ApproximationFunction;
import com.company.Solvers.Point;
import java.util.ArrayList;

public class ConsoleResultsWriter implements Writer {

    @Override
    public void write(ArrayList<ApproximationFunction> functions, ArrayList<Point> table) {
        for(Point point : table){
            System.out.println("x: " + point.getX() + " y: " + point.getY() + '\n');
        }
        System.out.println('\n');
        for(ApproximationFunction f : functions){
            System.out.println(f.getFunctionName() + '\n');
            System.out.println("Values:\n");
            for(Point point : table){
                System.out.printf("x: %.3f y: %.3f \n", point.getX(),f.getFunction().apply(point.getX()));
            }
            System.out.println("SKO:\n");
            System.out.printf("%.3f\n\n",f.getDeviationMeasure());
        }
    }
}