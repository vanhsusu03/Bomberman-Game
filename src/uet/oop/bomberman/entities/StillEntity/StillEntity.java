package uet.oop.bomberman.entities.StillEntity;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public abstract class StillEntity extends Entity {
//    public StillEntity() {
//    }

    public StillEntity(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, 0, sprite);
    }
}