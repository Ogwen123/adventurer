import javax.swing.JFrame;
import java.awt.*;

public class Main {
    static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setTitle("Space Invaders");
        window.setResizable(false);
        //window.setSize(tileSize * 20, tileSize * 16); // 20 tiles by 16 tiles

        Color bg = Color.decode("#4a6741");
        window.setBackground(bg);

        window.setVisible(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
    }
}