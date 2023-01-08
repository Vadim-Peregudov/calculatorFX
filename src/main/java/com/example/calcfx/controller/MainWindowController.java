package com.example.calcfx.controller;

import com.example.calcfx.confog.ClassConfigMainTheme;
import com.example.calcfx.confog.black.ClassBlackConfigMainTheme;
import com.example.calcfx.model.*;
import com.example.calcfx.model.calc.ReversePolishNotation;
import com.example.calcfx.model.calc.StackCalculator;
import com.example.calcfx.model.expression.Expression;
import com.example.calcfx.model.expression.ExpressionValidation;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;

import java.util.ResourceBundle;


public class MainWindowController implements Initializable {
    // Action zone
    public Rectangle buttonAnswer;
    public Rectangle buttonLeft;
    public Rectangle buttonRight;
    public Rectangle buttonDeleteAll;
    public Rectangle buttonDeleteOne;
    public Rectangle buttonMultiplication;
    public Rectangle buttonHistory;
    public Rectangle buttonDivision;
    public Rectangle buttonAddition;
    public Rectangle buttonSubtraction;
    public Rectangle button1;
    public Rectangle button2;
    public Rectangle button3;
    public Rectangle button4;
    public Rectangle button5;
    public Rectangle button6;
    public Rectangle button7;
    public Rectangle button8;
    public Rectangle button9;
    public Rectangle button0;
    public Rectangle buttonDot;
    public Rectangle buttonHooks;
    // Back button
    public Rectangle backButtonDeleteAll;
    public Rectangle backButtonDeleteOne;
    public Rectangle backButtonMultiplication;
    public Rectangle backButtonDivision;
    public Rectangle backButtonAddition;
    public Rectangle backButtonSubtraction;
    public Rectangle backButton1;
    public Rectangle backButton2;
    public Rectangle backButton3;
    public Rectangle backButton4;
    public Rectangle backButton5;
    public Rectangle backButton6;
    public Rectangle backButton7;
    public Rectangle backButton8;
    public Rectangle backButton9;
    public Rectangle backButton0;
    public Rectangle backButtonDot;
    public Rectangle backButtonLeft;
    public Rectangle backButtonRight;
    public Rectangle backButtonAnswer;
    public Rectangle backButtonHooks;
    public TextArea labelExpression;
    public Label answer;

    public ListView<String> historyList;
    public Label deg;
    public Label count;
    // field
    private boolean statusPressedButton = false;
    private float xPositionPressedButton = 0;
    private float yPositionPressedButton = 0;
    private long timeStartMoveCursor = 0;
    private final Expression expression = new Expression();
    private final Caret caret = new Caret(expression);
    private final ClassConfigMainTheme config = new ClassBlackConfigMainTheme();
    private boolean isValidExpression = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        initButton(button0, backButton0, "0", "00", "000", "");
        initButton(button1, backButton1, "1", MathOperations.SIN.getMathOP(), MathOperations.COS.getMathOP(), "");
        initButton(button2, backButton2, "2", MathOperations.ASIN.getMathOP(), MathOperations.ACOS.getMathOP(), "");
        initButton(button3, backButton3, "3", MathOperations.TAN.getMathOP(), MathOperations.ATAN.getMathOP(), "");
        initButton(button4, backButton4, "4", "", "", "");
        initButton(button5, backButton5, "5", "", "", "");
        initButton(button6, backButton6, "6", "", "", "");
        initButton(button7, backButton7, "7", MathOperations.FACTORIAL.getMathOP(), "", "");
        initButton(button8, backButton8, "8", MathOperations.LN.getMathOP(), MathOperations.LG.getMathOP(), "");
        initButton(button9, backButton9, "9", "π", "e", "");
        initButton(buttonDot, backButtonDot, ".", "", "", "");
        initButton(buttonAddition, backButtonAddition, MathOperations.ADDITION.getMathOP(), "", "", "");
        initButton(buttonDivision, backButtonDivision, MathOperations.DIVISION.getMathOP(), MathOperations.PERCENT.getMathOP(), MathOperations.SQUARE_ROOT.getMathOP(), "");
        initButton(buttonMultiplication, backButtonMultiplication, MathOperations.MULTIPLICATION.getMathOP(), MathOperations.NUMBER_TO_POW.getMathOP(), MathOperations.NUMBER_TO_SQUARE.getMathOP(), "");
        initButton(buttonSubtraction, backButtonSubtraction, MathOperations.SUBTRACTION.getMathOP(), "", "", "");
        initButton(buttonHooks, backButtonHooks, Parentheses.OPENING_AND_CLOSING_ROUNDED.getValue(), Parentheses.OPENING_ROUNDED.getValue(), Parentheses.CLOSING_ROUNDED.getValue(), "");

        initButtonDeleteAll(buttonDeleteAll, backButtonDeleteAll);
        initButtonAnswer(buttonAnswer, backButtonAnswer);
        initButtonDeleteOne(buttonDeleteOne, backButtonDeleteOne);

        initButtonMove(buttonLeft, backButtonLeft, -1);
        initButtonMove(buttonRight, backButtonRight, 1);

