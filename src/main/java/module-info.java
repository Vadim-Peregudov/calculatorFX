module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires ch.obermuhlner.math.big;

    exports com.example.calcfx;
    exports com.example.calcfx.controller;
    opens com.example.calcfx.controller to javafx.fxml;
}