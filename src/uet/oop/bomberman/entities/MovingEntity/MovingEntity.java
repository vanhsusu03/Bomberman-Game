package uet.oop.bomberman.entities.MovingEntity;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.map;

public abstract class MovingEntity extends Entity {
    protected boolean isDead;

    public MovingEntity(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    protected abstract void move();

    public boolean checkTightIntersection(int x1, int y1, int x2, int y2) {
        return x < x2 && y < y2
                && (x + sprite.get_realWidth() > x1)
                && (y + sprite.get_realHeight() > y1);
    }

    private boolean isCellCanCome(int xUnit, int yUnit) {
        return !(map[yUnit][xUnit] instanceof Brick)
                && !(map[yUnit][xUnit] instanceof Wall)
                && (!(map[yUnit][xUnit] instanceof Bomb)
                || (this instanceof Bomber
                && ((Bomb) map[yUnit][xUnit]).isBomberCanPass()));
    }

    protected boolean checkCanMove(int xUnit1, int yUnit1,
                                   int xUnit2, int yUnit2) {
        return isCellCanCome(xUnit1, yUnit1) && isCellCanCome(xUnit1, yUnit2)
                && isCellCanCome(xUnit2, yUnit1) && isCellCanCome(xUnit2, yUnit2);
    }
}