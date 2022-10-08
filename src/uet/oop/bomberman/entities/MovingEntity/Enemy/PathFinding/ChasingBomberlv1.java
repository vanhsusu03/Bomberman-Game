package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;

import javax.sql.XADataSource;

/**
 * Chasing bomberman lv1 - Smart: Normal
 */
public class ChasingBomberlv1 extends RandomMove {

    public ChasingBomberlv1(int x, int y, int speed) {
        super(x, y, speed);
    }
    @Override
     public int getX() {
       return super.getX();
     }
     @Override
     public int getY() {
        return super.getY();
     }
    //Chasing player in the range of a square has length 50 (canvas ratio)
    private int XrangeofChasing = 70;
    private int YrangeofChasing = 70;

    /**
     * RETURN 0: Can't navigate - player is out of range
     * RETURN 1: Player up - left
     * RETURN 2: PLayer up - right
     * RETURN 3: Player down - left
     * RETURN 4: Player down - right
     * RETURN 5: Player in the same x - y up
     * RETURN 6: PLayer in the same x - y down
     * RETURN 7: Player in the same y - x left
     * RETURN 8: Player in the same y - x right
     */
    private int directFollowChasing() {
        int Xdistance = x - Bomber.getxBomber();
        int Ydistance = y - Bomber.getyBomber();
        if ( Xdistance == 0) {
            if ( Ydistance > 0 && Ydistance <= YrangeofChasing) {
                return 5;
            } else if (Ydistance < 0 && -Ydistance <= YrangeofChasing) {
                return 6;
            }
        }
        if (Ydistance == 0 ) {
            if (Xdistance > 0 && Xdistance <= XrangeofChasing) {
                return 7;
            } else if (Xdistance < 0 && -Xdistance <= XrangeofChasing) {
                return 8;
            }
        }
        if( Xdistance > 0 && Xdistance < XrangeofChasing && Ydistance > 0 && Ydistance < YrangeofChasing ) {
            return 1;
        }
        if ( Xdistance > 0 && Xdistance < XrangeofChasing && -Ydistance > 0 && -Ydistance < YrangeofChasing) {
            return 3;
        }
        if ( -Xdistance > 0 && -Xdistance < XrangeofChasing && Ydistance > 0 && Ydistance < YrangeofChasing) {
            return 2;
        }
        if (-Xdistance > 0 && -Xdistance < XrangeofChasing && -Ydistance > 0 && -Ydistance < YrangeofChasing) {
            return 4;
        }
        return 0;
    }

    int chasedirection = 0;

    public void moveChasingChanges() {
        chasedirection = directFollowChasing();
        if(chasedirection == 0) {
            updateRandomMove();
        } else {
            switch (chasedirection) {
                case 1: // Top-Left
                    // Left
                    if(canMoveleft()) {
                        x -= speed;
                        break;
                    }else {
                        break;
                    }
                case 2: // Top - Right
                    //Up
                    if(canMoveup()) {
                         y-=speed;
                         break;
                    } else {
                        break;
                    }
                case 3: // Left - Down
                    //DOWN
                    if (canMovedown()) {
                        y+=speed;
                        break;
                    } else {
                        break;
                    }
                case 4: // Right - Down
                    if (canMoveright()) {
                        x += speed;
                        break;
                    } else {
                        break;
                    }
                case 5: // same x, y up
                    if (canMoveup()) {
                        y-=speed;
                        break;
                    } else {
                        break;
                    }
                case 6: // same x, y down
                    if (canMovedown()) {
                        y+=speed;
                        break;
                    } else {
                        break;
                    }
                case 7: // same y, x left
                    if(canMoveleft()) {
                        x-=speed;
                        break;
                    } else {
                        break;
                    }
                case 8: //same y, x right
                    if(canMoveright()) {
                        x+=speed;
                        break;
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
    }

    public void updateChasingmoveLv1() {
        moveChasingChanges();
    }
}
