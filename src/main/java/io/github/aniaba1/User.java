package io.github.aniaba1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class User {
    static String userName;
    int level = 1;
    int exp;
    int health = 25;
    int speed = 10;
    int defense = 5;
    int aura = 100;
    int damage;

    int expCap[] = {10, 15, 25, 35, 50, 65, 80, 100,
        120, 140, 160, 180, 200, 220, 240,
        260, 280, 300, 320, 340};

    public int levelUp(int exp) {
      this.exp = exp;
      if (this.exp >= expCap[this.level]) {
        level++;
      }
      return level;
    }
}
