package com.example.calcfx.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public enum Parentheses {
    OPENING_ROUNDED("("),
    CLOSING_ROUNDED(")"),

    OPENING_AND_CLOSING_ROUNDED("()");

    private final String value;

    Parentheses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ArrayList<Parentheses> getAllParentheses() {
        return new ArrayList<>() {{
            add(OPENING_ROUNDED);
            add(CLOSING_ROUNDED);
        }};
    }

    public static ArrayList<String> getAllParenthesesValues() {
        return getAllParentheses().stream().map(element -> element.getValue())
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
