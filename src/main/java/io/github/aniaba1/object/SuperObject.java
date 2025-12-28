package io.github.aniaba1.object;

import io.github.aniaba1.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Rectangle;

public class SuperObject {
    public WritableImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public void draw(GraphicsContext g, Game gm) {
            int screenX = worldX - gm.player.worldX + gm.player.screenX;
            int screenY = worldY - gm.player.worldY + gm.player.screenY;

            if(worldX + gm.tileSize > gm.player.worldX - gm.player.screenX && 
                worldX - gm.tileSize < gm.player.worldX + gm.player.screenX && 
                worldY + gm.tileSize > gm.player.worldY - gm.player.screenY && 
                worldY - gm.tileSize < gm.player.worldY + gm.player.screenY) {

                g.drawImage(image, screenX, screenY, gm.tileSize, gm.tileSize);
            }
    }
} 