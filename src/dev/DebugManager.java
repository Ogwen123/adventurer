package dev;

import entity.Entity;
import main.GamePanel;
import tile.TileManager;
import utils.Config;

import java.awt.*;

public class DebugManager {
    TileManager tileManager;
    GamePanel gamePanel;
    public int highlightX, highlightY = 0;

    public DebugManager(TileManager tileManager, GamePanel gamePanel) {
        this.tileManager = tileManager;
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2d) {
        if (Config.Debug.showCameraBufferBox) g2d.drawRect(480 - Config.cameraBuffer, 384 - Config.cameraBuffer, Config.cameraBuffer * 2 , Config.cameraBuffer * 2);
        if (Config.Debug.highlightOriginTile) g2d.drawRect(tileManager.tileCoordToScreenLoc(0, Entity.Plane.X) + gamePanel.cameraX, tileManager.tileCoordToScreenLoc(0, Entity.Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize);
        if (Config.Debug.highlightSpecifiedTile) g2d.drawRect(tileManager.tileCoordToScreenLoc(highlightX, Entity.Plane.X) + gamePanel.cameraX, tileManager.tileCoordToScreenLoc(highlightY, Entity.Plane.Y) + gamePanel.cameraY, Config.tileSize, Config.tileSize);
    }
}
