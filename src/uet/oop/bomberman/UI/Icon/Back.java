package uet.oop.bomberman.UI.Icon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.MouseAction;
import uet.oop.bomberman.graphics.Sprite;

public class Back extends Icon{

    public Back(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(checkActive()) {
            img = Sprite.ic_back_last;
        } else {
            img = Sprite.ic_back_first;
        }
    }
}
