package io.github.aniaba1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGame {
    public Scene game() {
       //
        Label question = new Label("What is your name warrior?");
        TextField namplate = new TextField();
        VBox vbox = new VBox(question, namplate);

        namplate.setOnAction (e -> {
            User.userName = namplate.getText();
            
            vbox.getChildren().clear();
            Label greetings = new Label("Hello Aura Knight " + User.userName + "\n\nAre you ready to begin?");
            Button yes = new Button("Yes");
            yes.setOnAction(event -> {
            Stage currentStage = (Stage) yes.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            Run root = new Run();
            try {
                stage.setScene(root.runner());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();
            });   

            vbox.getChildren().addAll(greetings, yes);
        });
        namplate.setMinWidth(50);
        namplate.setPrefWidth(50);
        namplate.setMaxWidth(50);

        
        
        vbox.setAlignment(Pos.CENTER);
        
        return new Scene(vbox, 400, 400);
    }
}