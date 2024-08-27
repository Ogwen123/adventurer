package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public double x, y;
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
}
