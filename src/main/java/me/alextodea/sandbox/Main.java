package me.alextodea.sandbox;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import me.alextodea.sandbox.ui.UserInterface;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        UserInterface ui = new UserInterface();
        BorderPane layout = ui.getLayout();

        Scene scene = new Scene(layout, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
