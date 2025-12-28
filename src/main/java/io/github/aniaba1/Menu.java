package io.github.aniaba1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu extends Application {
    public static void main(String[] args) {
        launch(args);
    }
/*     
    public void start(Stage stage) throws Exception {
        App root = new App();
        
        stage.setScene(root.screen());

        stage.setTitle("Menu");
        stage.show();
    }
*/
    public void start(Stage stage) throws Exception {
        Game root = new Game();
        Scene scene = new Scene(root, root.screenWidth, root.screenHeight, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Hell Knight");
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();
        root.setUpGame();
        root.requestFocus();
    }
}