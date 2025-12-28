package io.github.aniaba1;

import io.github.aniaba1.entity.Player;
import io.github.aniaba1.object.SuperObject;
import io.github.aniaba1.tile.TileManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public class Game extends Pane {
    //SCREEN SETTINGS (Pixels)
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 16;
    public final int maxWorldRow = 14;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[10];
    
    Canvas canvas;
    GraphicsContext g;

    public Game() { 
        this.setOnKeyPressed(e -> keyH.keyPressed(e));
        this.setOnKeyReleased(e -> keyH.keyReleased(e));
        this.setFocusTraversable(true);
        canvas = new Canvas(screenWidth, screenHeight);
        g = canvas.getGraphicsContext2D();
        g.setImageSmoothing(false);
        getChildren().add(canvas);
        gameLoop.start();
    }

    public void setUpGame() {
        aSetter.setObject();
    }

    AnimationTimer gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update();
            render();
        }
    };

    public void update() {
        player.update();
    }

    public void render() {    
        g.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        tileM.draw(g);

        for(int i = 0; i <obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g, this);
            }
        }

        player.draw(g);
    }
}
