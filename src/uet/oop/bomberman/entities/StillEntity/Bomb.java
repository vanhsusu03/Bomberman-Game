package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends StillEntity {
    private static final int DURATION_BOMB_EXPLOSION = (int) 2e9;
    private long startTime;
    private List<Flame> flames = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        startTime = System.nanoTime();
    }

    @Override
    public void update() {
        if (flames.isEmpty() && System.nanoTime() - startTime >= DURATION_BOMB_EXPLOSION) {
            for (int i = -Bomber.flameLength; i <= Bomber.flameLength; i++) {
                int xFlame = x / Sprite.SCALED_SIZE;
                int yFlame = y / Sprite.SCALED_SIZE;

                Flame.FlameType flameType1, flameType2;
                if (i == 0) {
                    flameType1 = Flame.FlameType.CENTER;
                    flameType2 = null;
                } else if (i == -Bomber.flameLength) {
                    flameType1 = Flame.FlameType.HORIZONTAL_LEFT_LAST;
                    flameType2 = Flame.FlameType.VERTICAL_TOP_LAST;
                } else if (i == Bomber.flameLength) {
                    flameType1 = Flame.FlameType.HORIZONTAL_RIGHT_LAST;
                    flameType2 = Flame.FlameType.VERTICAL_DOWN_LAST;
                } else {
                    flameType1 = Flame.FlameType.HORIZONTAL;
                    flameType2 = Flame.FlameType.VERTICAL;
                }
                flames.add(new Flame(xFlame + i, yFlame, flameType1));
                if (i != 0) {
                    flames.add(new Flame(xFlame, yFlame + i, flameType2));
                }
            }
        }

        int n = flames.size()/2;
        for (int i = flames.size() - 1; i >= 0; i--) {
            Flame flame = flames.get(i);

            boolean abcxyz = true;

            for (MovingEntity movingEntity : BombermanGame.movingEntities) {
                if ((movingEntity instanceof Bomber || movingEntity instanceof Enemy)
                        && movingEntity.getX() / Sprite.SCALED_SIZE == flame.getX() / Sprite.SCALED_SIZE
                        && movingEntity.getY() / Sprite.SCALED_SIZE == flame.getY() / Sprite.SCALED_SIZE) {
                    abcxyz = false;
                }
            }

            for (int j = 0; j < BombermanGame.stillEntities.size(); j++) {
                StillEntity stillEntity = BombermanGame.stillEntities.get(j);
                if (stillEntity.getX() / Sprite.SCALED_SIZE == flame.getX() / Sprite.SCALED_SIZE
                        && stillEntity.getY() / Sprite.SCALED_SIZE == flame.getY() / Sprite.SCALED_SIZE) {
                    if (!(stillEntity instanceof Grass)) {
                        abcxyz = false;
                    }
                }
            }

            if (abcxyz) continue;


            if (i > n) {
                for (int j = i + 2; j < flames.size(); j++) {
                    if (flames.get(j).getFlameType() == Flame.FlameType.CENTER) {
                        continue;
                    }
                    flames.remove(j);
                }
            } else {
                for (int j = i - 2; j >= 0; j -= 2) {
                    if (flames.get(j).getFlameType() == Flame.FlameType.CENTER) {
                        continue;
                    }
                    flames.remove(j);
                }
            }
        }

        flames.forEach(Flame::update);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (flames.isEmpty()) {
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,
                    frameCount, 40).getFxImage();
            updateFrameCount();
            gc.drawImage(img, x, y);
        }

        flames.forEach(g -> g.render(gc));

        for (int i = 0; i < flames.size(); i++) {
            if (flames.get(i).checkFinishedFlame()) {
                Bomber.passBomb.remove(0);
                Bomber.bombs.remove(0);
                return;
            }
        }
    }
}
