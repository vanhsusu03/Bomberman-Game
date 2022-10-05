package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity {
    protected static final int TIME_MOVING_DEAD_SPRITE = 100;

    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }
}