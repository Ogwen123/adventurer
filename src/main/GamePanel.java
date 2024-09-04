package main;

import dev.DebugManager;
import entity.Player;
import object.ObjectManager;
import tile.TileManager;
import utils.handlers.KeyHandler;
import utils.Profiler;
import utils.Config;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // game classes
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    ObjectManager objectManager = new ObjectManager(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    // game variables
    public int cameraX, cameraY;

    // debug classes
    Profiler profiler;
    DebugManager debug = new DebugManager(tileManager, this);

    public GamePanel(Profiler profiler) {
        this.setPreferredSize(new Dimension(Config.tileSize * Config.WINDOW_TILE_WIDTH, Config.tileSize * Config.WINDOW_TILE_HEIGHT));
        this.setBackground(new Color(0));
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
            profiler.updateProfilerStats(timeTaken, cameraX, cameraY, player.x, player.y);
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d);
        objectManager.draw(g2d);
        if (Config.Debug.debug) debug.draw(g2d);
        player.draw(g2d);

        g2d.dispose();
    }

    // getters
    public double getPlayerX() {
        return player.x;
    }

    public double getPlayerY() {
        return player.y;
    }
}
