package io.github.aniaba1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App {
    public Scene screen() {
        Label label = new Label("Aura Knight");
        label.setFont(new Font(24));
        Button newGame = new Button("NEW GAME");
        newGame.setOnAction(e -> {
            Stage currentStage = (Stage) newGame.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            stage.setTitle("New Game?");
           NewGameScreen root = new NewGameScreen();
            try {
                stage.setScene(root.newGame());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show(); 

        /*     Battle root = new Battle();
            try {
                stage.setScene(root.battleSequence());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();*/

        });

        Button startGame = new Button("START GAME");
        startGame.setDisable(true);
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(10, newGame, startGame);
        VBox vbox2 =  new VBox(vbox, hbox); 
        vbox2.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox(vbox2);
        hbox2.setAlignment(Pos.CENTER);
        return new Scene(hbox2, 400, 400);
    }
}


//  Scanner scanner = new Scanner(System.in);
      //  System.out.println("Welcome to Aura Knight");
      //  System.out.println("What is your name warrior");
     //   String name = scanner.nextLine();
      //  User user = new User(name);
      //  System.out.println("Hello " + user);
      //  scanner.close();