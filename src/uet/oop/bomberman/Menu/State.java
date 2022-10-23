package uet.oop.bomberman.Menu;

import uet.oop.bomberman.BombermanGame;

import java.awt.event.MouseEvent;

public class State {

    public State() {
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
}
