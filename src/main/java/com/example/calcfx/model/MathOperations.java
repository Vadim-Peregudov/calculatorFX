package com.example.calcfx.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public enum MathOperations {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SQUARE_ROOT("√"),
    NUMBER_TO_POW("^"),
    NUMBER_TO_SQUARE("^2"),
    SIN("sin"),
    COS("cos"),
    TAN("tan"),
    ASIN("asin"),
    ACOS("acos"),
    ATAN("atan"),
    LN("Ln"),
    LG("Lg"),
    FACTORIAL("!"),
    PERCENT("%");
    private final String mathOP;

    MathOperations(String mathOP) {
        this.mathOP = mathOP;
    }

    public String getMathOP() {
        return mathOP;
    }

    public static ArrayList<MathOperations> getListMathOp() {
        return new ArrayList<>() {{
            add(ADDITION);
            add(SUBTRACTION);
            add(MULTIPLICATION);
            add(DIVISION);
            add(SQUARE_ROOT);
            add(NUMBER_TO_POW);
            add(SIN);
            add(COS);
            add(TAN);
            add(ASIN);
            add(ACOS);
            add(ATAN);
            add(LN);
            add(LG);
            add(FACTORIAL);
            add(PERCENT);
            add(NUMBER_TO_SQUARE);
        }};
    }

    public static ArrayList<String> getValuesMathOp() {
        return getListMathOp().stream().map(element -> element.mathOP).collect(Collectors.toCollection(ArrayList::new));
    }

}
