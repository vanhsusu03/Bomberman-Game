package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Bomb extends StillEntity {
    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,
                frameCount, 40).getFxImage();

        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}
