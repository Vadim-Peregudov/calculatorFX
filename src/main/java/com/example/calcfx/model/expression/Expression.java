package com.example.calcfx.model.expression;

import com.example.calcfx.model.MathOperations;
import com.example.calcfx.model.NumberUtils;
import com.example.calcfx.model.Parentheses;

public class Expression {
    private StringBuilder expression;
    private StringBuilder buffer;

    public Expression() {
        expression = new StringBuilder();
        buffer = new StringBuilder();
    }

    public void addValueBuffer(String value) {
        clearBuffer();
        buffer.append(value);
    }

    private void clearBuffer() {
        buffer.delete(0, buffer.length());
    }

    public StringBuilder getExpression() {
        return new StringBuilder(expression);
    }

    public void deleteValueCaretPosition(int position) {
        if (expression.length() > 0) {
            expression.delete(position - 1, position);
        }
    }

    public void clearExpression() {
        expression.delete(0, expression.length());
        clearBuffer();
    }

    public int getLengthBuffer() {
        if (buffer.toString().equals(Parentheses.OPENING_AND_CLOSING_ROUNDED.getValue())) {
            return 1;
        }
        return buffer.length();
    }

    public void addExpressionCaretPosition(int position) {
        expression.insert(position, buffer);
        clearBuffer();
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    public StringBuilder getExpressionWithSeparators() {

        StringBuilder expressionWithSeparators = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            if (i == 0 && i + 1 < expression.length()) {
                expressionWithSeparators.append(expression.charAt(0));
                addCharToExpressionWithSeparators(expressionWithSeparators, i);
            } else if (i + 1 < expression.length()) {
                addCharToExpressionWithSeparators(expressionWithSeparators, i);
            } else if (expression.length() == 1) {
                expressionWithSeparators.append(expression.charAt(i));
            }
        }

        return expressionWithSeparators;
    }

    private void addCharToExpressionWithSeparators(StringBuilder expressionWithSeparators, int i) {
        TypeValue type1 = getTypeValue(expression.charAt(i));
        TypeValue type2 = getTypeValue(expression.charAt(i + 1));
        if (type1.equals(type2) && type1 != TypeValue.PARENTHESES && type1 != TypeValue.MATH_OPERATION) {
            expressionWithSeparators.append(expression.charAt(i + 1));
        } else {
            expressionWithSeparators.append(" ").append(expression.charAt(i + 1));
        }
    }

    private TypeValue getTypeValue(char st) {
        if (NumberUtils.isDigitAndDot(st)) {
            return TypeValue.DIGIT_AND_DOT;
        } else if (NumberUtils.isMathConst(st)) {
            return TypeValue.MATH_CONSTANT;
        } else if (Parentheses.getAllParenthesesValues().contains(Character.toString(st))) {
            return TypeValue.PARENTHESES;
        } else if (MathOperations.getValuesMathOp().contains(Character.toString(st))) {
            return TypeValue.MATH_OPERATION;
        } else {
            return TypeValue.NOT_DEFINED;
        }
    }


    private enum TypeValue {
        DIGIT_AND_DOT,
        MATH_CONSTANT,
        MATH_OPERATION,
        PARENTHESES,
        NOT_DEFINED;
    }
}
