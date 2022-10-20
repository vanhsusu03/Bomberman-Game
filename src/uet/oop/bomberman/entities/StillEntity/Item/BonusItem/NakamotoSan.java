package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

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
    public void update() {
    }
}
