package io.github.aniaba1.entity;

import io.github.aniaba1.Game;
import io.github.aniaba1.KeyHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

public class Player extends Entity{
    Game gm;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(Game gm, KeyHandler keyH) {
        this.gm = gm;
        this.keyH = keyH;
        screenX = gm.screenWidth/2 - (gm.tileSize/2);
        screenY = gm.screenHeight/2 - (gm.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
        setDefaultValues();
        getPlayerImage();


    }

    public void setDefaultValues() {
        worldX = gm.tileSize * 8;
        worldY = gm.tileSize * 6;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        up1 = new WritableImage(new Image(getClass().getResource("/player/Up_1.png").toExternalForm()).getPixelReader(), 16, 16);
        up2 = new WritableImage(new Image(getClass().getResource("/player/Up_2.png").toExternalForm()).getPixelReader(), 16, 16);
        down1 = new WritableImage(new Image(getClass().getResource("/player/Down_1.png").toExternalForm()).getPixelReader(), 16, 16);
        down2 = new WritableImage(new Image(getClass().getResource("/player/Down_2.png").toExternalForm()).getPixelReader(), 16, 16);
        left1 = new WritableImage(new Image(getClass().getResource("/player/Left_1.png").toExternalForm()).getPixelReader(), 16, 16);
        left2 = new WritableImage(new Image(getClass().getResource("/player/Left_2.png").toExternalForm()).getPixelReader(), 16, 16);
        right1 = new WritableImage(new Image(getClass().getResource("/player/Right_1.png").toExternalForm()).getPixelReader(), 16, 16);
        right2 = new WritableImage(new Image(getClass().getResource("/player/Right_2.png").toExternalForm()).getPixelReader(), 16, 16);
    }

    public void update() {
        if (keyH.up == true || keyH.down == true || keyH.left == true || keyH.right == true) {
            if (keyH.up == true) {
                direction = "up";
            } 
            else if (keyH.down == true) {
                direction = "down";
            } 
            else if (keyH.left == true) {
                direction = "left";
            } 
            else if (keyH.right == true) {
                direction = "right";
            }

            collisionOn = false;
            gm.cChecker.checkTile(this);

            int objIndex = gm.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (collisionOn == false) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i!= 999) {
            gm.obj[i] = null;
        }
    }

    public void draw(GraphicsContext g) {
       Image image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize);   
    }
}
