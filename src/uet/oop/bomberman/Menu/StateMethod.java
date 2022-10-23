package uet.oop.bomberman.Menu;

import javafx.scene.canvas.GraphicsContext;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface StateMethod {
    public void update();
    public void draw(GraphicsContext gc);
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
}
