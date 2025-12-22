package io.github.aniaba1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Run {
    

    public Scene runner() {
        Button battle = new Button("Battle");
        Button moveset = new Button("Moveset");
        battle.setOnAction(e -> {
            Stage currentStage = (Stage) battle.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            stage.setTitle("Battle");
            Battle root = new Battle();
            try {
                stage.setScene(root.battleSequence());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();
        });
        VBox vbox = new VBox(battle, moveset);
      //  vbox.setAlignment(Pos.CENTER);
        return new Scene(vbox, 400, 400);
    }
}
