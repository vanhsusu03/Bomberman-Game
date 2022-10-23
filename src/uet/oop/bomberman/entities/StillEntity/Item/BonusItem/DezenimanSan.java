package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class DezenimanSan extends BonusItem {
    public DezenimanSan(Sprite sprite) {
        super(sprite);
        point = (int) 1e6;
        isActivated = true;
    }

    public DezenimanSan(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public boolean checkConditionToSpawn() {
        return isActivated && !Enemy.isAnyoneKilled
                && !Brick.isHavingOnMap();
    }

    @Override
    public void update() {
    }
}
