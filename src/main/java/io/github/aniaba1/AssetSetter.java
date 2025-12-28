package io.github.aniaba1;

import io.github.aniaba1.object.OBJ_Chest;
import io.github.aniaba1.object.OBJ_Door;

public class AssetSetter {
    Game gm;

    public AssetSetter(Game gm) {
        this.gm = gm;
    }

    public void setObject() {
        gm.obj[0] = new OBJ_Door();
        gm.obj[0].worldX = 9 * gm.tileSize;
        gm.obj[0].worldY = 4 * gm.tileSize;

        gm.obj[1] = new OBJ_Chest();
        gm.obj[1].worldX = 9 * gm.tileSize;
        gm.obj[1].worldY = 2 * gm.tileSize;
    }
}
