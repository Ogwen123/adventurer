package utils.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import utils.Config;

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == Config.Keys.key_up) up = true;
        if (code == Config.Keys.key_down) down = true;
        if (code == Config.Keys.key_left) left = true;
        if (code == Config.Keys.key_right) right = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == Config.Keys.key_up) up = false;
        if (code == Config.Keys.key_down) down = false;
        if (code == Config.Keys.key_left) left = false;
        if (code == Config.Keys.key_right) right = false;
    }
}
