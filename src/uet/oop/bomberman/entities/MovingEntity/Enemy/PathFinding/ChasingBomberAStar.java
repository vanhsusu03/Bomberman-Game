package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class ChasingBomberAStar extends RandomMove {
    public ChasingBomberAStar(int x, int y, int speed) {
        super(x, y, speed);
    }

    Astar_FindingPath astar_findingPath = new Astar_FindingPath(BombermanGame.HEIGHT, BombermanGame.WIDTH);
    List<Node> path = new ArrayList<>();

    public void generatePath() {
        Node startNode = new Node(getGridX(), getGridY());
        Node endNode = new Node(Bomber.getxGridBomber(), Bomber.getyGridBomber());
        path = astar_findingPath.AStar_PathFinding(startNode, endNode);
    }

    public void tracePath() {
        for (int i = 0; i < path.size(); i++) {
            if (getX() != path.get(i).getX() * 32 || getY() != path.get(i).getY() * 32) {
                Node curNode = path.get(i);
                if (curNode.getX() == getGridX() && getX() % 32 == 0) {
                    if (getY() > curNode.getY() * 32) {
                        y -= speed;
                    } else if (getY() < curNode.getY() * 32) {
                        y += speed;
                    }
                    break;
                } else if (curNode.getY() == getGridY() && getY() % 32 == 0) {
                    if (getX() > curNode.getX() * 32) {
                        x -= speed;
                    } else if (getX() < curNode.getX() * 32) {
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

    public void moveChasingChangesAStar() {
        if (getX() % 32 == 0 && getY() % 32 == 0) {
            generatePath();
        }
        if (path == null || path.size() == 0) {
            updateRandomMove();
        } else {
            tracePath();
        }
    }

    public void updateChasingmoveAStar() {
        moveChasingChangesAStar();
    }
}
