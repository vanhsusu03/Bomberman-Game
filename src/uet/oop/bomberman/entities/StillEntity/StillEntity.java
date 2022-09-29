package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class StillEntity extends Entity {
    public StillEntity(int x, int y, Image img) {
        super(x, y, 0, img);
    }
}
