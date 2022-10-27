package uet.oop.bomberman.UI.Icon;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class OnSound extends Icon {
    public OnSound(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (checkActive()) {
            img = Sprite.ic_onsound_last;
        } else {
            img = Sprite.ic_onsound_first;
        }
    }
}
