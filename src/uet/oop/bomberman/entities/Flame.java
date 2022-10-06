package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class Flame extends Entity {
    public enum FlameType {
        CENTER,
        HORIZONTAL,
        HORIZONTAL_LEFT_LAST,
        HORIZONTAL_RIGHT_LAST,
        VERTICAL,
        VERTICAL_DOWN_LAST,
        VERTICAL_TOP_LAST
    }

    private FlameType flameType;

    public Flame(int xUnit, int yUnit, FlameType flameType) {
        super(xUnit, yUnit, 0, null);
        this.flameType = flameType;
    }

    public FlameType getFlameType() {
        return flameType;
    }

    public void setFlameType(FlameType flameType) {
        this.flameType = flameType;
    }

    private boolean checkIntersectionWithMovingEntity(MovingEntity movingEntity) {
        int xUnit1 = (movingEntity.getX() + 5) / Sprite.SCALED_SIZE;
        int yUnit1 = (movingEntity.getY() + 12) / Sprite.SCALED_SIZE;
        int xUnit2 = (movingEntity.getX() + movingEntity.getSprite().get_realWidth()) / Sprite.SCALED_SIZE;
        int yUnit2 = (movingEntity.getY() + movingEntity.getSprite().get_realHeight()) / Sprite.SCALED_SIZE;

        return checkBothInACell(xUnit1, yUnit1)
                || checkBothInACell(xUnit1, yUnit2)
                || checkBothInACell(xUnit2, yUnit1)
                || checkBothInACell(xUnit2, yUnit2);
    }

    private void killMovingEntity() {
        for (MovingEntity movingEntity : BombermanGame.movingEntities) {
            if (checkIntersectionWithMovingEntity(movingEntity)) {
                movingEntity.setDead(true);
            }
        }
    }

    private void changeFlameTypeToLast(int xUnit, int yUnit) {
        if (Bomber.bombs.get(0).getXUnit() < xUnit) {
            flameType = FlameType.HORIZONTAL_RIGHT_LAST;
        } else if (Bomber.bombs.get(0).getXUnit() > xUnit) {
            flameType = FlameType.HORIZONTAL_LEFT_LAST;
        } else if (Bomber.bombs.get(0).getYUnit() < yUnit) {
            flameType = FlameType.VERTICAL_DOWN_LAST;
        } else if (Bomber.bombs.get(0).getXUnit() < xUnit) {
            flameType = FlameType.VERTICAL_TOP_LAST;
        }
    }

    private void destroyBrickAndItem() {
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        if (BombermanGame.map[yUnit][xUnit] instanceof Brick) {
            ((Brick) BombermanGame.map[yUnit][xUnit]).setDestroyed(true);
        } else if (BombermanGame.map[yUnit][xUnit] instanceof Item) {
            BombermanGame.map[yUnit][xUnit] = new Grass(xUnit, yUnit, Sprite.grass);
            changeFlameTypeToLast(xUnit, yUnit);
        }
    }

    public void destroyAndKill() {
        killMovingEntity();
        destroyBrickAndItem();
    }

    public boolean checkFinishedFlame() {
        return sprite != null && (sprite.equals(Sprite.bomb_exploded2)
                || sprite.equals(Sprite.explosion_horizontal2)
                || sprite.equals(Sprite.explosion_horizontal_left_last2)
                || sprite.equals(Sprite.explosion_horizontal_right_last2)
                || sprite.equals(Sprite.explosion_vertical2)
                || sprite.equals(Sprite.explosion_vertical_down_last2)
                || sprite.equals(Sprite.explosion_vertical_top_last2));
    }

    @Override
    public void update() {
    }

    @Override
    public void render(GraphicsContext gc) {
        switch (flameType) {
            case CENTER:
                sprite = Sprite.movingSprite(Sprite.bomb_exploded,
                        Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal,
                        Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL_LEFT_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last,
                        Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case HORIZONTAL_RIGHT_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical,
                        Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL_DOWN_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last,
                        Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
            case VERTICAL_TOP_LAST:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last,
                        Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2,
                        frameCount, 20);
                img = sprite.getFxImage();
                break;
        }
        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}