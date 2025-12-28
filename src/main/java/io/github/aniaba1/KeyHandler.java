package io.github.aniaba1;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    public boolean up, down, left, right;

    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        
        if (code == KeyCode.W) up = true;

        if (code == KeyCode.S) down = true;

        if (code == KeyCode.A) left = true;

        if (code == KeyCode.D) right = true;
    }

    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        
        if (code == KeyCode.W) up = false;

        if (code == KeyCode.S) down = false;
        
        if (code == KeyCode.A) left = false;

        if (code == KeyCode.D) right = false;
    }
}