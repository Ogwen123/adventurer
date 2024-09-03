package main;

import entity.Entity;
import utils.Config;


public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int leftPlayerCoord = entity.x + entity.collisionArea.x + (gamePanel.tileManager.centreTileX * Config.tileSize);
        int rightPlayerCoord = entity.x + entity.collisionArea.x + entity.collisionArea.width + (gamePanel.tileManager.centreTileX * Config.tileSize);
        int topPlayerCoord = entity.y + entity.collisionArea.y + (gamePanel.tileManager.centreTileY * Config.tileSize);
        int bottomPlayerCoord = entity.y + entity.collisionArea.y + entity.collisionArea.height + (gamePanel.tileManager.centreTileY * Config.tileSize);

        // convert player coords to tile coord

        int leftTileCoord = leftPlayerCoord / Config.tileSize;
        int rightTileCoord = rightPlayerCoord / Config.tileSize;
        int topTileCoord = topPlayerCoord / Config.tileSize;
        int bottomTileCoord = bottomPlayerCoord / Config.tileSize;

        int tile1, tile2;

        // not in a switch statement to allow for diagonal movement
        if (gamePanel.keyHandler.up) {
            int temp = (topPlayerCoord - entity.speed) / Config.tileSize;

            tile1 = gamePanel.tileManager.map.get(temp).get(leftTileCoord);
            tile2 = gamePanel.tileManager.map.get(temp).get(rightTileCoord);

            if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                entity.collisionOn[0] = true;
            }
        }

        if (gamePanel.keyHandler.down) {
            int temp = (bottomPlayerCoord + entity.speed) / Config.tileSize;

            tile1 = gamePanel.tileManager.map.get(temp).get(leftTileCoord);
            tile2 = gamePanel.tileManager.map.get(temp).get(rightTileCoord);

            if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                entity.collisionOn[1] = true;
            }
        }

        if (gamePanel.keyHandler.left) {
            int temp = (leftPlayerCoord - entity.speed) / Config.tileSize;

            tile1 = gamePanel.tileManager.map.get(bottomTileCoord).get(temp);
            tile2 = gamePanel.tileManager.map.get(topTileCoord).get(temp);

            if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                entity.collisionOn[2] = true;
            }
        }

        if (gamePanel.keyHandler.right) {
            int temp = (rightPlayerCoord + entity.speed) / Config.tileSize;

            gamePanel.debug.collision1 = temp;
            gamePanel.debug.collision2_1 = topTileCoord;
            gamePanel.debug.collision2_2 = bottomTileCoord;

            tile1 = gamePanel.tileManager.map.get(bottomTileCoord).get(temp);
            tile2 = gamePanel.tileManager.map.get(topTileCoord).get(temp);

            if (gamePanel.tileManager.tiles[tile1].collision || gamePanel.tileManager.tiles[tile2].collision) {
                entity.collisionOn[3] = true;
            }
            System.out.println(entity.collisionOn[0] + " " + entity.collisionOn[1] + " " + entity.collisionOn[2] + " " + entity.collisionOn[3]);
        }

    }
}
