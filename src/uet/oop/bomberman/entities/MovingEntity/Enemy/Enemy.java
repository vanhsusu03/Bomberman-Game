package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.NakamotoSan;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends MovingEntity {
    protected static final int TIME_MOVING_DEAD_SPRITE = 100;
    public static boolean isAnyoneKilled = false;

    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
    }

    private void handleIfNakamotoSanIsActivated() {
        if (BombermanGame.bonusItem instanceof NakamotoSan
                && BombermanGame.bonusItem.checkConditionToSpawn()) {
            BombermanGame.bonusItem.spawn();
        }
    }

    protected void removeEnemyIfDeathAnimationEnds() {
        if (frameCount % TIME_MOVING_DEAD_SPRITE == TIME_MOVING_DEAD_SPRITE - 1) {
            BombermanGame.movingEntities.remove(this);
            isAnyoneKilled = true;

            handleIfNakamotoSanIsActivated();
        }
    }
}