package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Chasing bomberman lv1 - Smart: Normal - a bit of High.
 */
public class ChasingBomberlv2 extends RandomMove {
    public ChasingBomberlv2(int x, int y, int speed) {
        super(x, y, speed);
    }

    private int XrangeofChasing = 7;
    private int YrangeofChasing = 7;
    private int chasedirection;

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
        int Xdistance = x / Sprite.SCALED_SIZE - Bomber.getxBomber() / Sprite.SCALED_SIZE;
        int Ydistance = y / Sprite.SCALED_SIZE - Bomber.getyBomber() / Sprite.SCALED_SIZE;
        if (Xdistance == 0) {
            if (Ydistance > 0 && Ydistance <= YrangeofChasing) {
                return 5;
            } else if (Ydistance < 0 && -Ydistance <= YrangeofChasing) {
                return 6;
            }
        }
        if (Ydistance == 0) {
            if (Xdistance > 0 && Xdistance <= XrangeofChasing) {
                return 7;
            } else if (Xdistance < 0 && -Xdistance <= XrangeofChasing) {
                return 8;
            }
        }
        if (Xdistance > 0 && Xdistance < XrangeofChasing && Ydistance > 0 && Ydistance < YrangeofChasing) {
            return 1;
        }
        if (Xdistance > 0 && Xdistance < XrangeofChasing && -Ydistance > 0 && -Ydistance < YrangeofChasing) {
            return 3;
        }
        if (-Xdistance > 0 && -Xdistance < XrangeofChasing && Ydistance > 0 && Ydistance < YrangeofChasing) {
            return 2;
        }
        if (-Xdistance > 0 && -Xdistance < XrangeofChasing && -Ydistance > 0 && -Ydistance < YrangeofChasing) {
            return 4;
        }
        return 0;
    }

//    public void confirmDirections(boolean) {
//
//    }

    public void moveChasingChangeslv2() {
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            chasedirection = directFollowChasing();
        }
        switch (chasedirection) {
            case 1: // Top-Left
                // Left
                if (canMoveleft()) {
                    x -= speed;
                } else if (canMoveup()) {
                    y -= speed;
                } else {
                    chasedirection = 0;
                }
            case 2: // Top - Right
                //Up
                if (canMoveright()) {
                    x += speed;
                } else if (canMoveup()) {
                    y -= speed;
                } else {
                    chasedirection = 0;
                }
            case 3: // Left - Down
                //DOWN
                if (canMoveleft()) {
                    x -= speed;
                } else if (canMovedown()) {
                    y += speed;
                } else {
                    chasedirection = 0;
                }
            case 4: // Right - Down
                if (canMoveright()) {
                    x += speed;
                } else if (canMovedown()) {
                    y += speed;
                } else {
                    chasedirection = 0;
                }
            case 5: // same x, y up
                if (canMoveup()) {
                    y -= speed;
                } else {
                    updateRandomMove();
                }
            case 6: // same x, y down
                if (canMovedown()) {
                    y += speed;
                } else {
                    updateRandomMove();
                }
            case 7: // same y, x left
                if (canMoveleft()) {
                    x -= speed;
                } else {
                    updateRandomMove();
                }
            case 8: //same y, x right
                if (canMoveright()) {
                    x += speed;
                } else {
                    updateRandomMove();
                }
            default:
                updateRandomMove();
        }
    }

    public void updateChasingmoveLv2() {
        moveChasingChangeslv2();
    }
}
