package uet.oop.bomberman.entities.StillEntity;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Flame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends StillEntity {
    private static final int DURATION_BOMB_EXPLOSION = (int) 2e9;
    private long startTime;

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        startTime = System.nanoTime();
    }

    @Override
    public void update() {
        if (System.nanoTime() - startTime >= DURATION_BOMB_EXPLOSION) {
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
                BombermanGame.flames.add(new Flame(xFlame + i, yFlame, flameType1));
                if (i != 0) {
                    BombermanGame.flames.add(new Flame(xFlame, yFlame + i, flameType2));
                }
            }
            Bomber.bombs.remove(0);
            Bomber.passBomb.remove(0);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,
                frameCount, 40).getFxImage();

        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}
