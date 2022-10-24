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
    public ChasingBomberlv2(int x, int y, int speed, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(x, y, speed, wallPass, brickPass, bombPass);
    }

    private int xRangeOfChasing = 120;
    private int yRangeOfChasing = 120;
    private int chaseDirection = 0;

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
        int xDistance = x - Bomber.getxGridBomber() * Sprite.SCALED_SIZE;
        int yDistance = y - Bomber.getyGridBomber() * Sprite.SCALED_SIZE;
        if (xDistance == 0) {
            if (yDistance > 0 && yDistance <= yRangeOfChasing) {
                return 5;
            } else if (yDistance < 0 && -yDistance <= yRangeOfChasing) {
                return 6;
            }
        }
        if (yDistance == 0) {
            if (xDistance > 0 && xDistance <= xRangeOfChasing) {
                return 7;
            } else if (xDistance < 0 && -xDistance <= xRangeOfChasing) {
                return 8;
            }
        }
        if (xDistance > 0 && xDistance < xRangeOfChasing && yDistance > 0 && yDistance < yRangeOfChasing) {
            return 1;
        }
        if (xDistance > 0 && xDistance < xRangeOfChasing && -yDistance > 0 && -yDistance < yRangeOfChasing) {
            return 3;
        }
        if (-xDistance > 0 && -xDistance < xRangeOfChasing && yDistance > 0 && yDistance < yRangeOfChasing) {
            return 2;
        }
        if (-xDistance > 0 && -xDistance < xRangeOfChasing && -yDistance > 0 && -yDistance < yRangeOfChasing) {
            return 4;
        }
        return 0;
    }


    public void moveChasingChangesLv2() {
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            chaseDirection = directFollowChasing();
        }
        switch (chaseDirection) {
            case 1: // Top-Left
                // Left
                if (canMoveleft()) {
                    x -= speed;
                } else if (canMoveup()) {
                    y -= speed;
                } else {
                    chaseDirection = 0;
                }
            case 2: // Top - Right
                //Up
                if (canMoveright()) {
                    x += speed;
                } else if (canMoveup()) {
                    y -= speed;
                } else {
                    chaseDirection = 0;
                }
            case 3: // Left - Down
                //DOWN
                if (canMoveleft()) {
                    x -= speed;
                } else if (canMovedown()) {
                    y += speed;
                } else {
                    chaseDirection = 0;
                }
            case 4: // Right - Down
                if (canMoveright()) {
                    x += speed;
                } else if (canMovedown()) {
                    y += speed;
                } else {
                    chaseDirection = 0;
                }
            case 5: // same x, y up
                if (canMoveup()) {
                    y -= speed;
                } else {
                    chaseDirection = 0;
                }
            case 6: // same x, y down
                if (canMovedown()) {
                    y += speed;
                } else {
                    chaseDirection = 0;
                }
            case 7: // same y, x left
                if (canMoveleft()) {
                    x -= speed;
                } else {
                    chaseDirection = 0;
                }
            case 8: //same y, x right
                if (canMoveright()) {
                    x += speed;
                } else {
                    chaseDirection = 0;
                }
            default:
                updateRandomMove();
        }
    }

    public void updateChasingMoveLv2() {
        moveChasingChangesLv2();
    }
}
