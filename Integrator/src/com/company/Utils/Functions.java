package com.company.Utils;
import static java.lang.Math.*;
import java.util.function.Function;

public enum Functions {
    function1(x -> 2 * pow(x, 3) - 3 * pow(x, 2) + 5 * x - 9, "∫(2x^3-3x^2+5x-9)dx", false),
    function2(x -> sin(x) / x, "∫(sin(x)/x)dx", false),
    function3(x -> 1 / (x*x), "∫(1/x^2)dx", true);
    private final Function<Double, Double> function;
    private final String text;
    private final boolean broken;

    Functions(Function<Double, Double> function, String text, boolean broken) {
        this.function = function;
        this.text = text;
        this.broken = broken;
    }

    public String getText() {
        return text;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public boolean isBroken() {
        return broken;
    }
}

