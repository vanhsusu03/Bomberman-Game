package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.graphics.Sprite;

public class DezenimanSan extends BonusItem {
    public DezenimanSan(Sprite sprite) {
        super(sprite);
        point = (int) 8e5;
        isActivated = true;
    }

    public DezenimanSan(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {
    }
}
