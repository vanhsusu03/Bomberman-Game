package uet.oop.bomberman.UI.Icon;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventAction.MouseAction;

public abstract class Icon {
    protected int x;
    protected int y;
    protected int margin = 3;
    protected Image img;

    public Icon(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Check if mouse is in the range of this icon.
     *
     * @return true if it's in
     */
    public boolean checkActive() {
        return MouseAction.x >= x && MouseAction.x <= x + img.getWidth() - margin
                && MouseAction.y >= y && MouseAction.y <= y + img.getHeight();
    }

    /**
     * Update animations of Icons.
     */
    public abstract void update();

    public void render() {
        BombermanGame.gc.drawImage(img, x, y);
    }

}
