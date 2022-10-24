package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.Famicom;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends StillEntity {
    private static final int DURATION_BOMB_EXPLOSION = (int) 2e9;
    private long startTime;
    private boolean isBomberCanPass = true;
    private boolean isExplodedByChainReaction;
    private List<Flame> horizontalFlames = new ArrayList<>();
    private List<Flame> verticalFlames = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        startTime = System.nanoTime();
    }

    public boolean isBomberCanPass() {
        return isBomberCanPass;
    }

    public void setBomberCanPass(boolean bomberCanPass) {
        isBomberCanPass = bomberCanPass;
    }

    public void setExplosion() {
        startTime = System.nanoTime() - DURATION_BOMB_EXPLOSION + (int) 3e7;
    }

    public void setExplodedByChainReaction(boolean explodedByChainReaction) {
        isExplodedByChainReaction = explodedByChainReaction;
    }

    public void createFlame() {
        int flameLength = BombermanGame.bomber.getFlameLength();
        for (int i = -flameLength; i <= flameLength; i++) {
            Flame.FlameType horizontalFlame, verticalFlame;
            if (i == 0) {
                horizontalFlame = Flame.FlameType.CENTER;
                verticalFlame = null;
            } else if (i == -flameLength) {
                horizontalFlame = Flame.FlameType.HORIZONTAL_LEFT_LAST;
                verticalFlame = Flame.FlameType.VERTICAL_TOP_LAST;
            } else if (i == flameLength) {
                horizontalFlame = Flame.FlameType.HORIZONTAL_RIGHT_LAST;
                verticalFlame = Flame.FlameType.VERTICAL_DOWN_LAST;
            } else {
                horizontalFlame = Flame.FlameType.HORIZONTAL;
                verticalFlame = Flame.FlameType.VERTICAL;
            }

            int xFlame = getXUnit();
            int yFlame = getYUnit();

            horizontalFlames.add(new Flame(xFlame + i, yFlame, horizontalFlame));
            if (i != 0) {
                verticalFlames.add(new Flame(xFlame, yFlame + i, verticalFlame));
            }
        }
    }

    private void removeExcessFlame(List<Flame> flames) {
        int n = flames.size();
        for (int i = flames.size() - 1; i >= 0; i--) {
            Flame flame = flames.get(i);

            if (!flame.checkIntersectionWithBombs(this)) {
                if (flame.getYUnit() >= BombermanGame.map.length
                        || flame.getXUnit() >= BombermanGame.map[0].length
                        || BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass) {
                    continue;
                }
            }

            if (i >= n / 2) {
                for (int j = i + 1; j < flames.size(); j++) {
                    flames.remove(j--);
                }
            } else {
                for (int j = 0; j < i; ) {
                    flames.remove(j);
                    i--;
                }
                break;
            }
        }
    }

    private void removeCollidedFlame(List<Flame> flames) {
        for (int i = flames.size() - 1; i >= 0; i--) {
            Flame flame = flames.get(i);
            if (!(BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass)
                    || flame.checkIntersectionWithBombs(this)) {
                flames.remove(i);
            }
        }
    }

    private void handleIfFamicomIsActivated() {
        if (BombermanGame.bonusItem instanceof Famicom
                && BombermanGame.bonusItem.checkConditionToSpawn()
                && isExplodedByChainReaction) {
            Famicom famicomItem = (Famicom) BombermanGame.bonusItem;
            famicomItem.decreaseOneNumberRemainBombs();
            if (famicomItem.getNumberRemainBombsToGetItem() <= 0) {
                famicomItem.spawn();
            }
        }
    }

    @Override
    public void update() {
        if (horizontalFlames.isEmpty() && System.nanoTime() - startTime >= DURATION_BOMB_EXPLOSION) {
            createFlame();

            removeExcessFlame(horizontalFlames);
            removeExcessFlame(verticalFlames);

            horizontalFlames.forEach(Flame::destroyAndKill);
            verticalFlames.forEach(Flame::destroyAndKill);

            removeCollidedFlame(horizontalFlames);
            removeCollidedFlame(verticalFlames);
        }

        for (int i = 0; i < horizontalFlames.size(); i++) {
            if (horizontalFlames.get(i).checkFinishedFlame()) {
                handleIfFamicomIsActivated();
                BombermanGame.bomber.removeAElementInBombs(this);
                break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (horizontalFlames.isEmpty()) {
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,
                    frameCount, 40).getFxImage();
            updateFrameCount();
            gc.drawImage(img, x, y);
        }

        horizontalFlames.forEach(flame -> flame.render(gc));
        verticalFlames.forEach(flame -> flame.render(gc));
    }
}