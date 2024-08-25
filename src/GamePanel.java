import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GamePanel extends JPanel implements Runnable {

    static final int originalTileSize = 16;
    static final int scale = 3;
    static final int tileSize = originalTileSize * scale;

    static final int windowTileHeight = 16;
    static final int windowTileWidth = 20;

    Thread gameThread;
    Profiler profiler;

    public GamePanel(Profiler profiler) {
        this.setPreferredSize(new Dimension(tileSize * windowTileWidth, tileSize * windowTileHeight));
        this.setBackground(Color.decode("#4A6741"));
        this.setDoubleBuffered(true);
        this.profiler = profiler;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (gameThread != null) {
            update(); // update game state

            repaint(); // repaint the screen

            profiler.updateProfilerStats();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.white);
        g2d.fillRect(100, 100, tileSize, tileSize);

        g2d.dispose();
    }


}
