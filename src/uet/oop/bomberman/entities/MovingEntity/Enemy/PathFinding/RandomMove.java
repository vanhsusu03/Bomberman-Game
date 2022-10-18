package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

//import sun.jvm.hotspot.runtime.ppc64.PPC64CurrentFrameGuess;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.StillEntity.*;

import java.util.concurrent.ThreadLocalRandom;

public class RandomMove  {

    int x, y;
    int speed;
    boolean canMove = canMovedown();

    public RandomMove(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getGridX() {
        return this.x/Sprite.SCALED_SIZE;
    }

    public int getGridY() {
        return this.y/Sprite.SCALED_SIZE;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getDirection() {
        return this.direction;
    }

    private int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min,max);
    }

    private int randomMoveChanges(int x, int y, int z) {
        int changes = randomInt(0,3);
        if ( changes == 0) {
            return x;
        }
        else if (changes == 1) {
            return y;
        }
        else {
            return z;
        }
    }

    int direction = 0;
    public boolean canMoveleft()  {
        StillEntity tmpStill = BombermanGame.stillEntities.get(y/Sprite.SCALED_SIZE).get((x-speed)/Sprite.SCALED_SIZE);
        if (!(tmpStill instanceof Grass)) {
            return false;
        }
        return true;
    }

    public boolean canMoveright()  {
        StillEntity tmpStill = BombermanGame.stillEntities.get(y/Sprite.SCALED_SIZE).get((x)/Sprite.SCALED_SIZE + 1);
        //tmpStill.render(BombermanGame.gc);
        if(tmpStill instanceof Wall) {
            return false;
        }
        else if (!(tmpStill instanceof Grass)) {
            return false;
        }
        return true;
    }

    public boolean canMoveup()  {
//        checkCollisionwithMap();
//        if( y/Sprite.SCALED_SIZE-1 == 0) {
//            return false;
//        }
        StillEntity tmpStll = BombermanGame.stillEntities.get((y-speed)/Sprite.SCALED_SIZE ).get(x/Sprite.SCALED_SIZE);
        if (!(tmpStll instanceof Grass)) {
            return false;
        }
        return true;
    }

    public boolean canMovedown()  {
//        if( y/Sprite.SCALED_SIZE + 1 == BombermanGame.HEIGHT) {
//            return false;
//        }
        StillEntity tmpStll = BombermanGame.stillEntities.get(y/Sprite.SCALED_SIZE + 1).get(x/Sprite.SCALED_SIZE);
        if (!(tmpStll instanceof Grass)) {
            return false;
        }
        return true;
    }
    public boolean checkCollisionwithMap() {
        if (x/Sprite.SCALED_SIZE <= 1) {
            x = Sprite.SCALED_SIZE + 1;
            return false;
        }
        if (x/Sprite.SCALED_SIZE >= BombermanGame.WIDTH - 1) {
            x = (BombermanGame.WIDTH - 2) * Sprite.SCALED_SIZE  ;
            return false;
        }
        if (y/Sprite.SCALED_SIZE <= 1) {
            y = 1+ Sprite.SCALED_SIZE;
            return false;
        }
        if(y/Sprite.SCALED_SIZE >= BombermanGame.HEIGHT - 1) {
            y = (BombermanGame.HEIGHT - 1)*Sprite.SCALED_SIZE +1 ;
            return false;
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
        boolean canMoveleft = canMoveleft();
        boolean canMoveright = canMoveright();
        boolean canMoveup = canMoveup();
        boolean canMovedown = canMovedown();

        if(canMoveleft || canMoveright || canMoveup || canMovedown) {
            if (!canMove) {
                direction = randomInt(0,4);
                }
                switch (direction) {
                    case 0:
                        canMove = canMoveleft();
                         if (canMove) x -= speed;
                        break;
                    case 1:
                        canMove = canMoveright();
                        if (canMove) x += speed;
                        break;
                    case 2:
                        canMove = canMoveup();
                        if (canMove) y -= speed;
                        break;
                    case 3:
                        canMove = canMovedown();
                        if (canMove) y += speed;
                        break;
                }
            }
        }
    public void updateRandomMove() {
        moveDirectionChanges();
    }
}
