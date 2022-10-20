package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity {
    protected static final int TIME_MOVING_DEAD_SPRITE = 100;
    public static boolean isAnyoneKilled = false;

    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    protected void removeEnemyIfDeathAnimationEnds() {
        if (frameCount % TIME_MOVING_DEAD_SPRITE == TIME_MOVING_DEAD_SPRITE - 1) {
            BombermanGame.movingEntities.remove(this);
            isAnyoneKilled = true;
        }
    }
}