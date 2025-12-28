package io.github.aniaba1.object;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "Door";
        image = new WritableImage(new Image(getClass().getResource("/objects/Door.png").toExternalForm()).getPixelReader(), 16, 16);
        collision = true;
    }
}
