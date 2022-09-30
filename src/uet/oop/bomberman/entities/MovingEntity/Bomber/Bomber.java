package uet.oop.bomberman.entities.MovingEntity.Bomber;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Bomber extends MovingEntity {
    private boolean isDead;

    public Bomber(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    @Override
    public void update() {
        int _x = x, _y = y;

        if (KeyAction.keys[KeyEvent.VK_UP]) {
            y -= speed;
        } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
            y += speed;
        } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
            x -= speed;
        } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
            x += speed;
        }

        if (!checkCanMove()) {
            x = _x;
            y = _y;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, frameCount, 40).getFxImage();
        } else {
            if (KeyAction.keys[KeyEvent.VK_UP]) {
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2,
                        frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2,
                        frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,
                        frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,
                        frameCount, 40).getFxImage();
            }
        }
        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}
