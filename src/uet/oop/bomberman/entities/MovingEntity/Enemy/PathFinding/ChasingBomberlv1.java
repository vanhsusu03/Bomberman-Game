package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;


public class ChasingBomberlv1 extends RandomMove {

    public ChasingBomberlv1(int x, int y, int speed, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(x, y, speed, wallPass, brickPass, bombPass);
    }

    private final int rangetoChase = 5;
    private int chaseDirection = 0;

    /**
     * RETURN 0: Can't navigate - player is out of range.
     * RETURN 1: Player left.
     * RETURN 2: PLayer right.
     * RETURN 3: Player up.
     * RETURN 4: Player down.
     */
    private int directFollowChasing() {
        int xDistance = x / 32 - BombermanGame.bomber.getX() / 32;
        int yDistance = y / 32 - BombermanGame.bomber.getY() / 32;
        //Player on the left
        if (xDistance > 0 && xDistance < rangetoChase && yDistance == 0) {
            return 1;
        }
        //PLayer on the right
        if (-xDistance > 0 && -xDistance < rangetoChase && yDistance == 0) {
            return 2;
        }
        //Player is up
        if (xDistance == 0 && yDistance > 0 && yDistance < rangetoChase) {
            return 3;
        }
        //Player is down
        if (xDistance == 0 && -yDistance > 0 && -yDistance < rangetoChase) {
            return 4;
        }
        return 0;
    }

    /**
     * Find path.
     */
    public void moveChasingChangeslv1() {
        if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
            chaseDirection = directFollowChasing();
        }
        switch (chaseDirection) {
            case 1: //Left
                if (canMoveLeft()) {
                    x -= speed;
                    break;
                }
            case 2: //Right
                if (canMoveRight()) {
                    x += speed;
                    break;
                }
            case 3: //Up
                if (canMoveUp()) {
                    y -= speed;
                    break;
                }
            case 4: //Down
                if (canMoveDown()) {
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
