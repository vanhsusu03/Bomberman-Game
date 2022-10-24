package uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem;

import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public abstract class PowerUpItem extends Item {
    public static final int NUMBER_OF_ITEMS = 8;

    public PowerUpItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
}
