package uet.oop.bomberman.entities.MovingEntity.Bomber;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Bomb;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusTarget;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.GoddessMask;
import uet.oop.bomberman.entities.StillEntity.Item.Item;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BombpassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BrickpassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.DetonatorItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.FlamepassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.MysteryItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.PowerUpItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.SpeedItem;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Bomber extends MovingEntity {

    public static int xGridBomber;
    public static int yGridBomber;
    private int flameLength = 1;
    private List<Bomb> bombs = new ArrayList<>();
    private int maxNumberOfBombs = 1;
    private boolean isCanDetonateOldestBomb;
    private Set<String> outerCirclePositions = new HashSet<>();

    public Bomber() {
        super();
    }

    public Bomber(Bomber other) {
        super(other.gridX, other.gridY, other.speed, other.sprite);
        xGridBomber = other.gridX;
        yGridBomber = other.gridY;
    }

    public Bomber(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        xGridBomber = xUnit;
        yGridBomber = yUnit;
    }

    public static int getxGridBomber() {
        return xGridBomber;
    }

    public static int getyGridBomber() {
        return yGridBomber;
    }


    public List<Bomb> getBombs() {
        return Collections.unmodifiableList(bombs);
    }

    public void removeAElementInBombs(Bomb bomb) {
        bombs.remove(bomb);
    }

    public int getFlameLength() {
        return flameLength;
    }

    private boolean isHavingBombAtPosition(int xUnit, int yUnit) {
        for (Bomb bomb : bombs) {
            if (bomb.getXUnit() == xUnit && bomb.getYUnit() == yUnit) {
                return true;
            }
        }
        return false;
    }

    private void putBomb() {
        int xBomb = (int) Math.round((double) x / Sprite.SCALED_SIZE);
        int yBomb = (int) Math.round((double) y / Sprite.SCALED_SIZE);
        if (bombs.size() < maxNumberOfBombs
                && BombermanGame.map[yBomb][xBomb] instanceof Grass
                && !isHavingBombAtPosition(xBomb, yBomb)) {
            bombs.add(new Bomb(xBomb, yBomb, Sprite.bomb));
            BombermanGame.putBombSound.play(0, true);
        }
    }

    private void useMysteryItem() {
        Random random = new Random();
        int ranNum = random.nextInt(PowerUpItem.NUMBER_OF_ITEMS - 1);
        switch (ranNum) {
            case 0:
                speed++;
                System.out.println("speed");
                break;
            case 1:
                flameLength++;
                System.out.println("flame");
                break;
            case 2:
                maxNumberOfBombs++;
                System.out.println("bomb");
                break;
            case 3:
                isCanPassFlames = true;
                System.out.println("passflame");
                break;
            case 4:
                isCanPassBombs = true;
                System.out.println("passbomb");
                break;
            case 5:
                isCanPassBrick = true;
                System.out.println("brick");
                break;
            case 6:
                isCanDetonateOldestBomb = true;
                System.out.println("detonate");
                break;
        }
    }

    public boolean isAloneInMap() {
        return BombermanGame.movingEntities.size() == 1
                && BombermanGame.movingEntities.get(0) instanceof Bomber;
    }

    private void eatItem(int i, int j) {
        if (BombermanGame.map[i][j] instanceof SpeedItem) {
            speed++;
        } else if (BombermanGame.map[i][j] instanceof FlameItem) {
            flameLength++;
        } else if (BombermanGame.map[i][j] instanceof BombItem) {
            maxNumberOfBombs++;
        } else if (BombermanGame.map[i][j] instanceof FlamepassItem) {
            isCanPassFlames = true;
        } else if (BombermanGame.map[i][j] instanceof BombpassItem) {
            isCanPassBombs = true;
        } else if (BombermanGame.map[i][j] instanceof BrickpassItem) {
            isCanPassBrick = true;
        } else if (BombermanGame.map[i][j] instanceof DetonatorItem) {
            isCanDetonateOldestBomb = true;
        } else if (BombermanGame.map[i][j] instanceof MysteryItem) {
            useMysteryItem();
        } else if (BombermanGame.map[i][j] instanceof BonusItem) {
            BombermanGame.score += BombermanGame.bonusItem.getPoint();
        }
        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
        BombermanGame.eatItemSound.play(0, true);
    }

    private void handleIfBonusTargetIsActivated() {
        if (BombermanGame.bonusItem instanceof BonusTarget
                && BombermanGame.bonusItem.checkConditionToSpawn()) {
            BombermanGame.bonusItem.spawn();
        }
    }

    private void usePortal(int i, int j) {
        handleIfBonusTargetIsActivated();

        for (MovingEntity movingEntity : BombermanGame.movingEntities) {
            if (movingEntity instanceof Enemy) {
                return;
            }
        }

        BombermanGame.levelCompleteSound.play(0, false);
    }

    @Override
    public void move() {
        if (KeyAction.keys[KeyEvent.VK_UP]) {
            y -= speed;
            BombermanGame.moveUpDownSound.play(-1, false);
        } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
            y += speed;
            BombermanGame.moveUpDownSound.play(-1, false);
        } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
            x -= speed;
            BombermanGame.moveLeftRightSound.play(-1, false);
        } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
            x += speed;
            BombermanGame.moveLeftRightSound.play(-1, false);
        } else if (KeyAction.keys[KeyEvent.VK_SPACE]) {
            putBomb();
            KeyAction.keys[KeyEvent.VK_SPACE] = false;
        }
    }

    private void handleCollisionWithItemIn1Cell(int i, int j) {
        if (BombermanGame.map[i][j] instanceof Item) {
            eatItem(i, j);
        }
    }

    private void handleCollisionWithItemIn4Cells(int xUnit1, int yUnit1,
                                                 int xUnit2, int yUnit2) {
        handleCollisionWithItemIn1Cell(yUnit1, xUnit1);
        handleCollisionWithItemIn1Cell(yUnit1, xUnit2);
        handleCollisionWithItemIn1Cell(yUnit2, xUnit1);
        handleCollisionWithItemIn1Cell(yUnit2, xUnit2);
    }

    private void handleCollisionWithPortalIn1Cell(int i, int j) {
        if (BombermanGame.map[i][j] instanceof Portal) {
            usePortal(i, j);
        }
    }

    private void handleCollisionWithPortalIn4Cells(int xUnit1, int yUnit1,
                                                   int xUnit2, int yUnit2) {
        handleCollisionWithPortalIn1Cell(yUnit1, xUnit1);
        handleCollisionWithPortalIn1Cell(yUnit1, xUnit2);
        handleCollisionWithPortalIn1Cell(yUnit2, xUnit1);
        handleCollisionWithPortalIn1Cell(yUnit2, xUnit2);
    }

    public void death() {
        BombermanGame.bomberDeathSound.play(0, false);
    }

    private void handleCollisionWithEnemy() {
        for (MovingEntity movingEntity : BombermanGame.movingEntities) {
            if (!(movingEntity instanceof Enemy) || movingEntity.isDead()) {
                continue;
            }
            if (checkIntersectionWithOtherMovingEntity(movingEntity.getX(), movingEntity.getY(),
                    movingEntity.getX() + movingEntity.getSprite().get_realWidth(),
                    movingEntity.getY() + movingEntity.getSprite().get_realHeight())) {
                setDead(true);
                return;
            }
        }
    }

    private void handleBombPass(int xUnit1, int yUnit1,
                                int xUnit2, int yUnit2) {
        if (!bombs.isEmpty() && bombs.get(bombs.size() - 1).isBomberCanPass()) {
            int xUnitBomb = bombs.get(bombs.size() - 1).getXUnit();
            int yUnitBomb = bombs.get(bombs.size() - 1).getYUnit();

            if (!checkBothInACell(xUnitBomb, yUnitBomb, xUnit1, yUnit1)
                    && !checkBothInACell(xUnitBomb, yUnitBomb, xUnit1, yUnit2)
                    && !checkBothInACell(xUnitBomb, yUnitBomb, xUnit2, yUnit1)
                    && !checkBothInACell(xUnitBomb, yUnitBomb, xUnit2, yUnit2)
                    && !isCanPassBombs) {
                bombs.get(bombs.size() - 1).setBomberCanPass(false);
            }
        }
    }

    private void detonateOldestBomb() {
        if (isCanDetonateOldestBomb && !bombs.isEmpty()) {
            bombs.get(0).setExplosion();
        }
    }

    private boolean isWentOuterCircle() {
        return outerCirclePositions.size()
                >= 2 * ((BombermanGame.WIDTH - 2) + (BombermanGame.HEIGHT - 2)) - 4;
    }

    private void handleIfGoddessMaskIsActivated() {
        if (BombermanGame.bonusItem instanceof GoddessMask
                && BombermanGame.bonusItem.checkConditionToSpawn()) {
            updateCenterPosition();
            int centerXUnit = centerX / Sprite.SCALED_SIZE;
            int centerYUnit = centerY / Sprite.SCALED_SIZE;
            if (centerXUnit == 1 || centerYUnit == 1
                    || centerXUnit == BombermanGame.WIDTH - 2
                    || centerYUnit == BombermanGame.HEIGHT - 2) {
                outerCirclePositions.add(centerXUnit + " " + centerYUnit);
            } else {
                outerCirclePositions.clear();
            }

            if (isWentOuterCircle()) {
                BombermanGame.bonusItem.spawn();
            }
        }
    }

    private void handleCollisionWithBrickWall(int oldX, int oldY) {
        int oldXUnit = oldX / Sprite.SCALED_SIZE;
        int oldYUnit = oldY / Sprite.SCALED_SIZE;

        if ((double) oldY / Sprite.SCALED_SIZE - oldYUnit >= 0.55 && x > oldX
                && isCellCanCome(oldXUnit + 1, oldYUnit + 1)) {
            y += speed;
        } else if ((double) oldY / Sprite.SCALED_SIZE - oldYUnit <= 0.45 && x > oldX
                && isCellCanCome(oldXUnit + 1, oldYUnit)) {
            y -= speed;
        } else if ((double) oldY / Sprite.SCALED_SIZE - oldYUnit >= 0.55 && x < oldX
                && isCellCanCome(oldXUnit - 1, oldYUnit + 1)) {
            y += speed;
        } else if ((double) oldY / Sprite.SCALED_SIZE - oldYUnit <= 0.45 && x < oldX
                && isCellCanCome(oldXUnit - 1, oldYUnit)) {
            y -= speed;
        } else if ((double) oldX / Sprite.SCALED_SIZE - oldXUnit <= 0.6 && y > oldY
                && isCellCanCome(oldXUnit, oldYUnit + 1)) {
            x -= speed;
        } else if ((double) oldX / Sprite.SCALED_SIZE - oldXUnit >= 0.55 && y > oldY
                && isCellCanCome(oldXUnit + 1, oldYUnit + 1)) {
            x += speed;
        } else if ((double) oldX / Sprite.SCALED_SIZE - oldXUnit < 0.62 && y < oldY
                && isCellCanCome(oldXUnit, oldYUnit - 1)) {
            x -= speed;
        } else if ((double) oldX / Sprite.SCALED_SIZE - oldXUnit > 0.62 && y < oldY
                && isCellCanCome(oldXUnit + 1, oldYUnit - 1)) {
            x += speed;
        } else {
            x = oldX;
            y = oldY;
        }
    }

    @Override
    public void update() {
        if (isDead) {
            return;
        }

        if (KeyAction.keys[KeyEvent.VK_D]) {
            detonateOldestBomb();
            KeyAction.keys[KeyEvent.VK_D] = false;
        }

        int oldX = x, oldY = y;
        move();

        if (!checkCanMove(x, y)) {
            handleCollisionWithBrickWall(oldX, oldY);
        } else {
            int xUnit1 = x / Sprite.SCALED_SIZE;
            int yUnit1 = y / Sprite.SCALED_SIZE;
            int xUnit2 = (x + sprite.get_realWidth()) / Sprite.SCALED_SIZE;
            int yUnit2 = (y + sprite.get_realHeight()) / Sprite.SCALED_SIZE;

            handleCollisionWithItemIn4Cells(xUnit1, yUnit1, xUnit2, yUnit2);
            handleCollisionWithPortalIn4Cells(xUnit1, yUnit1, xUnit2, yUnit2);
            handleCollisionWithEnemy();
            handleBombPass(xUnit1, yUnit1, xUnit2, yUnit2);
            handleIfGoddessMaskIsActivated();
        }
        xGridBomber = x / Sprite.SCALED_SIZE;
        yGridBomber = y / Sprite.SCALED_SIZE;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, frameCount, 60);
            img = sprite.getFxImage();
        } else {
            if (KeyAction.keys[KeyEvent.VK_UP]) {
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1,
                        Sprite.player_up_2, frameCount, 18);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_DOWN]) {
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1,
                        Sprite.player_down_2, frameCount, 18);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_LEFT]) {
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1,
                        Sprite.player_left_2, frameCount, 18);
                img = sprite.getFxImage();
            } else if (KeyAction.keys[KeyEvent.VK_RIGHT]) {
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1,
                        Sprite.player_right_2, frameCount, 18);
                img = sprite.getFxImage();
            }
        }
        updateFrameCount();
        gc.drawImage(img, x, y);
    }
}