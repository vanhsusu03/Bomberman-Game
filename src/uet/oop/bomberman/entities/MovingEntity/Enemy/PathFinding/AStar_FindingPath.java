package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar_FindingPath {

    private int heightRange;
    private int widthRange;

    private boolean wallPass;
    private boolean brickPass;
    private boolean bombPass;

    private List<Node> pathList = new ArrayList<>();

    public AStar_FindingPath(int heightRange, int widthRange, boolean wallPass, boolean brickPass, boolean bombPass) {
        this.heightRange = heightRange;
        this.widthRange = widthRange;
        this.wallPass = wallPass;
        this.brickPass = brickPass;
        this.bombPass = bombPass;
    }

    //Parsed map: 1 - CANT GO, 0 - CAN GO
    public int[][] mapParsed() {
        int[][] mapParsed = new int[BombermanGame.HEIGHT][BombermanGame.WIDTH];
        Entity[][] entity = BombermanGame.map;
        List<MovingEntity> moveList = BombermanGame.movingEntities;
        for (MovingEntity movingEntity : moveList) {
            mapParsed[movingEntity.getYUnit()][movingEntity.getXUnit()] = 1;
        }
        for (int i = 0; i < heightRange; i++) { //height
            for (int j = 0; j < widthRange; j++) { //width
                Entity tmpStill = entity[i][j];
                if (tmpStill instanceof Wall || tmpStill instanceof Brick) {
                    mapParsed[i][j] = 1;
                } else {
                    mapParsed[i][j] = 0;
                }
            }
        }
        if (wallPass) {
            for (int i = 0; i < heightRange; i++) {
                for (int j = 0; j < widthRange; j++) {
                    if (entity[i][j] instanceof Wall) {
                        mapParsed[i][j] = 0;
                    }
                }
            }
        }
        if (brickPass) {
            for (int i = 0; i < heightRange; i++) {
                for (int j = 0; j < widthRange; j++) {
                    if (entity[i][j] instanceof Brick) {
                        mapParsed[i][j] = 0;
                    }
                }
            }
        }
        if (!bombPass) {
            for (int i = 0; i < heightRange; i++) {
                for (int j = 0; j < widthRange; j++) {
                    for (int k = 0; k < BombermanGame.bomber.getBombs().size(); k++) {
                        if (BombermanGame.bomber.getBombs().get(k).getXUnit() == j
                                && BombermanGame.bomber.getBombs().get(k).getYUnit() == i) {
                            mapParsed[i][j] = 1;
                        }
                    }
                }
            }
        }
        mapParsed[Bomber.getYGridBomber()][Bomber.getXGridBomber()] = 0;
        return mapParsed;
    }

    //DONE parsed map
    //Next: come to A*

    public void pathBackTrace(Node endNode) {
        pathList.clear();
        while (endNode != null) {
            pathList.add(0, endNode);
            endNode = endNode.getParentNode();
        }
    }

    public List<Node> AStar_PathFinding(Node startNode, Node endNode) {
        int[][] mapParsed = mapParsed();
        PriorityQueue<Node> futureNode = new PriorityQueue<>();
        List<Node> passedNode = new ArrayList<>();
        futureNode.offer(startNode);

        while (!futureNode.isEmpty()) {
            //curNode: smallest node
            Node curNode = futureNode.peek();

            //Take neighbors node coordinates
            int leftX = curNode.getX() - 1;
            int rightX = curNode.getX() + 1;
            int upY = curNode.getY() - 1;
            int downY = curNode.getY() + 1;
            //Trace to all nearby node (neighbors node)
            for (int col = leftX; col <= rightX; col++) {
                if (col < 0 || col >= BombermanGame.WIDTH) {
                    continue;
                }
                for (int row = upY; row <= downY; row++) {
                    if (row < 0 || row >= BombermanGame.HEIGHT) {
                        continue;
                    }
                    if (col == curNode.getX() && row == curNode.getY()) {
                        continue;
                    }
                    if (col != curNode.getX() && row != curNode.getY()) {
                        continue;
                    }
                    if (mapParsed[row][col] == 1) {
                        continue;
                    }

                    //Have accepted node - still open
                    boolean notPassed = true;
                    for (Node node : passedNode) {
                        if (curNode.equals(node)) {
                            notPassed = false;
                            break;
                        }
                    }
                    if (notPassed) {
                        Node newNode = new Node(col, row);
                        newNode.setCost(curNode.getGcost() + newNode.getEuclidDistance(curNode),
                                newNode.getEuclidDistance(endNode));
                        newNode.setParentNode(curNode);

                        //if newNode is endNode
                        if (newNode.equals(endNode)) {
                            endNode = newNode;
                            pathBackTrace(endNode);
                            return pathList;
                        } else {
                            //Check if newNode has been passed
                            //If a node has been passed & in this A* its cost is smaller and then update this
                            boolean isExist = false;
                            List<Node> tmpFuture = new ArrayList<>(futureNode);
                            for (int i = 0; i < tmpFuture.size(); i++) {
                                if (newNode.equals(tmpFuture.get(i))) {
                                    isExist = true;
                                    if (newNode.compareTo(tmpFuture.get(i)) < 0) {
                                        tmpFuture.set(i, newNode);
                                    }
                                }
                            }
                            futureNode.clear();
                            futureNode = new PriorityQueue<>(tmpFuture);
                            if (!isExist) {
                                futureNode.offer(newNode);
                            }

                        }

                    }
                }
            }
            //curNode is passed - closed
            futureNode.poll();
            passedNode.add(curNode);
        }
        return null;
    }
}
