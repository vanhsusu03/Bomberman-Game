package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    private final static int MAX_FRAME_COUNT = (int) 1e9;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected int speed;
    protected int frameCount;
    protected Sprite sprite = null;
    protected Image img;

    public Entity() {
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, int speed, Sprite sprite) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.speed = speed;
        this.sprite = sprite;
        if (sprite != null) {
            img = sprite.getFxImage();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXUnit() {
        return x / Sprite.SCALED_SIZE;
    }

    public int getYUnit() {
        return y / Sprite.SCALED_SIZE;
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

    public abstract void update();
}