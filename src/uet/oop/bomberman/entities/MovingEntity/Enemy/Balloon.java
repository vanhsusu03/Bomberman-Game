package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.balloon_dead, Sprite.mob_dead1,
                    Sprite.mob_dead2, Sprite.mob_dead3, frameCount, 100);
            img = sprite.getFxImage();
            updateFrameCount();
        }

        gc.drawImage(img, x, y);

        if (sprite.equals(Sprite.mob_dead3)) {
            BombermanGame.movingEntities.remove(this);
        }
    }
}
