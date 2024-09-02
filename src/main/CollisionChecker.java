package main;

import entity.Entity;
import entity.Entity.Direction;
import utils.Config;


public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int leftVertexPlayerCoord = entity.x + entity.collisionArea.x;
        int rightVertexPlayerCoord = entity.x + entity.collisionArea.x + entity.collisionArea.width;
        int topVertexPlayerCoord = entity.y + entity.collisionArea.y;
        int bottomVertexPlayerCoord = entity.y + entity.collisionArea.y + entity.collisionArea.height;

        // convert player coords to tile coord

        int leftVertexTileCoord = leftVertexPlayerCoord / Config.tileSize;
        int rightVertexTileCoord = rightVertexPlayerCoord / Config.tileSize;
        int topVertexTileCoord = topVertexPlayerCoord / Config.tileSize;
        int bottomVertexTileCoord = bottomVertexPlayerCoord / Config.tileSize;

        int tile1, tile2;

        switch (entity.direction) {
            case Direction.UP:
                topVertexTileCoord = (topVertexPlayerCoord - entity.speed) / Config.tileSize;

                gamePanel.debug.collisionY = topVertexTileCoord - 1;
                gamePanel.debug.collisionX1 = leftVertexTileCoord - 1;
                gamePanel.debug.collisionX2 = rightVertexTileCoord - 1;

                try {
                    tile1 = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY - 1).get(leftVertexTileCoord + gamePanel.tileManager.centreTileX - 1);
                    tile2 = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY - 1).get(rightVertexTileCoord + gamePanel.tileManager.centreTileX - 1);
                } catch(IndexOutOfBoundsException e) {
                    entity.collisionOn = true;
                    return;
                }

                if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case Direction.DOWN:
                bottomVertexTileCoord = (bottomVertexPlayerCoord + entity.speed) / Config.tileSize;

                gamePanel.debug.collisionY = bottomVertexTileCoord - 1;
                gamePanel.debug.collisionX1 = leftVertexTileCoord - 1;
                gamePanel.debug.collisionX2 = rightVertexTileCoord - 1;

                try {
                    tile1 = gamePanel.tileManager.map.get(bottomVertexTileCoord + gamePanel.tileManager.centreTileY - 1).get(leftVertexTileCoord + gamePanel.tileManager.centreTileX - 1);
                    tile2 = gamePanel.tileManager.map.get(bottomVertexTileCoord + gamePanel.tileManager.centreTileY - 1).get(rightVertexTileCoord + gamePanel.tileManager.centreTileX - 1);
                } catch(IndexOutOfBoundsException e) {
                    entity.collisionOn = true;
                    return;
                }

                if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                    entity.collisionOn = true;
                }
                break;

            case Direction.LEFT:
                break;
            case Direction.RIGHT:
                break;
            case Direction.NONE:
                return;
        }
    }
}
