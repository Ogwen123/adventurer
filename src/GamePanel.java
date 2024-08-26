import handlers.KeyHandler;
import utils.Profiler;
import utils.Config;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    Thread gameThread;
    Profiler profiler;
    KeyHandler keyHandler = new KeyHandler();

    // player data
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(Profiler profiler) {
        this.setPreferredSize(new Dimension(Config.tileSize * Config.WINDOW_TILE_WIDTH, Config.tileSize * Config.WINDOW_TILE_HEIGHT));
        this.setBackground(Color.decode("#4A6741"));
        this.setDoubleBuffered(true);
        this.profiler = profiler;
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            long time = System.nanoTime();
            update(); // update game state
            repaint(); // repaint the screen
            long timeTaken = System.nanoTime() - time;
            double remaining = (Config.frameDuration - timeTaken) / 1_000_000;
            try {
                Thread.sleep((long) remaining);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            profiler.updateProfilerStats(timeTaken);
        }
    }

    public void update() {
        // update player position
        if (keyHandler.up) playerY -= playerSpeed;
        if (keyHandler.down) playerY += playerSpeed;
        if (keyHandler.left) playerX -= playerSpeed;
        if (keyHandler.right) playerX += playerSpeed;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(playerX, playerY, Config.tileSize, Config.tileSize); // draws a square :)

        g2d.dispose();
    }


}
