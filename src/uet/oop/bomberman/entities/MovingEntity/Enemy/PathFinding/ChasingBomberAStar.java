package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class ChasingBomberAStar extends RandomMove {
    public ChasingBomberAStar(int x, int y, int speed, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(x, y, speed, wallPass, brickPass, bombPass);
    }

    AStar_FindingPath astar_findingPath = new AStar_FindingPath(BombermanGame.HEIGHT, BombermanGame.WIDTH, wallPass, brickPass, bombPass);
    List<Node> path = new ArrayList<>();

    /**
     * Call A*.
     */
    public void generatePath() {
        Node startNode = new Node(getXUnit(), getYUnit());
        Node endNode = new Node(Bomber.getXGridBomber(), Bomber.getYGridBomber());
        path = astar_findingPath.AStar_PathFinding(startNode, endNode);
    }

    /**
     * Trace path A*.
     */
    public void tracePath() {
        for (int i = 0; i < path.size(); i++) {
            if (getX() != path.get(i).getX() * Sprite.SCALED_SIZE
                    || getY() != path.get(i).getY() * Sprite.SCALED_SIZE) {
                Node curNode = path.get(i);
                if (curNode.getX() == getXUnit() && getX() % Sprite.SCALED_SIZE == 0) {
                    if (getY() > curNode.getY() * Sprite.SCALED_SIZE) {
                        direction = 2;
                        y -= speed;
                    } else if (getY() < curNode.getY() * Sprite.SCALED_SIZE) {
                        direction = 3;
                        y += speed;
                    }
                    break;
                } else if (curNode.getY() == getYUnit() && getY() % Sprite.SCALED_SIZE == 0) {
                    if (getX() > curNode.getX() * Sprite.SCALED_SIZE) {
                        direction = 0;
                        x -= speed;
                    } else if (getX() < curNode.getX() * Sprite.SCALED_SIZE) {
                        direction = 1;
                        x += speed;
                    }
                    break;
                }
            } else {
                path.remove(i);
                i--;
            }
        }
    }

    /**
     * Find path.
     */
    public void moveChasingChangesAStar() {
        if (getX() % Sprite.SCALED_SIZE == 0 && getY() % Sprite.SCALED_SIZE == 0) {
            generatePath();
        }
        if (path == null || path.size() == 0) {
            updateRandomMove();
        } else {
            tracePath();
        }
    }

    public void updateChasingMoveAStar() {
        moveChasingChangesAStar();
    }
}
