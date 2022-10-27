package uet.oop.bomberman.UI.Button;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventAction.MouseAction;

public abstract class Button {
    protected int x;
    protected int y;
    protected int margin = 3;

    protected Image img;

    public Button(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public void setImage (Image img) {
        this.img = img;
    }

    public boolean checkActive () {
        return (MouseAction.x >= x && MouseAction.x <= x + img.getWidth() - margin
                && MouseAction.y >= y && MouseAction.y <= y + img.getHeight());
    }
    public void render() {
        BombermanGame.gc.drawImage(img,x,y);
    }

}
