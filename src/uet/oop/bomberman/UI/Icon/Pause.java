package uet.oop.bomberman.UI.Icon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Pause extends Icon{
    public Pause(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        setActive(checkActive());
        if(isActive) {
            img = Sprite.ic_pause_last;
        } else {
            img = Sprite.ic_pause_first;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }


}