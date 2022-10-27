package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.ChasingBomberlv1;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite, wallPass, brickPass, bombPass);
        img = sprite.getFxImage();
        score = 2;
    }

    ChasingBomberlv1 chasingBomberlv1 = new ChasingBomberlv1(x, y, speed, wallPass, brickPass, bombPass);

    @Override
    public void update() {
        chasingBomberlv1.updateChasingMoveLv1();
        setX(chasingBomberlv1.getX());
        setY(chasingBomberlv1.getY());
        switch (chasingBomberlv1.getDirection()) {
            case 0:
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, frameCount, 80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, frameCount, 80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_left1, Sprite.oneal_right1, frameCount, 80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_right1, Sprite.oneal_left1, frameCount, 80).getFxImage();
                break;
        }
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead1,
                    Sprite.mob_dead2, Sprite.mob_dead3,
                    frameCount, TIME_MOVING_DEAD_SPRITE);
            img = sprite.getFxImage();
        }
        gc.drawImage(img, chasingBomberlv1.getX(), chasingBomberlv1.getY());
        removeEnemyIfDeathAnimationEnds();
    }

    @Override
    protected void move() {

    }
}