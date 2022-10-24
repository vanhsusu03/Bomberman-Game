package uet.oop.bomberman.UI.Panels;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.UI.Icon.Icon;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

import javafx.scene.image.Image;

public abstract class Panel{

    protected int x;
    protected int y;
    protected boolean isActive = false;
    protected Image img;
    protected GraphicsContext gc;
    public Panel(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    protected void setActive(boolean active) {
        this.isActive = active;
    }

    public abstract void setCoordinatesMouse( double x, double y);

    public abstract void update();
    public abstract void render(GraphicsContext gc);

}
