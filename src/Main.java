import javax.swing.JFrame;
import java.awt.*;

public class Main {
    static JFrame window;
    static Profiler profiler;

    public static void main(String[] args) {
        profiler = new Profiler();


        window = new JFrame();
        window.setTitle("Space Invaders");
        window.setResizable(false);
        //window.setSize(tileSize * 20, tileSize * 16); // 20 tiles by 16 tiles
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(profiler);

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // start the game loop
        gamePanel.startGameThread();
    }
}