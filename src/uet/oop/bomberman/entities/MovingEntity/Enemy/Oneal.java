package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    @Override
    public void move() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1,
                    Sprite.mob_dead2, Sprite.mob_dead3,
                    frameCount, TIME_MOVING_DEAD_SPRITE);
            img = sprite.getFxImage();
            updateFrameCount();
        } else {
        }

        gc.drawImage(img, x, y);

        removeEnemyIfDeathAnimationEnds();
    }
}