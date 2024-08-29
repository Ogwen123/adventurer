package main;

import entity.Entity;
import entity.Entity.Direction;
import tile.TileManager;
import utils.Config;

import java.util.ArrayList;

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

        int leftVertexTileCoord = (int) Math.floor(leftVertexPlayerCoord / Config.tileSize);
        int rightVertexTileCoord = (int) Math.floor(rightVertexPlayerCoord / Config.tileSize);
        int topVertexTileCoord = (int) Math.floor(topVertexPlayerCoord / Config.tileSize);
        int bottomVertexTileCoord = (int) Math.floor(bottomVertexPlayerCoord / Config.tileSize);

        System.out.println(topVertexPlayerCoord);
        System.out.println(topVertexPlayerCoord / Config.tileSize);
        System.out.println(Math.floor(topVertexPlayerCoord / Config.tileSize));
        System.out.println(topVertexTileCoord + gamePanel.tileManager.centreTileY);
        System.out.println(leftVertexTileCoord + gamePanel.tileManager.centreTileX);
        System.out.println("----------------------");

        int tile1, tile2;

        switch (entity.direction) {
            case Direction.UP:
                topVertexTileCoord = (topVertexPlayerCoord - entity.speed) / Config.tileSize;
                ArrayList<Integer> temp = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY);
                tile1 = temp.get(leftVertexTileCoord + gamePanel.tileManager.centreTileX);
                tile2 = gamePanel.tileManager.map.get(topVertexTileCoord + gamePanel.tileManager.centreTileY).get(rightVertexTileCoord + gamePanel.tileManager.centreTileX);
                //System.out.println(leftVertexTileCoord + gamePanel.tileManager.centreTileX);
                //System.out.println(topVertexTileCoord + gamePanel.tileManager.centreTileY);
                //System.out.println(tile1);
                //System.out.println(tile2);
                //System.out.println(gamePanel.tileManager.tiles[tile1].collision);
                //System.out.println(gamePanel.tileManager.tiles[tile2].collision);
                //System.out.println("----------------------");

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
