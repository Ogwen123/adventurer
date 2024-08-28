package entity;
import utils.handlers.KeyHandler;
import main.GamePanel;
import utils.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        initValues();
        getPlayerImages();
    }

    public void initValues() {
        this.x = 0;
        this.y = 0;
        this.speed = 4;
        direction = Direction.NONE;
        animation_duration = 10;
    }

    public void getPlayerImages() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player_right_2.png")));
            stationary = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/player.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        playerMovement();

        // handle animating the motion of the sprite
        spriteTracker++;
        if (spriteTracker > animation_duration) {
            sprite = !sprite;
            spriteTracker = 0;
        }
    }

    public void playerMovement() {
        direction = Direction.NONE; // reset direction

        // update player position
        if (keyHandler.up && !keyHandler.down) {// the '&& !...' bit is to not animate when opposing keys are held
            direction = Direction.UP;
            y -= speed;
        }
        if (keyHandler.down && !keyHandler.up) {
            direction = Direction.DOWN;
            y += speed;
        }
        if (keyHandler.left && !keyHandler.right) {
            direction = Direction.LEFT;
            x -= speed;
        }
        if (keyHandler.right && !keyHandler.left) {
            direction = Direction.RIGHT;
            x += speed;
        }

        // TODO maybe: stop diagonal movement from increasing speed by doing some goofy pythag
    }

    public void draw(Graphics2D g2d) {
        //g2d.setColor(Color.white);
        //g2d.fillRect(x, y, Config.tileSize, Config.tileSize); // draws a square :)

        BufferedImage image = switch (direction) {
            case Direction.UP -> (sprite ? up1 : up2);
            case Direction.DOWN -> (sprite ? down1 : down2);
            case Direction.LEFT -> (sprite ? left1 : left2);
            case Direction.RIGHT -> (sprite ? right1 : right2);
            default -> stationary;
        };

        g2d.drawImage(image, coordsToScreenLoc(x, Plane.X), coordsToScreenLoc(y, Plane.Y), Config.tileSize, Config.tileSize, null);
    }
}
