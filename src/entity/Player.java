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

    boolean[] COLLISION_TRACKER_RESET = {false, false, false, false};

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
        gamePanel.cameraX = 0;
        gamePanel.cameraY = 0;
        this.direction = Direction.NONE;
        this.animation_duration = 10;
        this.collisionArea = new Rectangle(8, 16, Config.tileSize * 2 / 3, Config.tileSize * 2 / 3);
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
        playerDirection();

        collisionOn = COLLISION_TRACKER_RESET.clone(); // {up, down, left, right}
        gamePanel.collisionChecker.checkTile(this);

        updateCamera();
        playerMovement();

        // handle animating the motion of the sprite
        spriteTracker++;
        if (spriteTracker > animation_duration) {
            sprite = !sprite;
            spriteTracker = 0;
        }
    }

    public void updateCamera() {
        // check if the camera position needs to be updated
        // move it by the players speed in the necessary direction

        int screenX = coordsToScreenLoc(x, Plane.X, gamePanel.cameraX);
        int screenY = coordsToScreenLoc(y, Plane.Y, gamePanel.cameraY);

        int screenCentreX = (Config.WINDOW_TILE_WIDTH * Config.tileSize) / 2;
        int screenCentreY = (Config.WINDOW_TILE_HEIGHT * Config.tileSize) / 2;

        if (((screenY + Config.tileSize / 2) < screenCentreY - Config.cameraBuffer) && keyHandler.up && !collisionOn[0]) {
            gamePanel.cameraY += speed;
        }
        if (((screenY + Config.tileSize / 2) > screenCentreY + Config.cameraBuffer) && keyHandler.down && !collisionOn[1]) {
            gamePanel.cameraY -= speed;
        }
        if (((screenX + Config.tileSize / 2) < screenCentreX - Config.cameraBuffer) && keyHandler.left && !collisionOn[2]) {
            gamePanel.cameraX += speed;
        }
        if (((screenX + Config.tileSize / 2) > screenCentreX + Config.cameraBuffer) && keyHandler.right && !collisionOn[3]) {
            gamePanel.cameraX -= speed;
        }

    }

    public void playerDirection() {
        // direction should only be used for the sprite, not for movement or camera position

        direction = Direction.NONE; // reset direction

        // update player direction
        if ((keyHandler.up && !keyHandler.down)) {// the first '&& !...' bit is to not animate when opposing keys are held
            direction = Direction.UP;
        }
        if ((keyHandler.down && !keyHandler.up)) {
            direction = Direction.DOWN;
        }
        if ((keyHandler.left && !keyHandler.right)) {
            direction = Direction.LEFT;
        }
        if ((keyHandler.right && !keyHandler.left)) {
            direction = Direction.RIGHT;
        }
    }

    public void playerMovement() {

        if (keyHandler.up && !collisionOn[0]) {
            y -= speed;
        }
        if (keyHandler.down && !collisionOn[1]) {
            y += speed;
        }
        if (keyHandler.left && !collisionOn[2]) {
            x -= speed;
        }
        if (keyHandler.right && !collisionOn[3]) {
            x += speed;
        }
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

        g2d.drawImage(image, coordsToScreenLoc(x, Plane.X, gamePanel.cameraX), coordsToScreenLoc(y, Plane.Y, gamePanel.cameraY), Config.tileSize, Config.tileSize, null);

    }
}
