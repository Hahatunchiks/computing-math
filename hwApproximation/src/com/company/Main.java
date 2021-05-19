package com.company;

import com.company.IO.*;
import com.company.Solvers.Approximations.*;
import com.company.Solvers.Point;
import com.company.Utils.Graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean flag = false;
        while (!flag) {
            System.out.println("Read from file: [y/n]");
            input = sc.nextLine();
            if (input.equals("y") || input.equals("n")) {
                flag = true;
            }
        }
        Reader reader;
        ArrayList<Point> table = new ArrayList<>();
        boolean res = false;
        if (input.equals("y")) {
            reader = new FileHandler();
            try {
                res = reader.read(table);
            } catch (IOException e) {
                System.err.println("File not found");
            }

        } else {
            reader = new ConsoleReader();
            try {
                res = reader.read(table);
            } catch (IOException e) {
                System.err.println("Wrong with input");
            }
        }

        if (res) {
            input = "";
            flag = false;
            while (!flag) {
                System.out.println("Write results in file: [y/n]");
                input = sc.nextLine();
                if (input.equals("y") || input.equals("n")) {
                    flag = true;
                }
            }
            Writer writer;
            if (input.equals("y")) {
                writer = new FileResultsWriter();
            } else {
                writer = new ConsoleResultsWriter();
            }

            ArrayList<ApproximationFunction> solved = new ArrayList<>();
            ArrayList<ApproximationFunction> functions = new ArrayList<>(Arrays.asList(new Linear(table), new Polynomial(table), new Logarithmic(table),
                    new Exponential(table), new Power(table)));
            for (ApproximationFunction f : functions) {
                f.findDependence();
                if (f.getFunctionName() != null) {
                    solved.add(f);
                }
            }
            solved.sort(Comparator.comparing(ApproximationFunction::getStandardDeviation));
            writer.write(solved, table);
            Graph graph = new Graph(table);
            graph.drawMainFrame();
            for(ApproximationFunction function : solved){
                graph.drawDependency(function);
            }
            graph.showGraph();
        } else {
            System.err.println("End with error");
        }
    }
}