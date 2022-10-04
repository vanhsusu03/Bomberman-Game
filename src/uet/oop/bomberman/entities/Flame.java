package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public enum FlameType {
        CENTER,
        HORIZONTAL,
        HORIZONTAL_LEFT_LAST,
        HORIZONTAL_RIGHT_LAST,
        VERTICAL,
        VERTICAL_DOWN_LAST,
        VERTICAL_TOP_LAST
    }

    private FlameType flameType;
    private boolean isCollided;

    public Flame(int xUnit, int yUnit, FlameType flameType) {
        super(xUnit, yUnit, 0, null);
        this.flameType = flameType;
    }

    public FlameType getFlameType() {
        return flameType;
    }

    public void setFlameType(FlameType flameType) {
        this.flameType = flameType;
    }

    public boolean isCollided() {
        return isCollided;
    }

    @Override
    public void update() {
        for (MovingEntity movingEntity : BombermanGame.movingEntities) {
//            if ((movingEntity instanceof Bomber || movingEntity instanceof Enemy)
//                    && movingEntity.getX() / Sprite.SCALED_SIZE == x / Sprite.SCALED_SIZE
//                    && movingEntity.getY() / Sprite.SCALED_SIZE == y / Sprite.SCALED_SIZE) {
            if ((movingEntity instanceof Bomber || movingEntity instanceof Enemy)
                    && movingEntity.checkTightIntersection(x, y, x + Sprite.DEFAULT_SIZE, y + Sprite.DEFAULT_SIZE)) {
                movingEntity.setDead(true);
                isCollided = true;
            }
        }

        for (int i = 0; i < BombermanGame.stillEntities.size(); i++) {
            StillEntity stillEntity = BombermanGame.stillEntities.get(i);
            if (stillEntity.getX() / Sprite.SCALED_SIZE == x / Sprite.SCALED_SIZE
                    && stillEntity.getY() / Sprite.SCALED_SIZE == y / Sprite.SCALED_SIZE) {
                if (stillEntity instanceof Brick) {
                    ((Brick) stillEntity).setDestroyed(true);
                }
                if (!(stillEntity instanceof Grass)) {
                    isCollided = true;
                }


//                if (flameType == HORIZONTAL) {
//
//                }





            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        switch (flameType) {
            case CENTER:
                sprite = Sprite.movingSprite(Sprite.bomb_exploded,
                        Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                        Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL_LEFT_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL_RIGHT_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                        Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL_DOWN_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL_TOP_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
        }
        updateFrameCount();
        gc.drawImage(img, x, y);
    }

    public boolean checkFinishedFlame() {
        return sprite.equals(Sprite.bomb_exploded2) || sprite.equals(Sprite.explosion_horizontal2)
                || sprite.equals(Sprite.explosion_horizontal_left_last2)
                || sprite.equals(Sprite.explosion_horizontal_right_last2)
                || sprite.equals(Sprite.explosion_vertical2)
                || sprite.equals(Sprite.explosion_vertical_down_last2)
                || sprite.equals(Sprite.explosion_vertical_top_last2);
    }
}
