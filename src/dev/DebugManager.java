package dev;

import utils.Config;

import java.awt.*;

public class DebugManager {
    public void draw(Graphics2D g2d) {
        if (Config.Debug.showCameraBufferBox) g2d.drawRect(480 - Config.cameraBuffer, 384 - Config.cameraBuffer, Config.cameraBuffer * 2 , Config.cameraBuffer * 2);
    }
}
