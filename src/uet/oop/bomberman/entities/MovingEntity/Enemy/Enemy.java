package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;

public abstract class Enemy extends MovingEntity {
    public Enemy(int x, int y, int speed, Image img) {
        super(x, y, speed, img);
    }
}
