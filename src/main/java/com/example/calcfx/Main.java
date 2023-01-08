package com.example.calcfx;

import com.example.calcfx.confog.ClassConfigMainTheme;
import com.example.calcfx.confog.black.ClassBlackConfigMainTheme;
import com.example.calcfx.controller.MainWindowController;
import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    private double xOffset;
    private double yOffset;
    ClassConfigMainTheme config = new ClassBlackConfigMainTheme();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("man-view.fxml"));
        Pane pane = fxmlLoader.load();
        initHeaderPanel(pane);

        Scene scene = new Scene(pane, config.getMinWidthWindow(), config.getMinHeightWindow(), config.getColorMainScene());

        stage.setTitle("CalcFX");
        stage.setScene(scene);
        stage.initStyle(config.getStageStyle());
        stage.show();
    }

    private void initHeaderPanel(Pane pane) {
        closeProgram(getNodeByClassAndId(pane, Circle.class, "buttonClose"), pane);
        foldingProgram(getNodeByClassAndId(pane, Circle.class, "buttonWindow"));
        settingProgram(getNodeByClassAndId(pane, Rectangle.class, "buttonHistory"), pane);
        createMoveWindow(getNodeByClassAndId(pane, Rectangle.class, "header"));
    }

    private void createMoveWindow(Node header) {
        header.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        header.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
    }

    private void settingProgram(Node buttonSetting, Pane pane) {
        buttonSetting.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (stage.getWidth() == config.getMinWidthWindow()) {
                Timeline animationWindowMagnification = createAnimationWindowMagnification(pane);
                Timeline animationWindowHeaderButtonCloseMagnification = createAnimationWindowHeaderButtonCloseMagnification(pane);
                Timeline animationWindowHeaderButtonWindowMagnification = createAnimationWindowHeaderButtonWindowMagnification(pane);
                ParallelTransition pr = new ParallelTransition(
                        animationWindowMagnification,
                        animationWindowHeaderButtonCloseMagnification,
                        animationWindowHeaderButtonWindowMagnification);
                pr.play();
            } else {
                Timeline animationWindowReduction = createAnimationWindowReduction(pane);
                Timeline animationWindowHeaderButtonCloseReduction = createAnimationWindowHeaderButtonCloseReduction(pane);
                Timeline animationWindowHeaderButtonWindowReduction = createAnimationWindowHeaderButtonWindowReduction(pane);
                ParallelTransition pr = new ParallelTransition(
                        animationWindowReduction,
                        animationWindowHeaderButtonCloseReduction,
                        animationWindowHeaderButtonWindowReduction);
                pr.play();
            }
        });

        var backButtonHooks = (Rectangle)getNodeByClassAndId(pane, Rectangle.class, "backButtonHistory");
        buttonSetting.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            backButtonHooks.setFill(config.getColorPressed());
        });
        buttonSetting.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseEvent -> {
            backButtonHooks.setFill(config.getColorReleased());
        });


    }

    private Timeline createAnimationWindowHeaderButtonWindowReduction(Pane pane) {
        Circle closeButton = (Circle) getNodeByClassAndId(pane, Circle.class, "buttonWindow");

        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(closeButton.layoutXProperty(),
                closeButton.getLayoutX()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(closeButton.layoutXProperty(),
                config.getMinWidthWindow() - 50, Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);

        return tl;
    }

    private Timeline createAnimationWindowHeaderButtonWindowMagnification(Pane pane) {
        Circle closeButton = (Circle) getNodeByClassAndId(pane, Circle.class, "buttonWindow");

        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(closeButton.layoutXProperty(),
                closeButton.getLayoutX()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(closeButton.layoutXProperty(),
                config.getMinWidthWindow() - 50 + 300, Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);
        return tl;
    }

    private Timeline createAnimationWindowHeaderButtonCloseReduction(Pane pane) {
        Circle closeButton = (Circle) getNodeByClassAndId(pane, Circle.class, "buttonClose");

        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(closeButton.layoutXProperty(),
                closeButton.getLayoutX()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(closeButton.layoutXProperty(),
                config.getMinWidthWindow() - 20, Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);

        return tl;
    }

    private Timeline createAnimationWindowHeaderButtonCloseMagnification(Pane pane) {
        Circle closeButton = (Circle) getNodeByClassAndId(pane, Circle.class, "buttonClose");

        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(closeButton.layoutXProperty(),
                closeButton.getLayoutX()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(closeButton.layoutXProperty(),
                config.getMinWidthWindow() - 20 + 300, Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);
        return tl;
    }

    private Timeline createAnimationWindowReduction(Pane pane) {
        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(stage.maxWidthProperty(),
                stage.getWidth()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(stage.maxWidthProperty(),
                config.getMinWidthWindow(), Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);

        var header = (Rectangle) getNodeByClassAndId(pane, Rectangle.class, "header");
        tl.setOnFinished(eventFinished -> {
            stage.setWidth(config.getMinWidthWindow());
            header.setWidth(stage.getWidth());
        });
        return tl;
    }

    private Timeline createAnimationWindowMagnification(Pane pane) {
        KeyFrame kStart = new KeyFrame(Duration.ZERO, new KeyValue(stage.minWidthProperty(),
                stage.getWidth()));
        KeyFrame kFinish = new KeyFrame(Duration.millis(1000), new KeyValue(stage.minWidthProperty(),
                stage.getWidth() + 300, Interpolator.EASE_BOTH));

        Timeline tl = new Timeline(kStart, kFinish);

        var header = (Rectangle) getNodeByClassAndId(pane, Rectangle.class, "header");

        tl.setOnFinished(eventFinished -> {
            stage.setWidth(config.getMinWidthWindow() + 300);
            header.setWidth(stage.getWidth());
        });
        return tl;
    }

    private void foldingProgram(Node buttonWindow) {
        buttonWindow.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            stage.setIconified(true);
        });
    }

    private void closeProgram(Node buttonClose, Pane pane) {
        buttonClose.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            FadeTransition fx = new FadeTransition(new Duration(250), pane);
            fx.setFromValue(1.0);
            fx.setToValue(0.0);
            fx.play();
            fx.setOnFinished(eventClose -> stage.close());
        });
    }

    private Node getNodeByClassAndId(Pane pane, Class<? extends Node> clazz, String idName) {
        var circle = pane.getChildren().stream().filter(node ->
                node.getClass().equals(clazz) && node.getId() != null && node.getId().equals(idName)).findFirst();
        if (circle.isPresent()) {
            return circle.get();
        }
        throw new NullPointerException("Is Circle by id = " + idName + ", is NULL");
    }


    public static void main(String[] args) {
        launch();
    }


}