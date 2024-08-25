import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    static final int originalTileSize = 16;
    static final int scale = 3;
    static final int tileSize = originalTileSize * scale;

    public GamePanel() {
        this.setPreferredSize(new Dimension(tileSize * 20, tileSize*16));

        
    }

}
