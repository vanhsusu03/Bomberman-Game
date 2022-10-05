package uet.oop.bomberman.entities.MovingEntity.Bomber;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {
    public static int flameLength = 1;
    public static List<Bomb> bombs = new ArrayList<>();
    private int maxNumberOfBombs;

    public Bomber(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        maxNumberOfBombs = 1;
    }

    private void putBomb() {
        if (bombs.size() < maxNumberOfBombs) {
            bombs.add(new Bomb((int) Math.round((double) x / Sprite.SCALED_SIZE),
                    (int) Math.round((double) y / Sprite.SCALED_SIZE), Sprite.bomb));
        }
    }

    private void eatItem(int i, int j) {
        if (BombermanGame.map[i][j] instanceof SpeedItem) {
            speed++;
        } else if (BombermanGame.map[i][j] instanceof FlameItem) {
            flameLength++;
        } else if (BombermanGame.map[i][j] instanceof BombItem) {
            maxNumberOfBombs++;
        }
        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
    }

    private void usePortal(int i, int j) {
        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
    }

    @Override
    public void move() {
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
            KeyAction.keys[KeyEvent.VK_SPACE] = false;
        }
    }

    private void handleCollisionWithItem(int xUnit1, int yUnit1,
                                         int xUnit2, int yUnit2) {
        if (BombermanGame.map[yUnit1][xUnit1] instanceof Item) {
            eatItem(yUnit1, xUnit1);
        }

        if (BombermanGame.map[yUnit1][xUnit2] instanceof Item) {
            eatItem(yUnit1, xUnit2);
        }

        if (BombermanGame.map[yUnit2][xUnit1] instanceof Item) {
            eatItem(yUnit2, xUnit1);
        }

        if (BombermanGame.map[yUnit2][xUnit2] instanceof Item) {
            eatItem(yUnit2, xUnit2);
        }
    }

    private void handleCollisionWithPortal(int xUnit1, int yUnit1,
                                           int xUnit2, int yUnit2) {
        if (BombermanGame.map[yUnit1][xUnit1] instanceof Portal) {
            usePortal(yUnit1, xUnit1);
        }

        if (BombermanGame.map[yUnit1][xUnit2] instanceof Portal) {
            usePortal(yUnit1, xUnit2);
        }

        if (BombermanGame.map[yUnit2][xUnit1] instanceof Portal) {
            usePortal(yUnit2, xUnit1);
        }

        if (BombermanGame.map[yUnit2][xUnit2] instanceof Portal) {
            usePortal(yUnit2, xUnit2);
        }
    }

    private void handleCollisionWithEnemy(int xUnit1, int yUnit1,
                                          int xUnit2, int yUnit2) {
        if (BombermanGame.map[yUnit1][xUnit1] instanceof Enemy
                || BombermanGame.map[yUnit1][xUnit2] instanceof Enemy
                || BombermanGame.map[yUnit2][xUnit1] instanceof Enemy
                || BombermanGame.map[yUnit2][xUnit2] instanceof Enemy) {
            isDead = true;
        }
    }

    private void handleBombPass(int xUnit1, int yUnit1,
                                int xUnit2, int yUnit2) {
        if (!bombs.isEmpty() && bombs.get(bombs.size() - 1).isBomberCanPass()) {
            if (!(BombermanGame.map[yUnit1][xUnit1] instanceof Bomb)
                    && !(BombermanGame.map[yUnit1][xUnit2] instanceof Bomb)
                    && !(BombermanGame.map[yUnit2][xUnit1] instanceof Bomb)
                    && !(BombermanGame.map[yUnit2][xUnit2] instanceof Bomb)) {
                bombs.get(bombs.size() - 1).setBomberCanPass(false);
            }
        }
    }

    @Override
    public void update() {
        if (isDead) {
            return;
        }

        int _x = x, _y = y;
        move();

        int xUnit1 = (x + 5) / Sprite.SCALED_SIZE;
        int yUnit1 = (y + 12) / Sprite.SCALED_SIZE;
        int xUnit2 = (x + sprite.get_realWidth()) / Sprite.SCALED_SIZE;
        int yUnit2 = (y + sprite.get_realHeight()) / Sprite.SCALED_SIZE;

        if (!checkCanMove(xUnit1, yUnit1, xUnit2, yUnit2)) {
            x = _x;
            y = _y;
        } else {
            handleCollisionWithItem(xUnit1, yUnit1, xUnit2, yUnit2);
            handleCollisionWithPortal(xUnit1, yUnit1, xUnit2, yUnit2);
            handleCollisionWithEnemy(xUnit1, yUnit1, xUnit2, yUnit2);
            handleBombPass(xUnit1, yUnit1, xUnit2, yUnit2);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, frameCount, 60);
            img = sprite.getFxImage();
        } else {
            if (KeyAction.keys[KeyEvent.VK_UP]) {
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, frameCount, 40);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, frameCount, 40);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, frameCount, 40);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, frameCount, 40);
                img = sprite.getFxImage();
            }
        }

        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}