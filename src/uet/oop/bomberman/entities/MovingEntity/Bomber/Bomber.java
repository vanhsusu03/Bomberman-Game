package uet.oop.bomberman.entities.MovingEntity.Bomber;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {
    private boolean isDead;
    public static int maxNumberOfBombs;
    public static List<Bomb> bombs = new ArrayList<>();

    public Bomber(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        maxNumberOfBombs = 1;
    }

    private void putBomb() {
        if (bombs.size() < maxNumberOfBombs) {
            bombs.add(new Bomb(x / Sprite.SCALED_SIZE,
                    y / Sprite.SCALED_SIZE, Sprite.bomb));
        }
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
        } else if (KeyAction.keys[KeyEvent.VK_SPACE]) {
            putBomb();
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
