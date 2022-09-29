package uet.oop.bomberman.entities.MovingEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MovingEntity extends Entity {
    public MovingEntity(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }
}
