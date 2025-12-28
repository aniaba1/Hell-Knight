package io.github.aniaba1.object;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {
        name = "Chest";
        image = new WritableImage(new Image(getClass().getResource("/objects/Chest.png").toExternalForm()).getPixelReader(), 16, 16);
    }
}
