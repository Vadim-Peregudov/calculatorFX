package com.example.calcfx.model;

import com.example.calcfx.model.expression.Expression;

public class Caret {
    private int position;
    private Expression expression;

    public Caret(Expression expression) {
        this.expression = expression;
        position = 0;

    }

    public int getPosition() {
        return position;
    }

    public void movePositionLeft(){
        if (position <= 0) {
            return;
        }
        position -= 1;
    }

    public void movePositionRight(){
        if (position >= expression.getExpression().length()) {
            return;
        }
        position += 1;
    }

    public void setStartPosition (){
        position = 0;
    }
    public void movePositionRight (int l){
        position += l;
    }


}
