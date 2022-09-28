package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Bomber extends Entity {
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMovingLeft;
    private boolean isMovingRight;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
}
