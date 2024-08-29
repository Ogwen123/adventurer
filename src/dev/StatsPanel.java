package dev;

import utils.Config;
import utils.Profiler;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel implements Runnable {

    Profiler profiler;
    Thread statsThread;

    public StatsPanel(Profiler profiler) {
        this.setPreferredSize(new Dimension(Config.STATS_PANEL_WIDTH, Config.STATS_PANEL_HEIGHT));
        this.setBackground(new Color(1, 1, 1, 0.25f));
        this.setDoubleBuffered(true);
        this.profiler = profiler;
        this.setFocusable(false);
    }

    public void startStatsThread() {
        statsThread = new Thread(this);
        statsThread.start();
    }

    @Override
    public void run() {
        if (profiler.seen) return;
        repaint();
        profiler.seen = true;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        double roundingFactor = Math.pow(10, Config.DISPLAY_DECIMALS);

        g2d.setFont(new Font("Arial", Font.BOLD, 15));

        double percentOfMax = (Math.round(((profiler.processingTime / Config.MAX_FRAME_DURATION_MILLIS) * 100) * roundingFactor) / roundingFactor);

        //  make string to show
        String processingTimeString = "Processing time taken (% of max): " + profiler.processingTime + "ms (" + percentOfMax + "%)";
        String fpsString = "FPS: " + Math.round(profiler.fps);
        String cameraPosition = "CamX: " + profiler.cameraX + ", CamY: " + profiler.cameraY;
        String playerPosition = "PlaX: " + profiler.x + ", PlaY: " + profiler.y;
        String finalString = processingTimeString + " | " + fpsString + " | " + cameraPosition + " | " + playerPosition;

        g2d.drawString(finalString, 0, 15);

        g2d.dispose();
    }
}
