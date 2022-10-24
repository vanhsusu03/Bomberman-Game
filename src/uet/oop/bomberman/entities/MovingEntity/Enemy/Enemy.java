package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.NakamotoSan;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Entity;
public abstract class Enemy extends MovingEntity {

    protected boolean wallPass;
    protected boolean brickPass;
    protected boolean bombPass;
    protected static final int TIME_MOVING_DEAD_SPRITE = 100;
    public static boolean isAnyoneKilled;
    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite);
        this.wallPass = wallPass;
        this.brickPass = brickPass;
        this.bombPass = bombPass;
    }
    private void handleIfNakamotoSanIsActivated() {
        if (BombermanGame.bonusItem instanceof NakamotoSan
                && BombermanGame.bonusItem.checkConditionToSpawn()) {
            BombermanGame.bonusItem.spawn();
        }
    }

    protected void removeEnemyIfDeathAnimationEnds() {
        if (frameCount % TIME_MOVING_DEAD_SPRITE == TIME_MOVING_DEAD_SPRITE - 1 && isDead) {
            BombermanGame.movingEntities.remove(this);
            isAnyoneKilled = true;

            handleIfNakamotoSanIsActivated();
        }
    }

}
