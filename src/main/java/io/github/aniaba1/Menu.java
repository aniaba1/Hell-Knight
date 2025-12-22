package io.github.aniaba1;

import javafx.application.Application;
import javafx.stage.Stage;

public class Menu extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        App root = new App();
        
        stage.setScene(root.screen());

        stage.setTitle("Menu");
        stage.show();
    }
}