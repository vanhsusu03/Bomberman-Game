package uet.oop.bomberman.entities.MovingEntity;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class MovingEntity extends Entity {
    public MovingEntity(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    private boolean checkIntersection(int x1, int y1, int x2, int y2) {
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
                if (stillEntity instanceof Portal) {
                    System.out.println("Vao` portal cmnr!!!");
                    BombermanGame.stillEntities.remove(stillEntity);
                } else if (stillEntity instanceof SpeedItem) {
                    speed++;
                    BombermanGame.stillEntities.remove(stillEntity);
                } else if (stillEntity instanceof FlameItem) {
                    BombermanGame.stillEntities.remove(stillEntity);
                } else if (stillEntity instanceof BombItem) {
                    Bomber.maxNumberOfBombs++;
                    BombermanGame.stillEntities.remove(stillEntity);
                }
                return false;
            }
        }
        return true;
    }
}