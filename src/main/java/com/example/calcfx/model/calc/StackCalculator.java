package com.example.calcfx.model.calc;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.example.calcfx.model.MathOperations;
import com.example.calcfx.model.NumberUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

public class StackCalculator {
    private static MathContext mathContext = MathContext.DECIMAL64;

    public static String getAnswer(String expressionRPN) {
        Stack<BigDecimal> stack = new Stack<>();
        for (String value : expressionRPN.split(" ")) {
            if (NumberUtils.isNumber(value)) {
                stack.push(new BigDecimal(value));
            } else if (value.equals("π")) {
                stack.push(BigDecimalMath.pi(mathContext));
            } else if (value.equals("e")) {
                stack.push(BigDecimalMath.e(mathContext));
            } else {
                try {
                    executeMathOp(stack, value);
                }catch (RuntimeException e){
                    return "Invalid expression";
                }
            }
        }
        return stack.pop().toString();
    }

    private static void executeMathOp(Stack<BigDecimal> stack, String value) {
        if (value.equals(MathOperations.ADDITION.getMathOP())) {
            addition(stack);
        } else if (value.equals(MathOperations.SUBTRACTION.getMathOP())) {
            subtraction(stack);
        } else if (value.equals(MathOperations.MULTIPLICATION.getMathOP())) {
            multiplication(stack);
        } else if (value.equals(MathOperations.DIVISION.getMathOP())) {
            division(stack);
        } else if (value.equals(MathOperations.SQUARE_ROOT.getMathOP())) {
            squareRoot(stack);
        } else if (value.equals(MathOperations.NUMBER_TO_POW.getMathOP())) {
            exponentiation(stack);
        } else if (value.equals(MathOperations.SIN.getMathOP())) {
            functionSIN(stack);
        } else if (value.equals(MathOperations.COS.getMathOP())) {
            functionCOS(stack);
        } else if (value.equals(MathOperations.TAN.getMathOP())) {
            functionTAN(stack);
        } else if (value.equals(MathOperations.ASIN.getMathOP())) {
            functionASIN(stack);
        } else if (value.equals(MathOperations.ACOS.getMathOP())) {
            functionACOS(stack);
        } else if (value.equals(MathOperations.ATAN.getMathOP())) {
            functionATAN(stack);
        } else if (value.equals(MathOperations.LN.getMathOP())) {
            functionLN(stack);
        } else if (value.equals(MathOperations.LG.getMathOP())) {
            functionLG(stack);
        } else if (value.equals(MathOperations.FACTORIAL.getMathOP())) {
            factorial(stack);
        } else if (value.equals(MathOperations.PERCENT.getMathOP())) {
            percentageNumber(stack);
        }

    }

    private static void percentageNumber(Stack<BigDecimal> stack) {
        var value1 = stack.pop();
        var value2 = stack.pop();
        stack.push(value2.multiply(value1, mathContext).divide(BigDecimal.valueOf(100), mathContext));
    }

    private static void squareRoot(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.sqrt(stack.pop(), mathContext));
    }

    private static void factorial(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.factorial(stack.pop(), mathContext));
    }

    private static void functionLG(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.log10(stack.pop(), mathContext));
    }

    private static void functionLN(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.log(stack.pop(), mathContext));
    }

    private static void functionATAN(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.atan(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void functionACOS(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.acos(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void functionASIN(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.asin(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void functionTAN(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.tan(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void functionCOS(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.cos(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void functionSIN(Stack<BigDecimal> stack) {
        stack.push(BigDecimalMath.sin(BigDecimalMath.toRadians(stack.pop(), mathContext), mathContext));
    }

    private static void exponentiation(Stack<BigDecimal> stack) {
        var power = stack.pop();
        var number = stack.pop();
        stack.push(BigDecimalMath.pow(number, power, mathContext));
    }

    private static void division(Stack<BigDecimal> stack) {
        var value_2 = stack.pop();
        var value_1 = stack.pop();
        stack.push(value_1.divide(value_2, mathContext));
    }

    private static void multiplication(Stack<BigDecimal> stack) {
        stack.push(stack.pop().multiply(stack.pop(), mathContext));
    }

    private static void subtraction(Stack<BigDecimal> stack) {
        var value_2 = stack.pop();
        var value_1 = stack.pop();
        stack.push(value_1.subtract(value_2, mathContext));
    }

    private static void addition(Stack<BigDecimal> stack) {
        stack.push(stack.pop().add(stack.pop(), mathContext));
    }


    public static MathContext getMathContext() {
        return mathContext;
    }

    public static void setMathContext(MathContext mathContext) {
        if (mathContext == null) {
            mathContext = MathContext.DECIMAL64;
        }
        StackCalculator.mathContext = mathContext;
    }
}
