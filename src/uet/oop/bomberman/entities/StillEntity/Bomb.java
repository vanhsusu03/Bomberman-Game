package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends StillEntity {
    private static final int DURATION_BOMB_EXPLOSION = (int) 2e9;
    private long startTime;
    private boolean isBomberCanPass;
    private List<Flame> horizontalFlames = new ArrayList<>();
    private List<Flame> verticalFlames = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        isBomberCanPass = true;
        startTime = System.nanoTime();
    }

    public boolean isBomberCanPass() {
        return isBomberCanPass;
    }

    public void setBomberCanPass(boolean bomberCanPass) {
        isBomberCanPass = bomberCanPass;
    }

    public void createFlame() {
        for (int i = -Bomber.flameLength; i <= Bomber.flameLength; i++) {
            Flame.FlameType horizontalFlame, verticalFlame;
            if (i == 0) {
                horizontalFlame = Flame.FlameType.CENTER;
                verticalFlame = null;
            } else if (i == -Bomber.flameLength) {
                horizontalFlame = Flame.FlameType.HORIZONTAL_LEFT_LAST;
                verticalFlame = Flame.FlameType.VERTICAL_TOP_LAST;
            } else if (i == Bomber.flameLength) {
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

    private void removeExcessHorizontalFlame() {
        int n = horizontalFlames.size();
        for (int i = horizontalFlames.size() - 1; i >= 0; i--) {
            Flame flame = horizontalFlames.get(i);
            if (BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass) {
                continue;
            }

            if (i > n / 2) {
                for (int j = i + 1; j < horizontalFlames.size(); j++) {
                    horizontalFlames.remove(j--);
                }
            } else {
                for (int j = 0; j < i; ) {
                    horizontalFlames.remove(j);
                    i--;
                }
                break;
            }
        }
    }

    private void removeExcessVerticalFlame() {
        int n = verticalFlames.size();
        for (int i = verticalFlames.size() - 1; i >= 0; i--) {
            Flame flame = verticalFlames.get(i);
            if (BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass) {
                continue;
            }

            if (i >= n / 2) {
                for (int j = i + 1; j < verticalFlames.size(); j++) {
                    verticalFlames.remove(j--);
                }
            } else {
                for (int j = 0; j < i; ) {
                    verticalFlames.remove(j);
                    i--;
                }
            }
        }
    }

    private void removeCollidedHorizontalFlame() {
        for (int i = horizontalFlames.size() - 1; i >= 0; i--) {
            Flame flame = horizontalFlames.get(i);
            if (!(BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass)) {
                horizontalFlames.remove(i);
            }
        }
    }

    private void removeCollidedVerticalFlame() {
        for (int i = verticalFlames.size() - 1; i >= 0; i--) {
            Flame flame = verticalFlames.get(i);
            if (!(BombermanGame.map[flame.getYUnit()][flame.getXUnit()] instanceof Grass)) {
                verticalFlames.remove(i);
            }
        }
    }

    private void removeExcessFlame() {
        removeExcessHorizontalFlame();
        removeExcessVerticalFlame();
    }

    private void removeCollidedFlame() {
        removeCollidedHorizontalFlame();
        removeCollidedVerticalFlame();
    }

    @Override
    public void update() {
        if (horizontalFlames.isEmpty() && System.nanoTime() - startTime >= DURATION_BOMB_EXPLOSION) {
            createFlame();
            removeExcessFlame();
            horizontalFlames.forEach(Flame::destroyAndKill);
            verticalFlames.forEach(Flame::destroyAndKill);
            removeCollidedFlame();
        }

        for (int i = 0; i < horizontalFlames.size(); i++) {
            if (horizontalFlames.get(i).checkFinishedFlame()) {
                Bomber.bombs.remove(this);
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