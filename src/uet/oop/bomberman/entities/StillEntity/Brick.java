package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StillEntity {
    private static final int TIME_MOVING_DESTROYED_SPRITE = 40;
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
                    Sprite.brick_exploded2, frameCount, TIME_MOVING_DESTROYED_SPRITE);
            img = sprite.getFxImage();
            updateFrameCount();
        }

        gc.drawImage(img, x, y);

        if (frameCount % TIME_MOVING_DESTROYED_SPRITE == TIME_MOVING_DESTROYED_SPRITE - 1) {
            int xUnit = x / Sprite.SCALED_SIZE;
            int yUnit = y / Sprite.SCALED_SIZE;
            BombermanGame.map[yUnit][xUnit] = new Grass(xUnit, yUnit, Sprite.grass);
        }
    }
}