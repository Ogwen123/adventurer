package entity;

import utils.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public enum Direction {
        NONE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stationary;
    public Direction direction;
    public boolean sprite; // using boolean to make it easier to switch between the two sprite options
    public int spriteTracker;
    public int animation_duration; // number of frames before the sprite switches

    public Rectangle collisionArea;
    public boolean[] collisionOn = {false, false, false, false}; // {up, down, left, right}

    public enum Plane {
        X,
        Y
    }

    public int coordsToScreenLoc(double coord, Plane plane, int camera) {
        return (int) Math.round(coord + ((double) ((plane == Plane.X ? Config.WINDOW_TILE_WIDTH : Config.WINDOW_TILE_HEIGHT) * Config.tileSize) / 2) + camera);
    }
}
