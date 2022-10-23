package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class BonusTarget extends BonusItem {
    public BonusTarget(Sprite sprite) {
        super(sprite);
        point = (int) 1e4;
        isActivated = true;
    }

    public BonusTarget(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public boolean checkConditionToSpawn() {
        return isActivated && !Enemy.isAnyoneKilled;
    }

    @Override
    public void update() {
    }
}
