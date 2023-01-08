package com.example.calcfx.model.calc;

import com.example.calcfx.model.NumberUtils;

import java.util.Stack;

public class ReversePolishNotation {
    private StringBuilder output;
    private StringBuilder input;
    private Stack<String> stack;

    public ReversePolishNotation(StringBuilder input) {
        output = new StringBuilder();
        this.input = input;
        stack = new Stack<>();
    }

    public String toPostfix() {
        String[] arrayInput = input.toString().split(" ");
        for (String str : arrayInput) {
            if (NumberUtils.isNumber(str) || isPostfixFunction(str) || isMathConstants(str)) {
                addValueInOutput(str);
            } else if (isPrefixFunction(str)) {
                stack.push(str);
            } else if (str.equals("(")) {
                stack.push(str);
            } else if (str.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    addValueInOutput(stack.pop());
                }
            } else if (isBinaryOperation(str)) {
                while (!stack.isEmpty() &&
                        (isPrefixFunction(stack.peek()) ||
                        (getOperationPriority(stack.peek()) >= getOperationPriority(str)))) {
                    addValueInOutput(stack.pop());
                }
                stack.push(str);
            }
        }
        while (!stack.isEmpty()) {
            addValueInOutput(stack.pop());
        }
        return output.toString();
    }

    private boolean isBinaryOperation(String str) {
        return switch (str) {
            case "+", "-", "*", "/","^","√","%" -> true;
            default -> false;
        };
    }

    private void addValueInOutput(String str) {
        if (str.equals("(") || str.equals(")")) {
            return;
        }
        if (output.length() == 0) {
            output.append(str);
        } else {
            output.append(" ").append(str);
        }
    }

    private int getOperationPriority(String opr) {
        return switch (opr) {
            case "+", "-" -> 10;
            case "*", "/" -> 20;
            case "^", "√" , "%" -> 30;
            default -> 0;
        };
    }

    private boolean isPostfixFunction(String str) {
        return switch (str) {
            case "!" -> true;
            default -> false;
        };
    }

    private boolean isPrefixFunction(String str) {
        return switch (str) {
            case "sin", "cos", "asin", "acos", "tan", "atan", "Ln", "Lg" -> true;
            default -> false;
        };
    }
    private boolean isMathConstants (String str){
        return str.equals("π") || str.equals("e");
    }

}
