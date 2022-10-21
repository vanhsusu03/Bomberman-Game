package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
/**
 * Chasing bomberman lv1 - Smart: Normal - a bit of High.
 */
public class ChasingBomberlv2 extends RandomMove {
    public ChasingBomberlv2(int x, int y, int speed) {
        super(x, y, speed);
    }

    private int XrangeofChasing = 5;
    private int YrangeofChasing = 5;

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
        int Xdistance = x/Sprite.SCALED_SIZE - Bomber.getxBomber()/Sprite.SCALED_SIZE;
        int Ydistance = y/Sprite.SCALED_SIZE - Bomber.getyBomber()/Sprite.SCALED_SIZE;
        if ( Xdistance == 0) {
            if ( Ydistance >= 0 && Ydistance <= YrangeofChasing) {
                return 5;
            } else if (Ydistance <= 0 && -Ydistance <= YrangeofChasing) {
                return 6;
            }
        }
        if (Ydistance == 0 ) {
            if (Xdistance >= 0 && Xdistance <= XrangeofChasing) {
                return 7;
            } else if (Xdistance <= 0 && -Xdistance <= XrangeofChasing) {
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

    public void moveChasingChangeslv2() {
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

    public void updateChasingmoveLv2() {
        moveChasingChangeslv2();
    }
}
