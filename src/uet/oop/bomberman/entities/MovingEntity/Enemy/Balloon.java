package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.RandomMove;

public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite, wallPass, brickPass, bombPass);
        img = sprite.getFxImage();
        score = 1;
    }

    RandomMove moveRandom = new RandomMove(x,y,speed, wallPass, brickPass, bombPass);

    /**
     * Change animations from move: 0 - L, 1 - R, 2 - U, 3 - D
     */
    @Override
    public void update() {
        moveRandom.updateRandomMove();
        setX(moveRandom.getX());
        setY(moveRandom.getY());
        switch (moveRandom.getDirection()) {
            case 0:
                img = Sprite.movingSprite(Sprite.balloon_left1,Sprite.balloon_left2,Sprite.balloon_left3,frameCount,80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.balloon_right1,Sprite.balloon_right2,Sprite.balloon_right3,frameCount,80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.balloon_right1,Sprite.balloon_left1,Sprite.balloon_right1,frameCount,80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.balloon_left1,Sprite.balloon_right1,Sprite.balloon_left1,frameCount,80).getFxImage();
                break;
        }
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            if(!hasChangedFrame) {
                frameCount = 0;
            }
            hasChangedFrame = true;
            sprite = Sprite.movingSprite(Sprite.balloon_dead, Sprite.mob_dead1,
                    Sprite.mob_dead2, Sprite.mob_dead3,
                    frameCount, TIME_MOVING_DEAD_SPRITE);
            img = sprite.getFxImage();
            moveRandom.setSpeed(0);
        }
        gc.drawImage(img, moveRandom.getX(), moveRandom.getY());
        removeEnemyIfDeathAnimationEnds();
    }

    @Override
    protected void move() {

    }
}
