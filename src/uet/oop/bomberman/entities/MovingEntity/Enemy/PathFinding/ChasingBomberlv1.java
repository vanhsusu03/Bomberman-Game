package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import javax.sql.XADataSource;

/**
 * Chasing bomberman lv1 - Smart: Low- a bit of Normal.
 */
public class ChasingBomberlv1 extends RandomMove {

    public ChasingBomberlv1(int x, int y, int speed) {
        super(x, y, speed);
    }

    private final int rangetoChase = 5;

    /**
     * RETURN 0: Can't navigate - player is out of range.
     * RETURN 1: Player left.
     * RETURN 2: PLayer right.
     * RETURN 3: Player up.
     * RETURN 4: Player down.
     */
    private int directFollowChasing() {
        int Xdistance = x/Sprite.SCALED_SIZE - Bomber.getxBomber()/Sprite.SCALED_SIZE;
        int Ydistance = y/Sprite.SCALED_SIZE - Bomber.getyBomber()/Sprite.SCALED_SIZE;
        //System.out.println("XDIS= " + Xdistance + " YDIS= " + Ydistance);
        //Player on the left
        if( Xdistance > 0 && Xdistance < rangetoChase && Ydistance == 0) {
            return 1;
        }
        //PLayer on the right
        if ( -Xdistance > 0 && -Xdistance < rangetoChase && Ydistance == 0 ) {
            return 2;
        }
        //Player is up
        if ( Xdistance == 0 && Ydistance > 0 && Ydistance < rangetoChase) {
            return 3;
        }
        //Player is down
        if (Xdistance == 0  && -Ydistance > 0 && -Ydistance < rangetoChase) {
            return 4;
        }
        return 0;
    }

    int chasedirection = 0;

    public void moveChasingChangeslv1() {
        chasedirection = directFollowChasing();
        //ystem.out.println(chasedirection);
            switch (chasedirection) {
                case 1: //Left
                    if(canMoveleft()) {
                        x -= speed;
                        break;
                    }
                case 2: //Right
                    if(canMoveright()) {
                         x+=speed;
                         break;
                    }
                case 3: //Up
                    if (canMoveup()) {
                        y-=speed;
                        break;
                    }
                case 4: //Down
                    if (canMovedown()) {
                        y += speed;
                        break;
                    }
                default:
                    updateRandomMove();
            }
        }

    public void updateChasingmoveLv1() {
        moveChasingChangeslv1();
    }
}
