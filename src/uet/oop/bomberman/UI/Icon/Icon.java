package uet.oop.bomberman.UI.Icon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.event.MouseEvent;

public abstract class Icon {
    protected int x;
    protected int y;
    protected double xMouse;
    protected double yMouse;
    protected int margin = 3;
    protected boolean isActive;

    protected Image img;

    protected GraphicsContext gc;

    public Icon(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean checkActive () {
        if(xMouse >= x && xMouse <= x + img.getWidth() - margin
        && yMouse >= y && yMouse <= y + img.getHeight()) {
            return true;
        }
        return false;
    }

    public void setMouseGrid(double x, double y) {
        this.xMouse = x;
        this.yMouse = y;
    }
    public abstract void update();
    public abstract void render(GraphicsContext gc);

}
