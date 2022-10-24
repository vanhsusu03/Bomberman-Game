package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import javax.sql.XADataSource;

/**
 * Chasing bomberman lv1 - Smart: Low- a bit of Normal.
 */
public class ChasingBomberlv1 extends RandomMove {

    public ChasingBomberlv1(int x, int y, int speed, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(x, y, speed, wallPass, brickPass, bombPass);
    }

    private final int rangetoChase = 85;
    private int chaseDirection = 0;

    /**
     * RETURN 0: Can't navigate - player is out of range.
     * RETURN 1: Player left.
     * RETURN 2: PLayer right.
     * RETURN 3: Player up.
     * RETURN 4: Player down.
     */
    private int directFollowChasing() {
        int Xdistance = x - Bomber.getxGridBomber() * Sprite.SCALED_SIZE;
        int Ydistance = y - Bomber.getyGridBomber() * Sprite.SCALED_SIZE;
        //Player on the left
        if (Xdistance > 0 && Xdistance < rangetoChase && Ydistance == 0) {
            return 1;
        }
        //PLayer on the right
        if (-Xdistance > 0 && -Xdistance < rangetoChase && Ydistance == 0) {
            return 2;
        }
        //Player is up
        if (Xdistance == 0 && Ydistance > 0 && Ydistance < rangetoChase) {
            return 3;
        }
        //Player is down
        if (Xdistance == 0 && -Ydistance > 0 && -Ydistance < rangetoChase) {
            return 4;
        }
        return 0;
    }

    public void moveChasingChangeslv1() {
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            chaseDirection = directFollowChasing();
        }
        switch (chaseDirection) {
            case 1: //Left
                if (canMoveleft()) {
                    x -= speed;
                    break;
                }
            case 2: //Right
                if (canMoveright()) {
                    x += speed;
                    break;
                }
            case 3: //Up
                if (canMoveup()) {
                    y -= speed;
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

    public void updateChasingMoveLv1() {
        moveChasingChangeslv1();
    }
}
