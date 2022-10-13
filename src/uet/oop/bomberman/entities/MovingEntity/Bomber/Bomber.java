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
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends MovingEntity {

    public static int xBomber, yBomber;
    private boolean isDead;
    private int numberOfBombs;
    public static List<Boolean> passBomb = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();

    public Bomber(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        xBomber = xUnit * Sprite.SCALED_SIZE;
        yBomber = yUnit * Sprite.SCALED_SIZE;
        numberOfBombs = 1;
    }

    public static int getxBomber() {
        return xBomber;
    }
    public static int getyBomber() {
        return yBomber;
    }

    public static void setxBomber(int x) {
        xBomber = x;
    }
    public static void setyBomber(int y) {
        yBomber = y;
    }
    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

    private void putBomb() {
        if (bombs.size() < numberOfBombs) {
            passBomb.add(true);
            bombs.add(new Bomb((int) Math.round((double) x / Sprite.SCALED_SIZE),
                    (int) Math.round((double) y / Sprite.SCALED_SIZE), Sprite.bomb));
        }
    }

    private void eatItems(Item item) {
        if (item instanceof SpeedItem) {
            speed++;
            BombermanGame.stillEntities.remove(item);
        } else if (item instanceof FlameItem) {
            BombermanGame.stillEntities.remove(item);
        } else if (item instanceof BombItem) {
            numberOfBombs++;
            BombermanGame.stillEntities.remove(item);
        }
    }

    private void usePortal(Portal portal) {
        BombermanGame.stillEntities.remove(portal);
    }

    @Override
    public void update() {
//        if (isDead) {
//
//        }

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
            KeyAction.keys[KeyEvent.VK_SPACE] = false;
        }

        for(int i=0;i < BombermanGame.HEIGHT;i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                StillEntity stillEntity = BombermanGame.stillEntities.get(i).get(j);
                int xStillEntity = stillEntity.getX(), yStillEntity = stillEntity.getY();
                if (checkIntersection(xStillEntity, yStillEntity,
                        xStillEntity + stillEntity.getSprite().get_realWidth(),
                        yStillEntity + stillEntity.getSprite().get_realHeight())) {
                    if (stillEntity instanceof Item) {
                        eatItems((Item) stillEntity);
                        BombermanGame.stillEntities.get(i).set(j,new Grass(j,i,Sprite.grass));
                    } else if (stillEntity instanceof Portal) {
                        usePortal((Portal) stillEntity);
                        i--;
                    }
                }
            }
        }

        for (MovingEntity movingEntity : BombermanGame.movingEntities) {
            if (!(movingEntity instanceof Enemy)) continue;
            int xMovingEntity = movingEntity.getX(), yMovingEntity = movingEntity.getY();
            if (checkIntersection(xMovingEntity, yMovingEntity,
                    xMovingEntity + movingEntity.getSprite().get_realWidth(),
                    yMovingEntity + movingEntity.getSprite().get_realHeight())) {
                //isDead = true;
            }
        }

        if (!checkCanMove()) {
            x = _x;
            y = _y;
        }
        setxBomber(this.x);
        setyBomber(this.y);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, frameCount, 40).getFxImage();
        } else {
            if (KeyAction.keys[KeyEvent.VK_UP]) {
                img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
                img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
                img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, frameCount, 40).getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
                img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, frameCount, 40).getFxImage();
            }
//            else {
//                img = Sprite.movingSprite(Sprite.player_right,Sprite.player_right,
//                        Sprite.player_right,frameCount,40).getFxImage();
//            }
        }

        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}
