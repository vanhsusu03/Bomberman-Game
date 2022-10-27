package uet.oop.bomberman.entities.StillEntity.Item;

import uet.oop.bomberman.entities.StillEntity.StillEntity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends StillEntity {
    public Item() {}
    public Item(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }
}