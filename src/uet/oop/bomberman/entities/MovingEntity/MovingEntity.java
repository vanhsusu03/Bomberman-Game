package uet.oop.bomberman.entities.MovingEntity;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.MapLoadFile;

import java.util.List;

public abstract class MovingEntity extends Entity {
    protected int centerX;
    protected int centerY;
    protected boolean isDead;
    protected boolean isCanPassFlames;
    protected boolean isCanPassBombs;
    protected boolean isCanPassBrick;
    public MovingEntity() {
        super();

    }
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

    protected void updateCenterPosition() {
        centerX = x + sprite.get_realWidth() / 2;
        centerY = y + sprite.get_realHeight() / 2;
    }

    protected boolean checkIntersectionWithOtherMovingEntity(int x1, int y1,
                                                             int x2, int y2) {
        return x < x2 && y < y2
                && (x + sprite.get_realWidth() > x1)
                && (y + sprite.get_realHeight() > y1);
    }

    protected boolean isCellCanCome(int xUnit, int yUnit) {
        List<Bomb> bombs = BombermanGame.bomber.getBombs();
        if (this instanceof Bomber) {
            for (Bomb bomb : bombs) {
                if (bomb.getXUnit() == xUnit && bomb.getYUnit() == yUnit
                        && !bomb.isBomberCanPass()) {
                    return false;
                }
            }
        }

        return (!(BombermanGame.map[yUnit][xUnit] instanceof Brick)
                || isCanPassBrick)
                && !(BombermanGame.map[yUnit][xUnit] instanceof Wall);
    }

    protected boolean checkCanMove(int x, int y) {
        int xUnit1 = x / Sprite.SCALED_SIZE;
        int yUnit1 = y / Sprite.SCALED_SIZE;
        int xUnit2 = (x + sprite.get_realWidth()) / Sprite.SCALED_SIZE;
        int yUnit2 = (y + sprite.get_realHeight()) / Sprite.SCALED_SIZE;

        return isCellCanCome(xUnit1, yUnit1) && isCellCanCome(xUnit1, yUnit2)
                && isCellCanCome(xUnit2, yUnit1) && isCellCanCome(xUnit2, yUnit2);
    }
}