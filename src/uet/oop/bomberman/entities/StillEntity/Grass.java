package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends StillEntity {
    public static Image grassImg;

    public Grass(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {
    }
}