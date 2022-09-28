package uet.oop.bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAction implements KeyListener {
    // Max(VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_SPACE) = 40.
    private static boolean[] keys = new boolean[41];

    public static void setKey(String key, boolean isUsing) {
        switch (key) {
            case "UP":
                keys[KeyEvent.VK_UP] = isUsing;
            case "DOWN":
                keys[KeyEvent.VK_DOWN] = isUsing;
            case "LEFT":
                keys[KeyEvent.VK_LEFT] = isUsing;
            case "RIGHT":
                keys[KeyEvent.VK_RIGHT] = isUsing;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
