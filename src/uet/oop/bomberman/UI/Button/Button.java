package uet.oop.bomberman.UI.Button;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.event.MouseEvent;

public abstract class Button {
    protected int x;
    protected int y;

    protected int margin = 3;
    protected boolean isActive = false;

    protected Image img;

    protected GraphicsContext gc;

    public Button(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean checkActive (MouseEvent e) {
        if(e.getX() >= x && e.getX() <= x + img.getWidth() - margin
                && e.getY() >= y && e.getY() <= y + img.getHeight()) {
            return true;
        }
        return false;
    }

    public abstract void update();
    public abstract void render(GraphicsContext gc);

}
