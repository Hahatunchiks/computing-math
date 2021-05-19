package com.company.IO;
import com.company.Solvers.Approximations.ApproximationFunction;
import com.company.Solvers.Point;

import java.io.*;
import java.util.ArrayList;

public class FileResultsWriter implements Writer {

    @Override
    public void write(ArrayList<ApproximationFunction> functions, ArrayList<Point> table) {

        try (FileWriter writer = new FileWriter("/home/mike/IdeaProjects/computing-math/hwApproximation/src/com/company/sourse/out")) {
            for(Point point : table){
                writer.write("x: " + point.getX() + " y: " + point.getY() + '\n');
            }
            writer.write('\n');
            for(ApproximationFunction f : functions){
                writer.write(f.getFunctionName() + '\n');
                writer.write("Values:\n");
                for(Point point : table){
                    writer.write("x: " + point.getX() + " y: " + f.getFunction().apply(point.getX()) + '\n');
                }
                writer.write("SKO:\n");
                writer.write(f.getDeviationMeasure() + "\n\n");
                if(f.getName().equals("линейная")){
                    writer.write("Correlation: \n" + f.getCorrelation() + "\n\n");
                }
            }
            writer.write("Best:\n");
            writer.write(functions.get(0).getName() + '\n');
        } catch (IOException e) {
            System.err.println("How can I do this?");
        }
    }
}