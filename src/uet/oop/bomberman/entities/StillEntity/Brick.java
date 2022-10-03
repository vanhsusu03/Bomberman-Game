package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StillEntity {
    private boolean isDestroyed;

    public Brick(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDestroyed) {
            sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                    Sprite.brick_exploded2, frameCount, 40);
            img = sprite.getFxImage();
            updateFrameCount();
        }

        gc.drawImage(img, x, y);

        if (sprite.equals(Sprite.brick_exploded2)) {
            BombermanGame.stillEntities.remove(this);
        }
    }
}
