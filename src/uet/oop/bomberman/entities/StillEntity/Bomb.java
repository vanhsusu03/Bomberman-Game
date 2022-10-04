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
    private List<Flame> horizontalFlames = new ArrayList<>();
    private List<Flame> verticalFlames = new ArrayList<>();

    public Bomb(int xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
        startTime = System.nanoTime();
    }

    public void createFlame() {
        if (horizontalFlames.isEmpty() && System.nanoTime() - startTime >= DURATION_BOMB_EXPLOSION) {
            for (int i = -Bomber.flameLength; i <= Bomber.flameLength; i++) {
                int xFlame = x / Sprite.SCALED_SIZE;
                int yFlame = y / Sprite.SCALED_SIZE;

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
                horizontalFlames.add(new Flame(xFlame + i, yFlame, horizontalFlame));
                if (i != 0) {
                    verticalFlames.add(new Flame(xFlame, yFlame + i, verticalFlame));
                }
            }

            int n = horizontalFlames.size();
            for (int i = horizontalFlames.size() - 1; i >= 0; i--) {
                Flame flame = horizontalFlames.get(i);

                boolean abcxyz = true;

//                for (MovingEntity movingEntity : BombermanGame.movingEntities) {
//                    if ((movingEntity instanceof Bomber || movingEntity instanceof Enemy)
//                            && movingEntity.checkTightIntersection(flame.getX(), flame.getY(),
//                            flame.getX() + Sprite.DEFAULT_SIZE,
//                            flame.getY() + Sprite.DEFAULT_SIZE)) {
//                        abcxyz = false;
//                    }
//                }

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

                if (i > n / 2) {
                    for (int j = i + 1; j < horizontalFlames.size(); j++) {
                        horizontalFlames.remove(j--);
                    }
                } else {
                    for (int j = 0; j < i; j++) {
                        horizontalFlames.remove(j--);
                        i--;
                    }
                }
            }

            n = verticalFlames.size();
            for (int i = verticalFlames.size() - 1; i >= 0; i--) {
                Flame flame = verticalFlames.get(i);

                boolean abcxyz = true;

//                for (MovingEntity movingEntity : BombermanGame.movingEntities) {
//                    if ((movingEntity instanceof Bomber || movingEntity instanceof Enemy)
//                            && movingEntity.checkTightIntersection(flame.getX(), flame.getY(),
//                            flame.getX() + Sprite.DEFAULT_SIZE,
//                            flame.getY() + Sprite.DEFAULT_SIZE)) {
//                        abcxyz = false;
//                    }
//                }

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

                if (i >= n / 2) {
                    for (int j = i + 1; j < verticalFlames.size(); j++) {
                        verticalFlames.remove(j--);
                    }
                } else {
                    for (int j = 0; j < i; j++) {
                        verticalFlames.remove(j--);
                        i--;
                    }
                }
            }
            horizontalFlames.forEach(Flame::update);
            verticalFlames.forEach(Flame::update);

            for (int i = horizontalFlames.size() - 1; i >= 0; i--) {
                Flame flame = horizontalFlames.get(i);

                boolean abcxyz = true;



                for (int j = 0; j < BombermanGame.stillEntities.size(); j++) {
                    StillEntity stillEntity = BombermanGame.stillEntities.get(j);
                    if (stillEntity.getX() / Sprite.SCALED_SIZE == flame.getX() / Sprite.SCALED_SIZE
                            && stillEntity.getY() / Sprite.SCALED_SIZE == flame.getY() / Sprite.SCALED_SIZE) {
                        if (!(stillEntity instanceof Grass)) {
                            abcxyz = false;
                            if (stillEntity instanceof Item) {
                                BombermanGame.stillEntities.remove(j);
                                j--;
                            }
                        }
                    }
                }

                if (abcxyz) continue;

                horizontalFlames.remove(i);
            }

            for (int i = verticalFlames.size() - 1; i >= 0; i--) {
                Flame flame = verticalFlames.get(i);

                boolean abcxyz = true;


                for (int j = 0; j < BombermanGame.stillEntities.size(); j++) {
                    StillEntity stillEntity = BombermanGame.stillEntities.get(j);

                    if (stillEntity.getX() / Sprite.SCALED_SIZE == flame.getX() / Sprite.SCALED_SIZE
                            && stillEntity.getY() / Sprite.SCALED_SIZE == flame.getY() / Sprite.SCALED_SIZE) {
                        if (!(stillEntity instanceof Grass)) {
                            abcxyz = false;
                            if (stillEntity instanceof Item) {
                                BombermanGame.stillEntities.remove(j);
                                j--;
                            }
                        }
                    }
                }

                if (abcxyz) continue;

                verticalFlames.remove(i);
            }
        }
    }

    @Override
    public void update() {
        createFlame();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (horizontalFlames.isEmpty()) {
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,
                    frameCount, 40).getFxImage();
            updateFrameCount();
            gc.drawImage(img, x, y);
        }

        horizontalFlames.forEach(g -> g.render(gc));
        verticalFlames.forEach(g -> g.render(gc));

        for (int i = 0; i < horizontalFlames.size(); i++) {
            if (horizontalFlames.get(i).checkFinishedFlame()) {
                Bomber.passBomb.remove(0);
                Bomber.bombs.remove(0);
                return;
            }
        }
    }
}
