package uet.oop.bomberman.entities.StillEntity.Item.BonusItem;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class BonusItem extends Item {
    protected int point;
    protected boolean isActivated = true;

//    public BonusItem(Sprite sprite) {
//        this.sprite = sprite;
//        img = sprite.getFxImage();
//    }

    public BonusItem(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }

    public int getPoint() {
        return point;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    private int countGrassCells() {
        int count = 0;
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                if (BombermanGame.map[i][j] instanceof Grass) {
                    count++;
                }
            }
        }
        return count;
    }

    public void createRandomOnGrass() {
        Random rand = new Random();
        int posRand = rand.nextInt(countGrassCells());
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                if (BombermanGame.map[i][j] instanceof Grass) {
                    if (posRand <= 0) {
                        x = j * Sprite.SCALED_SIZE;
                        y = i * Sprite.SCALED_SIZE;
                        BombermanGame.map[i][j] = this;
                        return;
                    }
                    posRand--;
                }
            }
        }
    }

    public void spawn() {
        createRandomOnGrass();
        setActivated(false);
    }

    public abstract boolean checkConditionToSpawn();
}