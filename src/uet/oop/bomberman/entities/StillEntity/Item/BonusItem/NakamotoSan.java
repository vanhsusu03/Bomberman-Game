package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.graphics.Sprite;

public class NakamotoSan extends BonusItem {
    public NakamotoSan(Sprite sprite) {
        super(sprite);
        point = (int) 1e7;
        isActivated = true;
    }

    public NakamotoSan(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public boolean checkConditionToSpawn() {
        return isActivated && BombermanGame.bomber.isAloneInMap()
                && !Brick.isAnythingDestroyed;
    }

    @Override
    public void update() {
    }
}
