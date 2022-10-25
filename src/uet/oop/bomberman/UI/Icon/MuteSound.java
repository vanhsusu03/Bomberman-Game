package uet.oop.bomberman.UI.Icon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class MuteSound extends Icon{
    public MuteSound(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        setActive(checkActive());
        if(isActive) {
            img = Sprite.ic_mutesound_last;
        } else {
            img = Sprite.ic_mutesound_first;
        }
    }
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }

}
