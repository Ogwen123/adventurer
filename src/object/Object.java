package object;

import entity.Entity;
import tile.TileManager;
import utils.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {
    public BufferedImage[] object_images;
    public boolean collision = false;
    public int x, y; // using tile coords
    public String type;
    public int imageTracker = 0;

    public Object(String type, int x, int y, BufferedImage[] images) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.object_images = images;
    }

    public void draw(Graphics2D g2d, int cameraX, int cameraY) {
        g2d.drawImage(object_images[imageTracker], TileManager.tileCoordToScreenLoc(x, Entity.Plane.X, cameraX), TileManager.tileCoordToScreenLoc(y, Entity.Plane.Y, cameraY), Config.tileSize, Config.tileSize, null);
    }
}

