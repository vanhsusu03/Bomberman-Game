package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

//import sun.jvm.hotspot.runtime.ppc64.PPC64CurrentFrameGuess;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.StillEntity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomMove {

    protected int x, y;
    protected int speed;
    protected boolean wallPass;
    protected boolean brickPass;
    protected boolean bombPass;
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

    public int getGridX() {
        return this.x / Sprite.SCALED_SIZE;
    }

    public int getGridY() {
        return this.y / Sprite.SCALED_SIZE;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getDirection() {
        return this.direction;
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    private int randomMoveChanges(int x, int y, int z) {
        int changes = randomInt(0, 3);
        if (changes == 0) {
            return x;
        } else if (changes == 1) {
            return y;
        } else {
            return z;
        }
    }

    int direction = 0;

    public boolean canMoveleft() {
        Entity tmpStill = BombermanGame.map[y / Sprite.SCALED_SIZE][(x - speed) / Sprite.SCALED_SIZE];
        if(!bombPass) {
            for(int i=0;i<bombList.size();i++) {
                if(bombList.get(i).getGridX() == (x - speed) / Sprite.SCALED_SIZE
                        && bombList.get(i).getGridY() == y / Sprite.SCALED_SIZE ) {
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

    public boolean canMoveright() {
        Entity tmpStill = BombermanGame.map[y / Sprite.SCALED_SIZE][(x / Sprite.SCALED_SIZE) +1 ];
        if(!bombPass) {
            for(int i=0;i<bombList.size();i++) {
                if(bombList.get(i).getGridX() == (x / Sprite.SCALED_SIZE) + 1
                        && bombList.get(i).getGridY() == y / Sprite.SCALED_SIZE ) {
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

    public boolean canMoveup() {
        Entity tmpStill = BombermanGame.map[(y - speed) / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE];
        if(!bombPass) {
            for(int i=0;i<bombList.size();i++) {
                if(bombList.get(i).getGridX() == x / Sprite.SCALED_SIZE
                        && bombList.get(i).getGridY() == (y-speed) / Sprite.SCALED_SIZE ) {
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

    public boolean canMovedown() {
        Entity tmpStill = BombermanGame.map[(y / Sprite.SCALED_SIZE) + 1][x / Sprite.SCALED_SIZE];
        if(!bombPass) {
            for(int i=0;i<bombList.size();i++) {
                if(bombList.get(i).getGridX() == x/ Sprite.SCALED_SIZE
                        && bombList.get(i).getGridY() == (y / Sprite.SCALED_SIZE) + 1 ) {
                    System.out.println(bombList.get(i).getGridX() + " " + bombList.get(i).getGridY());
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
                canMove = canMoveleft();
                if (canMove) {
                    x -= speed;
                }
                break;
            case 1:
                canMove = canMoveright();
                if (canMove) {
                    x += speed;
                }
                break;
            case 2:
                canMove = canMoveup();
                if (canMove) {
                    y -= speed;
                }
                break;
            case 3:
                canMove = canMovedown();
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
