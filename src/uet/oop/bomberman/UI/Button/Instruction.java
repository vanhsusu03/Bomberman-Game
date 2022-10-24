package uet.oop.bomberman.UI.Button;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Instruction extends Button{

    public Instruction(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(isActive) {
            img = Sprite.ins_last;
        } else {
            img = Sprite.ins_first;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img,x,y);
    }
}
