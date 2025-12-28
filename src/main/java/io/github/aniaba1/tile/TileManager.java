package io.github.aniaba1.tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.github.aniaba1.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class TileManager {
    Game gm;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(Game gm) {
        this.gm = gm;
        tile = new Tile[10];
        mapTileNum = new int[gm.maxWorldCol][gm.maxWorldRow];
        getTileImage();
        loadMap("/maps/map1.txt");
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = new WritableImage(new Image(getClass().getResource("/tiles/Hell_Grass_1.png").toExternalForm()).getPixelReader(), 16, 16);

        tile[1] = new Tile();
        tile[1].image = new WritableImage(new Image(getClass().getResource("/tiles/Hell_Grass_2.png").toExternalForm()).getPixelReader(), 16, 16);

        tile[2] = new Tile();
        tile[2].image = new WritableImage(new Image(getClass().getResource("/tiles/Hell_Flowers.png").toExternalForm()).getPixelReader(), 16, 16);

        tile[3] = new Tile();
        tile[3].image = new WritableImage(new Image(getClass().getResource("/tiles/Underdeveloped_Hell_Flowers.png").toExternalForm()).getPixelReader(), 16, 16);

        tile[4] = new Tile();
        tile[4].image = new WritableImage(new Image(getClass().getResource("/tiles/Wall.png").toExternalForm()).getPixelReader(), 16, 16);
        tile[4].collision = true;

        tile[5] = new Tile();
        tile[5].image = new WritableImage(new Image(getClass().getResource("/tiles/Shaded_Wall.png").toExternalForm()).getPixelReader(), 16, 16);
        tile[5].collision = true;

       

        tile[7] = new Tile();
        tile[7].image = new WritableImage(new Image(getClass().getResource("/tiles/Room_Floor.png").toExternalForm()).getPixelReader(), 16, 16);

        tile[8] = new Tile();
        tile[8].image = new WritableImage(new Image(getClass().getResource("/tiles/Lava.png").toExternalForm()).getPixelReader(), 16, 16);
        tile[8].collision = true;
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gm.maxWorldCol && row < gm.maxWorldRow) { 
                String line = br.readLine();
                while (col < gm.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gm.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {

        }
    }

    public void draw(GraphicsContext g) {
        int worldCol = 0;
        int worldRow = 0;
    
        while (worldCol < gm.maxWorldCol && worldRow < gm.maxWorldRow) {
            int titleNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gm.tileSize;
            int worldY = worldRow * gm.tileSize;
            int screenX = worldX - gm.player.worldX + gm.player.screenX;
            int screenY = worldY - gm.player.worldY + gm.player.screenY;

            if(worldX + gm.tileSize > gm.player.worldX - gm.player.screenX && 
                worldX - gm.tileSize < gm.player.worldX + gm.player.screenX && 
                worldY + gm.tileSize > gm.player.worldY - gm.player.screenY && 
                worldY - gm.tileSize < gm.player.worldY + gm.player.screenY) {

            g.drawImage(tile[titleNum].image, screenX, screenY, gm.tileSize, gm.tileSize);
        }

            worldCol++;

            if(worldCol == gm.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