        initLabelExpression();

    }

    private void initLabelExpression() {
        initLabelExpressionKeyboard();
        initLabelExpressionContextMenu();
    }

    private void initLabelExpressionKeyboard() {
        labelExpression.addEventHandler(KeyEvent.ANY, keyEvent -> {
            labelExpression.setText(expression.toString());
            setCaretPosition();
        });
    }

    private void initLabelExpressionContextMenu() {
        labelExpression.setContextMenu(new ContextMenu());
    }

    private void initButtonMove(Rectangle buttonLeft, Rectangle backButtonLeft, int i) {
        buttonLeft.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> backButtonLeft.setFill(config.getColorPressed()));
        buttonLeft.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButtonLeft.setFill(config.getColorReleased());
            if (i > 0) {
                moveRight();
            } else {
                moveLeft();
            }
            setCaretPosition();
        });

    }

    private void setCaretPosition() {
        labelExpression.positionCaret(caret.getPosition());
    }

    private void moveLeft() {
        caret.movePositionLeft();
    }

    private void moveRight() {
        caret.movePositionRight();
    }

    private void initButtonAnswer(Rectangle actionButton, Rectangle backButton) {
        actionButton.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> backButton.setFill(config.getColorPressed()));
        actionButton.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButton.setFill(config.getColorReleased());
            if (isValidExpression) {
                String answerExpression = StackCalculator.getAnswer(new ReversePolishNotation(expression.getExpressionWithSeparators()).toPostfix());
                answer.setText(answerExpression);
            }
        });
    }

    private void initButtonDeleteOne(Rectangle actionButton, Rectangle backButton) {
        actionButton.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> backButton.setFill(config.getColorPressed()));
        actionButton.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButton.setFill(config.getColorReleased());
            expression.deleteValueCaretPosition(caret.getPosition());

            initHeightLabelExpression();

            labelExpression.setText(expression.toString());
            checkExpression(expression);

            ChangePositionCaretWhenRemoved();
            labelExpression.positionCaret(caret.getPosition());
        });
    }

    private void ChangePositionCaretWhenRemoved() {
        moveLeft();
    }


    private void initButtonDeleteAll(Rectangle actionButton, Rectangle backButton) {
        actionButton.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> backButton.setFill(config.getColorPressed()));
        actionButton.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButton.setFill(config.getColorReleased());
            expression.clearExpression();
            initHeightLabelExpression();
            labelExpression.setText(expression.toString());
            checkExpression(expression);
            caret.setStartPosition();
        });
    }


    private void initButton(Rectangle actionButton, Rectangle backButton, String valueCenter, String valueUp, String valueDown, String valueLeft) {
        initButtonMouseEventPressed(actionButton, backButton, valueCenter);
        initButtonMouseEventDragged(actionButton, valueUp, valueDown, valueLeft);
        iniButtonMouseEventReleased(actionButton, backButton);

    }


    private void initButtonMouseEventPressed(Rectangle actionButton, Rectangle backButton, String valueCenter) {
        actionButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            backButton.setFill(config.getColorPressed());
            xPositionPressedButton = (float) event.getScreenX();
            yPositionPressedButton = (float) event.getScreenY();

            timeStartMoveCursor = System.currentTimeMillis();
            expression.addValueBuffer(valueCenter);
        });
    }

    private void initButtonMouseEventDragged(Rectangle actionButton, String valueUp, String valueDown, String valueLeft) {
        actionButton.addEventHandler(MouseEvent.MOUSE_DRAGGED, mouseEvent -> {
            if (!statusPressedButton) {
                long timeNowMoveCursor = System.currentTimeMillis();
                if (timeNowMoveCursor - timeStartMoveCursor > 100) {
                    if (mouseEvent.getScreenY() - yPositionPressedButton < -40) {
                        if (!valueUp.equals(""))
                            expression.addValueBuffer(valueUp);

                        statusPressedButton = true;
                    } else if (mouseEvent.getScreenY() - yPositionPressedButton > 40) {
                        if (!valueDown.equals(""))
                            expression.addValueBuffer(valueDown);

                        statusPressedButton = true;
                    } else if (mouseEvent.getScreenX() - xPositionPressedButton < -40) {
                        if (!valueLeft.equals(""))
                            expression.addValueBuffer(valueLeft);

                        statusPressedButton = true;
                    }
                }
            }
        });

    }

    private void iniButtonMouseEventReleased(Rectangle actionButton, Rectangle backButton) {
        actionButton.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButton.setFill(config.getColorReleased());
            resetValue();

            int lengthBuffer = expression.getLengthBuffer();
            expression.addExpressionCaretPosition(caret.getPosition());
            initHeightLabelExpression();

            labelExpression.setText(expression.toString());
            checkExpression(expression);
            addExpressionHistoryList();

            caret.movePositionRight(lengthBuffer);
            setCaretPosition();
        });
    }

    private void checkExpression(Expression e) {
        if (ExpressionValidation.isValid(e.getExpressionWithSeparators())) {
            labelExpression.setStyle("-fx-text-fill: rgb(" + config.getColorCorrectExpression().getRed() * 255 + ", " + config.getColorCorrectExpression().getGreen() * 255 + ", " + config.getColorCorrectExpression().getBlue() * 255 + ")");
            isValidExpression = true;
        } else {
            labelExpression.setStyle("-fx-text-fill: rgb(" + config.getColorIncorrectExpression().getRed() * 255 + ", " + config.getColorIncorrectExpression().getGreen() * 255 + ", " + config.getColorIncorrectExpression().getBlue() * 255 + ")");
            isValidExpression = false;
        }

    }

    private void initHeightLabelExpression() {
        if (expression.toString().length() > 66) {
            int height = 109 + ((expression.toString().length() - 66) / 22 + 1) * 35;
            labelExpression.setPrefHeight(height);
        } else {
            labelExpression.setPrefHeight(109);
        }
    }


    private void resetValue() {
        statusPressedButton = false;
        xPositionPressedButton = 0;
        yPositionPressedButton = 0;
        timeStartMoveCursor = 0;
    }

    private void addExpressionHistoryList() {
        historyList.getItems().add(0, expression.toString());
    }

}