package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class GoddessMask extends BonusItem {
    public GoddessMask(Sprite sprite) {
        super(sprite);
        point = (int) 2e4;
    }

    public GoddessMask(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public boolean checkConditionToSpawn() {
        return isActivated && BombermanGame.bomber.isAloneInMap();
    }

    @Override
    public void update() {
    }
}
