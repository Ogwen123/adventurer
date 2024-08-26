package entity;
import handlers.KeyHandler;
import main.GamePanel;
import utils.Config;

import java.awt.*;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        initValues();
    }

    public void initValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;
    }

    public void update() {
        // update player position
        if (keyHandler.up) y -= speed;
        if (keyHandler.down) y += speed;
        if (keyHandler.left) x -= speed;
        if (keyHandler.right) x += speed;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, Config.tileSize, Config.tileSize); // draws a square :)
    }
}
