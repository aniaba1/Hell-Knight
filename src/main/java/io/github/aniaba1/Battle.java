package io.github.aniaba1;

import javafx.util.Duration;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Battle {
    int number;
    int damage;
    int round = 1;
    int enemyHealth;
    String system;
    Button slash;
    Button haste;
    private boolean playerTurn = true;

    User player = new User();
    Knight knight;
    TextArea systemText;
    Label healthLabel;
    Label healthLabel2;
    Label levelLabel;
    VBox vPanel;
    VBox vbox;

    TitledPane battleName;

    Label levelLabel2;
    VBox vPanel2;
    TitledPane battleName2;
    VBox vbox2;
    Button start;
    VBox vbox3;

    User user = new User();

    public Scene battleSequence() {
        BorderPane border = new BorderPane();
        slash = new Button("Slash");
        haste = new Button("Haste");
        slash.setOnAction(new Moveset());
        haste.setOnAction(new Moveset());

        
        levelLabel = new Label("Level: " + String.valueOf(user.level));
        healthLabel = new Label("Health: " + String.valueOf(user.health));
        vPanel = new VBox(10, levelLabel, healthLabel, slash, haste);
        battleName = new TitledPane(User.userName, vPanel);
        battleName.setCollapsible(false);
        vbox = new VBox(10, battleName);

        knight = new Knight();
        levelLabel2 = new Label("Level: " + String.valueOf(knight.getLevel()));
        healthLabel2 = new Label("Health: " + String.valueOf(knight.getHealth()));
        vPanel2 = new VBox(10, levelLabel2, healthLabel2);
        battleName2 = new TitledPane(knight.getName(), vPanel2);
        battleName2.setCollapsible(false);
        vbox2 = new VBox(10, battleName2);

        start = new Button("Start");
        vbox3 = new VBox(start);
        
        systemText = new TextArea();
        systemText.setEditable(false);
        systemText.setPromptText("System . . .");
        start.setOnAction(e -> {
            vbox3.getChildren().clear();
                
            
            if (user.speed <= knight.getSpeed()) {
                enemyTurn();              
            } else if (user.speed >= knight.getSpeed()) {
                startPlayerTurn();                    
            }       
        });
        
        vbox3.setAlignment(Pos.CENTER);

        
        border.setLeft(vbox);
        border.setRight(vbox2);
        border.setCenter(vbox3);
        border.setBottom(systemText);
        return new Scene(border);
    }

    private void startPlayerTurn() {
        buttonEnabler();
        systemText.appendText("Your turn!\n");
    }

    private void endPlayerTurn() {
        buttonDisabler();
        if (user.speed <= knight.getSpeed()) {
            round++;
            systemText.appendText("Round " + round +"\n");
        }
        enemyTurn();
    }

    private void enemyTurn() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            systemText.appendText(knight.slash());

            int damage = Math.max(0, knight.getAttack() - user.defense);
            user.health -= damage;
            if (user.speed >= knight.getSpeed()) {
                round++;
                systemText.appendText("Round " + round +"\n");
            } 
            playerUpdatehealth();
            startPlayerTurn();
        });
        pause.play();
    }
 
    public void playerUpdatehealth() { 
        healthLabel.setText("Health: " + String.valueOf(user.health));
        if (user.health <= 0) {
            user.health = 0;
            Label gameOver = new Label("GAME OVER");
            vbox3.getChildren().addAll(gameOver, returnBtn());
        } 
    }

    public void playerUpdateLevel() {
        user.levelUp(knight.getExp());
        levelLabel.setText("Level: " + String.valueOf(user.level));
    }

    public void enemyUpdatehealth() { 
        healthLabel2.setText("Health: " + String.valueOf(knight.getHealth()));
        if (knight.getHealth() <= 0) {
            
            healthLabel2.setText("Health: 0");
            Label winScreen = new Label("Victory");
            vbox3.getChildren().addAll(winScreen, returnBtn());
        }
        
    }

    public Button returnBtn() {
        Button returnBtn = new Button("Exit");
        returnBtn.setOnAction(e-> {
            Stage currentStage = (Stage) returnBtn.getScene().getWindow();
            currentStage.close();
            Stage stage = new Stage();
            stage.setTitle("Run");
            Run root = new Run();
            try {
                stage.setScene(root.runner());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage.show();
        });
        return returnBtn;
    }

    private class Moveset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==slash) {
                systemText.appendText(User.userName + " uses Slash!\n");
                damage = 20;
                enemyHealth = knight.getHealth();
                enemyHealth -= damage;
                knight.setHealth(enemyHealth);
                enemyUpdatehealth();
                endPlayerTurn();             
            }
            if (event.getSource()==haste) {
                systemText.appendText(User.userName + " uses Haste!\n");
                user.speed += 10;
                endPlayerTurn();
            }
        }    
    }

    public void buttonDisabler() {
        slash.setDisable(true);
        haste.setDisable(true);
    }

    public void buttonEnabler() {
        slash.setDisable(false);
        haste.setDisable(false);
    }
}


