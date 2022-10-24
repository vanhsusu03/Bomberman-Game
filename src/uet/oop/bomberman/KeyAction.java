package uet.oop.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAction implements KeyListener {
    // Max(VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_SPACE, VK_D) = 68.
    public static boolean[] keys = new boolean[69];

    public static void setKeptKey(String key, boolean isUsing) {
        switch (key) {
            case "UP":
                keys[KeyEvent.VK_UP] = isUsing;
                break;
            case "DOWN":
                keys[KeyEvent.VK_DOWN] = isUsing;
                break;
            case "LEFT":
                keys[KeyEvent.VK_LEFT] = isUsing;
                break;
            case "RIGHT":
                keys[KeyEvent.VK_RIGHT] = isUsing;
                break;
        }
    }

    public static void setTypedKey(String key, boolean isTyped) {
        switch (key) {
            case " ":
                keys[KeyEvent.VK_SPACE] = isTyped;
                break;
            case "d":
                keys[KeyEvent.VK_D] = isTyped;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}