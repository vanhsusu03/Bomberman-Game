package uet.oop.bomberman.entities.MovingEntity;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {
    protected boolean isDead;

    public MovingEntity(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    protected boolean checkIntersection(int x1, int y1, int x2, int y2) {
        return (x + 5 < x2) && (y + 12 < y2)
                && (x + sprite.get_realWidth() > x1)
                && (y + sprite.get_realHeight() > y1);
    }

    protected boolean checkCanMove() {
        for (StillEntity stillEntity : BombermanGame.stillEntities) {
            if (stillEntity instanceof Grass) {
                continue;
            }
            int xStillEntity = stillEntity.getX(), yStillEntity = stillEntity.getY();
            if (checkIntersection(xStillEntity, yStillEntity,
                    xStillEntity + stillEntity.getSprite().get_realWidth(),
                    yStillEntity + stillEntity.getSprite().get_realHeight())) {
                return false;
            }
        }

        for (int i = 0; i < Bomber.bombs.size(); i++) {
            Bomb bomb = Bomber.bombs.get(i);
            int xBomb = bomb.getX(), yBomb = bomb.getY();
            if (checkIntersection(xBomb, yBomb,
                    xBomb + bomb.getSprite().get_realWidth(),
                    yBomb + bomb.getSprite().get_realHeight())) {
                if (!(this instanceof Bomber && Bomber.passBomb.get(i))) {
                    return false;
                }
            } else if (this instanceof Bomber) {
                Bomber.passBomb.set(i, false);
            }
        }
        return true;
    }
}