package uet.oop.bomberman.UI.Icon;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Resume extends Icon{

    public Resume(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(checkActive()) {
            img = Sprite.ic_resume_last;
        } else {
            img = Sprite.ic_resume_first;
        }
    }
}
