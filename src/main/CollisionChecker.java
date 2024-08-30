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

                System.out.println(topVertexPlayerCoord);
                System.out.println(leftVertexPlayerCoord);
                System.out.println(topVertexTileCoord);
                System.out.println(leftVertexTileCoord);
                System.out.println("-------------------");


                try { // if the index is out of bounds then it is because it is checking the border
                    tile1 = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY).get(leftVertexTileCoord + gamePanel.tileManager.centreTileX);
                    tile2 = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY).get(rightVertexTileCoord + gamePanel.tileManager.centreTileX);
                } catch(IndexOutOfBoundsException e) {
                    entity.collisionOn = true;
                    return;
                }

                gamePanel.debug.highlightX = leftVertexTileCoord;
                gamePanel.debug.highlightY = topVertexTileCoord;

                if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                    entity.collisionOn = true;
                }

                break;
            case Direction.DOWN:
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
