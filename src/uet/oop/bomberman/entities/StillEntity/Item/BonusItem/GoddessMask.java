package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.graphics.Sprite;

public class GoddessMask extends BonusItem {
    public GoddessMask(Sprite sprite) {
        super(sprite);
        point = (int) 2e4;
        isActivated = true;
    }

    public GoddessMask(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {
    }
}
