package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Famicom extends BonusItem {
    private static final int NUMBER_BOMBS_CHAIN_REACTION_NEED = 50;
    private int numberRemainBombsToGetItem;

//    public Famicom(Sprite sprite) {
//        super(sprite);
//        numberRemainBombsToGetItem = NUMBER_BOMBS_CHAIN_REACTION_NEED;
//        point = (int) 5e5;
//    }

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