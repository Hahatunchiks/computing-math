package com.company.Methods.Calculations;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static final int STEPS = 30;

    public Graph(double left, double right, int EQUATION_IDENTIFIER) throws IOException {
        List<Double> xs = createXSeries(left, right);
        List<Double> ys = createYSeries(EQUATION_IDENTIFIER, left, right);
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
                .title("Graph").xAxisTitle("X").yAxisTitle("Y")
                .build();
        chart.setCustomXAxisTickLabelsFormatter((x) -> String.format("%.2f", x));
        try{
            chart.addSeries("Graph", xs, ys);
            BitmapEncoder.saveBitmap(chart, "Graph", BitmapEncoder.BitmapFormat.PNG);}catch (IllegalArgumentException e){
            System.out.println("Некорректные границы промежутка!");
            System.exit(0);
        }
    }

    public static List<Double> createXSeries(double left, double right) {
        double step = (right - left) / STEPS;
        double x = left;
        List<Double> xs = new ArrayList<>();
        while (x < right) {
            xs.add(x);
            x += step;
        }
        return xs;
    }

    public static List<Double> createYSeries(int EQUATION_IDENTIFIER, double left, double right) {
        double step = (right - left) / STEPS;
        double x = left;
        List<Double> ys = new ArrayList<>();
        while (x < right) {
            ys.add(Calculator.calculateFunction(x, EQUATION_IDENTIFIER));
            x += step;
        }
        return ys;
    }
}