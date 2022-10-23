package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Famicom extends BonusItem {
    private int numberRemainBombsToGetItem = 50;

    public Famicom(Sprite sprite) {
        super(sprite);
        point = (int) 5e5;
        isActivated = true;
    }

    public Famicom(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public void decreaseOneNumberRemainBombs() {
        numberRemainBombsToGetItem--;
    }

    public int getNumberRemainBombsToGetItem() {
        return numberRemainBombsToGetItem;
    }

    @Override
    public boolean checkConditionToSpawn() {
        return isActivated && BombermanGame.bomber.isAloneInMap();
    }

    @Override
    public void update() {
    }
}
