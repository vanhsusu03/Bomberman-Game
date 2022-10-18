package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;

import java.util.ArrayList;
import java.util.List;

public class ChasingBomberlv2 extends RandomMove {
    public ChasingBomberlv2(int x, int y, int speed) {
        super(x, y, speed);
    }

    private int bomberOldX;
    private int bomberOldY;
    Astar_FindingPath astar_findingPath = new Astar_FindingPath(BombermanGame.HEIGHT, BombermanGame.WIDTH);
    List<Node> path = new ArrayList<>();
    public void generatePath() {
        Node startNode = new Node(getGridX(),getGridY());
        Node endNode = new Node (Bomber.getxGridBomber(),Bomber.getyGridBomber());
        path = astar_findingPath.AStar_PathFinding(startNode,endNode);
        bomberOldX = Bomber.getxGridBomber();
        bomberOldY = Bomber.getyGridBomber();
    }

    public void tracePath() {
        for(int i=0;i<path.size();i++) {
            if(getGridX() == path.get(i).getX() && getGridY() == path.get(i).getY()) {
                continue;
            } else {
                Node curNode = path.get(i);
                if(curNode.getX() == getGridX()) {
                    if(getGridY() > curNode.getY()) {
                        y -= speed;
                    } else if (getGridY() < curNode.getY()) {
                        y += speed;
                    }
                } else if (curNode.getY() == getGridY()) {
                    if(getGridX() > curNode.getX()) {
                        x-=speed;
                    } else if (getGridX() < curNode.getX()) {
                        x+=speed;
                    }
                }
                break;
            }
        }
    }

    public void moveChasingChanges() {
        generatePath();
        if(path == null || path.size() == 0) {
            updateRandomMove();
        } else {
            tracePath();
        }
    }

    public void updateChasingmoveLv2() {
        moveChasingChanges();
    }
}
