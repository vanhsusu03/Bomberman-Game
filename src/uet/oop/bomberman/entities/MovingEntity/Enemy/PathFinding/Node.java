package uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding;

public class Node implements Comparable<Node> {

    //Node in grid coordinates Oxy
    private int x;
    private int y;
    //Cost to move from start node
    private int gCost = 0;
    //Minimum cost to move to end node
    private int hCost = 0;
    //Total minimum cost to move from start node to end node
    private int fCost = 0;

    //Parent node
    Node parentNode = null;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCost (int gCost, int hCost) {
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }
    //x & y is GRID coordinates
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGcost() {
        return gCost;
    }

    public int getHcost() {
        return hCost;
    }

    public int getFcost() {
        return fCost;
    }

    public Node getParentNode() {
        return parentNode;
    }
    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getEuclidDistance(Node otherNode) {
        int gridX = otherNode.getX() - this.x;
        int gridY = otherNode.getY() - this.y;
        return (int)(Math.sqrt(gridX*gridX + gridY*gridY) * 10);
    }

    //Compare to decide which road is the shortest
    @Override
    public int compareTo(Node o) {
        if(fCost > o.getFcost()) {
            return 1;
        } else if (fCost < o.getFcost()) {
            return -1;
        }

        if(gCost > o.getGcost()) {
            return 1;
        } else if (gCost < o.getGcost()) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            if(this.x == ((Node) obj).getX() && this.y == ((Node) obj).getY()) {
                return true;
            }
        }
        return false;
    }
}
