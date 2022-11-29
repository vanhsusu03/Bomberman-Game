package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.entities.StillEntity.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMove {

    protected int x, y;
    protected int speed;
    protected boolean wallPass;
    protected boolean brickPass;
    protected boolean bombPass;
    protected int direction = 0;
    boolean canMove = false;
    private List<Bomb> bombList = new ArrayList<>();

    public RandomMove(int x, int y, int speed, boolean wallPass, boolean brickPass, boolean bombPass) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.wallPass = wallPass;
        this.brickPass = brickPass;
        this.bombPass = bombPass;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getXUnit() {
        return this.x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return this.y / Sprite.SCALED_SIZE;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return this.direction;
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }


    public boolean canMoveLeft() {
        Entity tmpStill = BombermanGame.map[y / Sprite.SCALED_SIZE][(x - speed) / Sprite.SCALED_SIZE];
        if (!bombPass) {
            for (int i = 0; i < bombList.size(); i++) {
                if (bombList.get(i).getXUnit() == (x - speed) / Sprite.SCALED_SIZE
                        && bombList.get(i).getYUnit() == y / Sprite.SCALED_SIZE) {
                    return false;
                }
            }
        }
        if (!(tmpStill instanceof Grass)) {
            if ((!wallPass && !brickPass)
                    || (wallPass && !(tmpStill instanceof Wall))
                    || (brickPass && !(tmpStill instanceof Brick))) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveRight() {
        Entity tmpStill = BombermanGame.map[y / Sprite.SCALED_SIZE][(x / Sprite.SCALED_SIZE) + 1];
        if (!bombPass) {
            for (int i = 0; i < bombList.size(); i++) {
                if (bombList.get(i).getXUnit() == (x / Sprite.SCALED_SIZE) + 1
                        && bombList.get(i).getYUnit() == y / Sprite.SCALED_SIZE) {
                    return false;
                }
            }
        }
        if (!(tmpStill instanceof Grass)) {
            if ((!wallPass && !brickPass)
                    || (wallPass && !(tmpStill instanceof Wall))
                    || (brickPass && !(tmpStill instanceof Brick))) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveUp() {
        Entity tmpStill = BombermanGame.map[(y - speed) / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE];
        if (!bombPass) {
            for (int i = 0; i < bombList.size(); i++) {
                if (bombList.get(i).getXUnit() == x / Sprite.SCALED_SIZE
                        && bombList.get(i).getYUnit() == (y - speed) / Sprite.SCALED_SIZE) {
                    return false;
                }
            }
        }
        if (!(tmpStill instanceof Grass)) {
            if ((!wallPass && !brickPass)
                    || (wallPass && !(tmpStill instanceof Wall))
                    || (brickPass && !(tmpStill instanceof Brick))) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveDown() {
        Entity tmpStill = BombermanGame.map[(y / Sprite.SCALED_SIZE) + 1][x / Sprite.SCALED_SIZE];
        if (!bombPass) {
            for (int i = 0; i < bombList.size(); i++) {
                if (bombList.get(i).getXUnit() == x / Sprite.SCALED_SIZE
                        && bombList.get(i).getYUnit() == (y / Sprite.SCALED_SIZE) + 1) {
                    System.out.println(bombList.get(i).getXUnit() + " " + bombList.get(i).getYUnit());
                    return false;
                }
            }
        }
        if (!(tmpStill instanceof Grass)) {
            if ((!wallPass && !brickPass)
                    || (wallPass && !(tmpStill instanceof Wall))
                    || (brickPass && !(tmpStill instanceof Brick))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 0 - canMoveLeft
     * 1 - canMoveRight
     * 2 - canMoveUp
     * 3 - canMoveDown
     */
    private void moveDirectionChanges() {
        bombList = BombermanGame.bomber.getBombs();
        if (!canMove) {
            direction = randomInt(0, 4);
        }
        switch (direction) {
            case 0:
                canMove = canMoveLeft();
                if (canMove) {
                    x -= speed;
                }
                break;
            case 1:
                canMove = canMoveRight();
                if (canMove) {
                    x += speed;
                }
                break;
            case 2:
                canMove = canMoveUp();
                if (canMove) {
                    y -= speed;
                }
                break;
            case 3:
                canMove = canMoveDown();
                if (canMove) {
                    y += speed;
                }
                break;
        }
    }

    public void updateRandomMove() {
        moveDirectionChanges();
    }
}
