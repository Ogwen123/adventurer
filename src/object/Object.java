package object;

import entity.Entity;
import tile.TileManager;
import utils.Config;

import java.awt.*;

public class Object {
    public int x, y; // using tile coords
    public ObjectMetadata objectData;
    public String currentImage;

    public Object(int x, int y, ObjectMetadata objectData) {
        this.x = x;
        this.y = y;
        this.objectData = objectData;
        currentImage = objectData.imageNames[0];
    }

    public void draw(Graphics2D g2d, int cameraX, int cameraY) {
        g2d.drawImage(objectData.objectImages.get(currentImage), TileManager.tileCoordToScreenLoc(x, Entity.Plane.X, cameraX), TileManager.tileCoordToScreenLoc(y, Entity.Plane.Y, cameraY), Config.tileSize, Config.tileSize, null);
    }
}

