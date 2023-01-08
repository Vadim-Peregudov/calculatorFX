package com.example.calcfx.model;

public class NumberUtils {
    public static boolean isNumber(String str) {
        int countDot = 0;
        for (char c : str.toCharArray()) {
            if (isDot(c)) {
                countDot += 1;
            } else if (!isDigit(c)) {
                return false;
            }
            if (countDot > 1) {
                return false;
            }
        }
        return true;
    }


    public static boolean isDigit(String digit) {
        return isDigit(digit.charAt(0));
    }

    public static boolean isDigit(char digit) {
        return digit >= '0' && digit <= '9';
    }

    public static boolean isDigitAndDot(String str) {
        return isDigit(str) || isDot(str);
    }

    public static boolean isDigitAndDot(char c) {
        return isDigit(c) || isDot(c);
    }

    public static boolean isDigitAndDot(StringBuilder sb) {
        return isDigitAndDot(sb.charAt(0));
    }

    public static boolean isDot(String str) {
        return str.equals(".");
    }

    public static boolean isDot(char c) {
        return c == '.';
    }

    public static boolean isNumberAndMathConst(String str) {
        return isNumber(str) || str.equals("π") || str.equals("e");
    }
    public static boolean isMathConst(String str) {
        return str.equals("π") || str.equals("e");
    }
    public static boolean isMathConst(StringBuilder str) {
        return str.charAt(0) == 'π' || str.charAt(0) == 'e' ;
    }
    public static boolean isMathConst(char c) {
        return c == 'π' || c == 'e' ;
    }

}
