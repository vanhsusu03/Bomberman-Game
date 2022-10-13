package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Entity;
public abstract class Enemy extends MovingEntity {

    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

}
