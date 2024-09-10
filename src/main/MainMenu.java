package main;

import utils.Config;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        this.setPreferredSize(new Dimension(Config.tileSize * Config.WINDOW_TILE_WIDTH, Config.tileSize * Config.WINDOW_TILE_HEIGHT));
        this.setBackground(new Color(0));
        this.setFocusable(true);

        JLabel title = new JLabel("Adventurer");
        title.setForeground(new Color(255, 255, 255));
        this.add(title);
    }

}
