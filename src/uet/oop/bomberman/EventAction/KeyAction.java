package uet.oop.bomberman.EventAction;

import java.awt.event.KeyEvent;

public class KeyAction {
    // Max(VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_SPACE, VK_D) = 68.
    public static boolean[] keys = new boolean[69];

    /**
     * Set keys when keeps.
     *
     * @param key     - type of key
     * @param isUsing - using or not
     */
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
            case "ESCAPE":
                keys[KeyEvent.VK_ESCAPE] = isUsing;
                break;
        }
    }

    /**
     * Set key's type.
     *
     * @param key     - type of key
     * @param isTyped - is typed or not
     */
    public static void setTypedKey(String key, boolean isTyped) {
        switch (key) {
            case " ":
                keys[KeyEvent.VK_SPACE] = isTyped;
                break;
            case "d":
                keys[KeyEvent.VK_D] = isTyped;
                break;
            case "ESCAPE":
                keys[KeyEvent.VK_ESCAPE] = isTyped;
                break;
        }
    }

}