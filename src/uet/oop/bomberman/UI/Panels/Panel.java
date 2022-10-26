package uet.oop.bomberman.UI.Panels;


import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.map.MapLoadFile;

public abstract class Panel{

    protected int x;
    protected int y;
    protected boolean isRunning = false;
    protected Image img;

    public Panel(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
    public boolean getRunning() {
        return this.isRunning;
    }
    public void render() {
        BombermanGame.gc.drawImage(img, x,y);
    }

}
