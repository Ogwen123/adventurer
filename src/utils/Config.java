package utils;

import java.awt.event.KeyEvent;

public class Config {

    public static final int originalTileSize = 16;
    static public final int scale = 3;
    static public final int tileSize = originalTileSize * scale;

    static public final int WINDOW_TILE_HEIGHT = 16;
    static public final int WINDOW_TILE_WIDTH = 20;

    static public int FPS = 60;
    static public double frameDuration = 1_000_000_000.0 / FPS;
    static public double MAX_FRAME_DURATION_MILLIS = 1000.0 / FPS;

    static public final boolean SHOW_STATS_PANEL = true;
    static public final int STATS_PANEL_HEIGHT = 25;
    static public final int STATS_PANEL_WIDTH = 600;
    static public final int DISPLAY_DECIMALS = 2;

    public static class Keys {
        static public int up = KeyEvent.VK_W;
        static public int down = KeyEvent.VK_S;
        static public int left = KeyEvent.VK_A;
        static public int right = KeyEvent.VK_D;

    }

}
