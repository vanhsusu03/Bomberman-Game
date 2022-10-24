package uet.oop.bomberman.map;

import javafx.scene.canvas.GraphicsContext;

public class Camera {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int velX;
    private int velY;

    //  Tọa độ tối đa của end.x và end.y
    private int maxX;
    private int maxY;
    private int width;
    private int height;

    //  CONSTRUCTORS
    /**
     * Các tọa độ là tọa độ trong Map, không phải tọa độ Window.
     */
    public Camera(int startX, int startY, int width, int height, int maxX, int maxY) {
        this.startX = startX;
        this.startY = startY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.width = width;
        this.height = height;
        this.endX = startX + width;
        this.endY = startY + height;
        this.velX = 0;
        this.velY = 0;
    }

    /**
     * Copy constructor.
     */
    public Camera(Camera source) {
        startX = source.startX;
        startY = source.startY;
        endX = source.endX;
        endY = source.endY;
        velX = source.velX;
        velY = source.velY;
        maxX = source.maxX;
        maxY = source.maxY;
        width = source.width;
        height = source.height;
    }

    //  SETTERS
    public void setCenter(int x, int y) {
        startX = x - width / 2;
        startY = y - height / 2;
        endX = startX + width;
        endY = height;
    }

    public void setPosition(int startX, int startY) {
        this.startX = startX;
        this.startX = startY;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        update();
    }

    //  GETTERS

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
//  FUNCTIONS
    /**
     * Cập nhập velocity (độ thay đổi của x và y)
     */
    public void move(int velX, int velY) {
        //this.velocity = velocity;
        this.velX = velX;
        this.velY = velY;
    }

    public void update() {
        //start.x += velocity.x;
        //start.y += velocity.y;
        //  X

        if (startX < 0) {
            startX = 0;
        }
        else if (startX + width > maxX) {
            //  Trường hợp chiều rộng bản đồ nhỏ hơn chiều rộng camera.
            if (maxX < width) {
                startX = 0;
            } else {
                startX = maxX - width;
            }
        }
        //  Y
        if (startY < 0) {
            startY = 0;
        }
        else if (startY + height > maxY) {
            //  Trường hợp chiều cao bản đồ nhỏ hơn chiều cao camera.
            if (maxY < height) {
                startY = 0;
            } else {
                startY = maxY - height;
            }
        }

        endX = startX + width;
        endY = startY + height;
    }

    @Override
    public Camera clone() {
        return new Camera(this);
    }

    public void render(int x, int y, GraphicsContext graphicsContext) {
        graphicsContext.fillRect(x, y, width, height);
    }
}
