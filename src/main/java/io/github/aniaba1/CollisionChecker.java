
package io.github.aniaba1;

import io.github.aniaba1.entity.Entity;

public class CollisionChecker {
    Game gm;
    public CollisionChecker(Game gm) {
        this.gm = gm;
    }

    public void checkTile(Entity entity) {
       int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.getX());
       int entityRightWorldX = (int) (entity.worldX + entity.solidArea.getX() + entity.solidArea.getWidth());
       int entityTopWorldY = (int) (entity.worldY + entity.solidArea.getY());
       int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.getY() + entity.solidArea.getHeight());
       
        int entityLeftCol = entityLeftWorldX/gm.tileSize;
        int entityRightCol = entityRightWorldX/gm.tileSize;
        int entityTopRow = entityTopWorldY/gm.tileSize;
        int entityBottomRow = entityBottomWorldY/gm.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gm.tileSize;
                tileNum1 = gm.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gm.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gm.tileM.tile[tileNum1].collision == true || gm.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for(int i = 0; i < gm.obj.length; i++) {
            if (gm.obj[i] != null) {
                entity.solidArea.setX(entity.worldX + entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.worldY + entity.solidAreaDefaultY);

                gm.obj[i].solidArea.setX(gm.obj[i].worldX + gm.obj[i].solidArea.getX());
                gm.obj[i].solidArea.setY(gm.obj[i].worldY + gm.obj[i].solidArea.getY());

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.setY(entity.solidArea.getY() - entity.speed);
                        if (entity.solidArea.intersects(gm.obj[i].solidArea.getBoundsInLocal())) {
                            if(gm.obj[i].collision == true) {
                                entity.collisiion = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.setY(entity.solidArea.getY() + entity.speed);
                        if (entity.solidArea.intersects(gm.obj[i].solidArea.getBoundsInLocal())) {
                            if(gm.obj[i].collision == true) {
                                entity.collisiion = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.setX(entity.solidArea.getX() - entity.speed);
                        if (entity.solidArea.intersects(gm.obj[i].solidArea.getBoundsInLocal())) {
                            if(gm.obj[i].collision == true) {
                                entity.collisiion = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.setX(entity.solidArea.getX() + entity.speed);
                        if (entity.solidArea.intersects(gm.obj[i].solidArea.getBoundsInLocal())) {
                            if(gm.obj[i].collision == true) {
                                entity.collisiion = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.setX(entity.solidAreaDefaultX);
                entity.solidArea.setY(entity.solidAreaDefaultY);

                gm.obj[i].solidArea.setX(entity.solidAreaDefaultX);
                gm.obj[i].solidArea.setY(entity.solidAreaDefaultY);
            } 
        }
        return index;
    }
}
