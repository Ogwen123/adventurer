import utils.Config;
import utils.Profiler;
import javax.swing.*;

public class Main {
    static JFrame window;
    static Profiler profiler;



    public static void main(String[] args) {
        profiler = new Profiler();

        window = new JFrame();
        //window.setSize(Config.tileSize * Config.windowTileWidth, Config.tileSize * Config.windowTileHeight);
        window.setSize(Config.tileSize * Config.WINDOW_TILE_WIDTH, Config.tileSize * Config.WINDOW_TILE_HEIGHT);
        window.setTitle("Adventurer");
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(profiler);

        if (Config.SHOW_STATS_PANEL) {
            JLayeredPane container = new JLayeredPane();
            window.add(container);

            StatsPanel statsPanel = new StatsPanel(profiler);

            gamePanel.setBounds(0, 0, Config.tileSize * Config.WINDOW_TILE_WIDTH, Config.tileSize * Config.WINDOW_TILE_HEIGHT);
            statsPanel.setBounds(0, 0, Config.STATS_PANEL_WIDTH, Config.STATS_PANEL_HEIGHT);

            container.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
            container.add(statsPanel, JLayeredPane.PALETTE_LAYER);

            statsPanel.startStatsThread();
        } else {
            window.add(gamePanel);
        }

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // start the game loop
        gamePanel.startGameThread();
    }
}