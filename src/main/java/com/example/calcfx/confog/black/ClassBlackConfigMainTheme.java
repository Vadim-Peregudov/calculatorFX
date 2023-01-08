package com.example.calcfx.confog.black;


import com.example.calcfx.confog.ClassConfigMainTheme;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class ClassBlackConfigMainTheme extends ClassConfigMainTheme {
    private static final String STILE_MAIN_WINDOW =
                    "-fx-background-radius: 6;"+
                    "-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);"+
                    "-fx-background-insets: 0, 0 1 1 0;";
    private static final Color COLOR_MAIN_SCENE = Color.rgb(0,0,0,0);
    private static final StageStyle STAGE_STYLE_MAIN_STAGE = StageStyle.TRANSPARENT;
    public static final int MIN_WIDTH_WINDOW = 401;
    public static final int MIN_HEIGHT_WINDOW = 630;
    private static final Color COLOR_PRESSED = Color.rgb(7, 7, 7);
    private static final Color COLOR_RElEASED = Color.rgb(33, 33, 33);
    private static final Color COLOR_INCORRECT_EXPRESSION = Color.rgb(200,200,200);
    private static final Color COLOR_CORRECT_EXPRESSION = Color.rgb(255,255,255);
    @Override
    public String getStileMainWindow() {
        return STILE_MAIN_WINDOW;
    }
    @Override
    public Color getColorMainScene() {
        return COLOR_MAIN_SCENE;
    }
    @Override
    public StageStyle getStageStyle() {
        return STAGE_STYLE_MAIN_STAGE;
    }
    @Override
    public int getMinWidthWindow() {
        return MIN_WIDTH_WINDOW;
    }
    @Override
    public int getMinHeightWindow() {
        return MIN_HEIGHT_WINDOW;
    }

    @Override
    public Color getColorPressed() {
        return COLOR_PRESSED;
    }

    @Override
    public Color getColorReleased() {
        return COLOR_RElEASED;
    }

    @Override
    public Color getColorIncorrectExpression() {
        return COLOR_INCORRECT_EXPRESSION;
    }

    @Override
    public Color getColorCorrectExpression() {
        return COLOR_CORRECT_EXPRESSION;
    }

}
