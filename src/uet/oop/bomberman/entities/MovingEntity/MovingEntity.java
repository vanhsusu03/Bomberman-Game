package uet.oop.bomberman.entities.MovingEntity;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.map;

public abstract class MovingEntity extends Entity {
    protected int centerX;
    protected int centerY;
    protected boolean isDead;
    protected boolean isCanPassFlames = false;
    protected boolean isCanPassBombs = false;
    protected boolean isCanPassBrick = false;

    public MovingEntity(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isCanPassFlames() {
        return isCanPassFlames;
    }

    protected abstract void move();

    protected boolean checkIntersectionWithOtherMovingEntity(int x1, int y1,
                                                             int x2, int y2) {
        return x < x2 && y < y2
                && (x + sprite.get_realWidth() > x1)
                && (y + sprite.get_realHeight() > y1);
    }

    private boolean isCellCanCome(int xUnit, int yUnit) {
        if (this instanceof Bomber) {
            for (Bomb bomb : Bomber.bombs) {
                if (bomb.getXUnit() == xUnit && bomb.getYUnit() == yUnit
                        && !bomb.isBomberCanPass()) {
                    return false;
                }
            }
        }

        return (!(map[yUnit][xUnit] instanceof Brick) || isCanPassBrick)
                && !(map[yUnit][xUnit] instanceof Wall);
    }

    protected boolean checkCanMove(int xUnit1, int yUnit1,
                                   int xUnit2, int yUnit2) {
        return isCellCanCome(xUnit1, yUnit1) && isCellCanCome(xUnit1, yUnit2)
                && isCellCanCome(xUnit2, yUnit1) && isCellCanCome(xUnit2, yUnit2);
    }
}