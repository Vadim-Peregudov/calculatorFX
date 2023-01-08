package com.example.calcfx.model.expression;

import com.example.calcfx.model.MathOperations;
import com.example.calcfx.model.NumberUtils;

import java.util.*;

public class ExpressionValidation {

    public static boolean isValid(StringBuilder expression) {
        ArrayList<String> values = new ArrayList<>(List.of(expression.toString().split(" ")));

        if (values.size() < 1) {
            return false;
        } else if (parenthesisNestingCheck(values)) {
            return false;
        } else if (!numberCheck(values)) {
            return false;
        }
        return values.isEmpty();
    }

    private static void deleteMathOperation(ArrayList<String> value) {
        for (int i = 0; i < value.size(); i++) {
            if (MathOperations.getValuesMathOp().contains(value.get(i))) {
                value.remove(i);
                i--;
            }
        }
    }

    private static boolean numberCheck(ArrayList<String> values) {
        deleteMathOperation(values);
        for (int i = 0; i < values.size(); i++) {
            if (!NumberUtils.isNumberAndMathConst(values.get(i))) {
                return false;
            } else {
                values.remove(i);
                i--;
            }
        }
        return true;
    }

    private static boolean parenthesisNestingCheck(ArrayList<String> values) {
        var list = crateNestingCheckList(values);
        if (list.isEmpty()) {
            return false;
        }
        if (checkingNumberNested(list)) {
            return !checkPlacement(list);
        }
        return true;
    }

    private static boolean checkPlacement(LinkedList<Character> list) {
        int count = 0;
        for (Character character : list) {
            if (character == '(') {
                count++;
            } else {
                count--;
                if (count < 0)
                    return false;
            }
        }
        return count == 0;
    }
    private static boolean checkingNumberNested(List<Character> list) {
        int left = 1;
        int right = 1;
        for (Character c : list) {
            if (c == '(') {
                left += 1;
            } else if (c == ')') {
                right += 1;
            }
        }
        return left == right;

    }

    private static LinkedList<Character> crateNestingCheckList(ArrayList<String> values) {
        var list = new LinkedList<Character>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).equals("(")) {
                list.add('(');
                values.remove(i);
                i--;
            } else if (values.get(i).equals(")")) {
                list.add(')');
                values.remove(i);
                i--;
            }
        }
        return list;
    }

}
