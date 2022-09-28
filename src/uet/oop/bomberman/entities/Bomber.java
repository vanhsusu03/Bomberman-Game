package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;

public class Bomber extends Entity {
    private boolean isDead;

    public Bomber(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }

    @Override
    public void update() {
        if (KeyAction.keys[KeyEvent.VK_UP]) {
            y -= speed;
        } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
            y += speed;
        } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
            x -= speed;
        } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
            x += speed;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, frameCount, 30).getFxImage();
        } else {
            if (KeyAction.keys[KeyEvent.VK_UP]) {
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2,
                        frameCount, 30).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2,
                        frameCount, 30).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2,
                        frameCount, 30).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2,
                        frameCount, 30).getFxImage();
            }
        }
        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}
