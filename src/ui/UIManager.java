package ui;

import utils.handlers.KeyHandler;

public class UIManager {
    public boolean escapeMenu = false;
    public boolean inventory = false;

    public KeyHandler keyHandler;

    public UIManager(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
