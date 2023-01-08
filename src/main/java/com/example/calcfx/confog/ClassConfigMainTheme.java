package com.example.calcfx.confog;


import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public abstract class ClassConfigMainTheme {
    public abstract String getStileMainWindow();
    public abstract Color getColorMainScene();
    public abstract StageStyle getStageStyle();
    public abstract int getMinWidthWindow();
    public abstract int getMinHeightWindow();
    public abstract Color getColorPressed();
    public abstract Color getColorReleased();
    public abstract Color getColorIncorrectExpression();
    public abstract Color getColorCorrectExpression();
}
