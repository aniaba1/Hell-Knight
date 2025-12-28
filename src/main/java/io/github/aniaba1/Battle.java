package io.github.aniaba1;

import javafx.util.Duration;

import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
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
    int buttonSpam = 0;
    boolean death;
    boolean playerDodge;
    boolean enemyDodge;

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

    //The battle UI
    public Scene battleSequence() {
        BorderPane border = new BorderPane();
        
        //Player Button Moves
        slash = new Button("Slash");
        haste = new Button("Haste");
        slash.setOnAction(new Moveset());
        haste.setOnAction(new Moveset());

        //Player UI
        levelLabel = new Label("Level: " + String.valueOf(user.level));
        healthLabel = new Label("Health: " + String.valueOf(user.health));
        vPanel = new VBox(10, levelLabel, healthLabel, slash, haste);
        battleName = new TitledPane(User.userName, vPanel);
        battleName.setCollapsible(false);
        vbox = new VBox(10, battleName);

        //Enemy UI
        knight = new Knight();
        levelLabel2 = new Label("Level: " + String.valueOf(knight.getLevel()));
        healthLabel2 = new Label("Health: " + String.valueOf(knight.getHealth()));
        vPanel2 = new VBox(10, levelLabel2, healthLabel2);
        battleName2 = new TitledPane(knight.getName(), vPanel2);
        battleName2.setCollapsible(false);
        vbox2 = new VBox(10, battleName2);

        //Start Battle Button
        start = new Button("Start");
        vbox3 = new VBox(start);
        
        //Displays battle outcome
        systemText = new TextArea();
        systemText.setEditable(false);
        systemText.setPromptText("System . . .");
        buttonDisabler();
        
        //Action of start button
        start.setOnAction(e -> {
            //Start button disappears
            vbox3.getChildren().clear();
            
            //Game start
            systemText.appendText("Round " + round +"\n");
            if (user.speed <= knight.getSpeed()) {
                enemyTurn();              
            } 
            else if (user.speed >= knight.getSpeed()) {
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

    //Start Player Turns
    private void startPlayerTurn() {
        //Enables use of moves
        buttonEnabler();
        //Displays User turn
        systemText.appendText("Your turn!\n");
    }
    
    private void endPlayerTurn() {
        //Disables use of moves
        buttonDisabler();
        //If enemy is faster start new round
        if (user.speed <= knight.getSpeed()) {
            roundStarter();
        } 
        //Start enemy turn
        enemyTurn();
    }

    //Start Enemy Turns
    private void enemyTurn() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            //Display knight move
            systemText.appendText(knight.slash());
            playerdodge();
            //If dodge false Enemy is daelt damage otherwise player turn
            if (playerDodge == false) {
                int damage = Math.max(0, knight.getAttack() - user.defense);
                user.health -= damage;
                playerUpdatehealth();
                startPlayerTurn();
                //If player is faster enemy ends the round
                if (user.speed >= knight.getSpeed()) {
                    roundStarter();
                } 
            }
            //If enemy dodges start player turn
            else if (playerDodge == true) {
                startPlayerTurn();
            }
        });
        pause.play();
    }

    //Update player health
    public void playerUpdatehealth() { 
        //Changes Health Label
        healthLabel.setText("Health: " + String.valueOf(user.health));
        //If player is dead Game is over exit battle
        if (user.health <= 0) {
            death = true;
            buttonDisabler();
            healthLabel.setText("Health: 0");
            Label gameOver = new Label("GAME OVER");
            vbox3.getChildren().addAll(gameOver, returnBtn());
        } 
    }

    //Update enemy health
    public void enemyUpdatehealth() {
        //Changes Health Label
        healthLabel2.setText("Health: " + String.valueOf(knight.getHealth()));
        //If Enemy is dead Game is over exit battle
        if (knight.getHealth() <= 0) {
            buttonDisabler();
            healthLabel2.setText("Health: 0");
            Label winScreen = new Label("Victory");
            vbox3.getChildren().addAll(winScreen, returnBtn());
        }   
    }

    //Update player level
    public void playerUpdateLevel() {
        user.levelUp(knight.getExp());
        levelLabel.setText("Level: " + String.valueOf(user.level));
    }

    //Updates round 
    public void roundStarter() {
        //Both player and enemy are alive update round
        if (user.health > 0 || knight.getHealth() > 0) {
            round++;
            systemText.appendText("Round " + round +"\n");
        } 
        //If either die
        else if (death == true) {
            systemText.appendText("You Lose");
        }
        else if (knight.getHealth() < 0) {
            systemText.appendText("You Win!");
        }
    }

    public void death() {

    }

    //Player Dodge Mechanic
    public void playerdodge() {
        Random rand = new Random();
        //Probability logic player is faster than enemy
        if (user.speed > knight.getSpeed()) {
            //Set range for probability
            int r = rand.nextInt(user.speed); //0-Player speed 
            //How to probability works
            //Ex. Player speed = 40, enemy speed = 25
            //40-25=15 62.5%
            if (r >= knight.getSpeed()) {
                systemText.appendText(User.userName+ " has dodged\n");
                playerDodge = true;
                roundStarter();
                return;
            } 
            else if (r < knight.getSpeed()) {
                playerDodge = false;
                return;
            }
        } 
        else if (user.speed < knight.getSpeed()) {
            playerDodge = false;
            return;
        }
    }

    public void enemyDodge() {
        Random rand = new Random();    
        if (user.speed < knight.getSpeed()) {
            int r = rand.nextInt(knight.getSpeed());
            if (r >= user.speed) {
                systemText.appendText(knight.getName() + " has dodged\n");
                enemyDodge = true;
                roundStarter();
                enemyTurn();
                return;
            } 
            else if (r < user.speed) {
                enemyDodge = false;
                return;
            }
        }
        else if (knight.getSpeed() < user.speed) {
            enemyDodge = false;
            return;
        }  
    }

    //If you win or lose exit to the previous scene
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

    //Moveset private class
    private class Moveset implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //Slash Move
            if (event.getSource()==slash) {
                systemText.appendText(User.userName + " uses Slash!\n");
                enemyDodge();
                if (enemyDodge == false) {
                    damage = 20;
                    enemyHealth = knight.getHealth();
                    enemyHealth -= damage = Math.max(0, damage - knight.getDefense());
                    knight.setHealth(enemyHealth);
                    enemyUpdatehealth();
                   /*  if (enemyHealth == 0) {
                        roundStarter();
                        return;
                    }*/
                    endPlayerTurn(); 
                }           
            }
            //Haste Button
            if (event.getSource()==haste) {
                systemText.appendText(User.userName + " uses Haste!\n");
                user.speed += 10;
                if (user.speed > knight.getSpeed()) {
                    if (buttonSpam >= 1) { 
                        endPlayerTurn();
                        return;
                    }
                    roundStarter();
                    buttonSpam++;
                    startPlayerTurn();
                    return;
                } 
                else {
                    endPlayerTurn();
                    buttonSpam--;
                    buttonSpam = 0;
                }
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


