package io.github.aniaba1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewGameScreen {
    public Scene newGame() {
        Label question = new Label("Would You Like to start a new game?");
        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            Stage currentStage = (Stage) yes.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            StartGame root = new StartGame();
            try {
                stage.setScene(root.game());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();
        });
        Button no =  new Button("No");
        no.setOnAction(e ->{
            Stage currentStage = (Stage) no.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            App root = new App();
            try {
                stage.setScene(root.screen());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();
        });
        VBox vbox = new VBox(question);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(10, yes, no);
        hbox.setAlignment(Pos.CENTER);
        VBox vbox2 = new VBox(vbox, hbox);
        vbox2.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(vbox2);
        hbox2.setAlignment(Pos.CENTER);
        return new Scene(hbox2, 400,400);
    }
}
