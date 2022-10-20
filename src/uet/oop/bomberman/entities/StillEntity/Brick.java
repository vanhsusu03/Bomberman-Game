package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StillEntity {
    private static final int TIME_MOVING_DESTROYED_SPRITE = 40;
    public static boolean isAnythingDestroyed = false;
    private boolean isDestroyed;

    public Brick(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
        if (destroyed) {
            isAnythingDestroyed = true;
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        int xUnit = getXUnit();
        int yUnit = getYUnit();

        if (isDestroyed && BombermanGame.hiddenEntities[yUnit][xUnit] != null) {
            BombermanGame.map[getYUnit()][getXUnit()] = BombermanGame.hiddenEntities[yUnit][xUnit];
            BombermanGame.hiddenEntities[yUnit][xUnit] = null;
            return;
        }

        if (isDestroyed) {
            sprite = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1,
                    Sprite.brick_exploded2, frameCount, TIME_MOVING_DESTROYED_SPRITE);
            img = sprite.getFxImage();
            updateFrameCount();
        }

        gc.drawImage(img, x, y);

        if (frameCount % TIME_MOVING_DESTROYED_SPRITE == TIME_MOVING_DESTROYED_SPRITE - 1) {
            BombermanGame.map[yUnit][xUnit] = new Grass(xUnit,
                    yUnit, Sprite.grass);
        }
    }
}