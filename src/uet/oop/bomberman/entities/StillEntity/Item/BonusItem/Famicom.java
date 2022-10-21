package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.graphics.Sprite;

public class Famicom extends BonusItem {
    public static int numberRemainBombsToGetItem = 50;

    public Famicom(Sprite sprite) {
        super(sprite);
        point = (int) 5e5;
        isActivated = true;
    }

    public Famicom(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    @Override
    public void update() {
    }
}
