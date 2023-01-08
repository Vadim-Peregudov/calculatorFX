package com.example.calcfx.model;

import java.util.ArrayList;

public enum MathConstant {
    PI("π"),
    E("e");
    private final String value;

    MathConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public static ArrayList<MathConstant> getMathConstant(){
        return new ArrayList<>(){{
            add(PI);
            add(E);
        }};
    }
}
