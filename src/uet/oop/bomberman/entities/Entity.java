package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    //Tọa độ theo tọa độ lưới - MAP
    protected int gridX;
    protected int gridY;
    private final static int MAX_FRAME_COUNT = (int) 1e9;
    protected int speed;
    protected int frameCount;
    protected Sprite sprite;
    protected Image img;

    public Entity() {

    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, int speed, Sprite sprite) {
        this.gridX = xUnit;
        this.gridY = yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.speed = speed;
        this.sprite = sprite;
        if (sprite != null) {
            img = sprite.getFxImage();
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getGridX() {
        return this.gridX;
    }

    public int getGridY() {
        return this.gridY;
    }
    public int getXUnit() {
        return x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return y / Sprite.SCALED_SIZE;
    }


    public Image getImage() {
        return this.img;
    }

    public Sprite getSprite() {
        return sprite;
    }
    protected boolean checkBothInACell(int xUnit, int yUnit) {
        return xUnit == getXUnit() && yUnit == getYUnit();
    }

    protected boolean checkBothInACell(int xUnit1, int yUnit1,
                                       int xUnit2, int yUnit2) {
        return xUnit1 == xUnit2 && yUnit1 == yUnit2;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public void updateFrameCount() {
        if (frameCount >= MAX_FRAME_COUNT) {
            frameCount = 0;
        } else {
            frameCount++;
        }
    }

//    public boolean checkCollisionwithMap(int x, int y) {
//        if ( x <= 0) {
//            x = 1;
//            return false;
//        }
//        if ( x >= BombermanGame.WIDTH ) {
//            x = BombermanGame.WIDTH - 1;
//            return false;
//        }
//        if ( y <= 0 ) {
//            y = 1;
//            return false;
//        }
//        if(y >= BombermanGame.HEIGHT) {
//            y = BombermanGame.HEIGHT -1;
//            return false;
//        }
//        return true;
//    }

    public abstract void update();
}