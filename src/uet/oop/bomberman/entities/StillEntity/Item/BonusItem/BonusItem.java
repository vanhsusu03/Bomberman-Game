package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public abstract class BonusItem extends Item {
    public BonusItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
}