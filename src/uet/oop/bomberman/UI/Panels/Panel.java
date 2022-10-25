package uet.oop.bomberman.UI.Panels;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.Icon.Icon;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

import javafx.scene.image.Image;

public abstract class Panel{

    protected int x;
    protected int y;
    protected boolean isRunning = false;
    protected Image img;
    protected GraphicsContext gc;
    protected Canvas canvas;
    public Panel(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public abstract void setCoordinatesMouse(double x, double y);

    public abstract void setMouseClicked(boolean isClicked);
    public abstract void update();
    public abstract void render(GraphicsContext gc);
    public abstract void runningPanel(GraphicsContext gc);
    //public abstract void runningPanel(Stage stage);

}
