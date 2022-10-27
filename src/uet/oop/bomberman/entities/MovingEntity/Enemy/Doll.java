package uet.oop.bomberman.entities.MovingEntity.Enemy;


import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.RandomMove;
import uet.oop.bomberman.graphics.Sprite;

// Speed 3 (Normal) - Smart: 1 (Lowest)
public class Doll extends Enemy {

    public Doll(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite, wallPass, brickPass, bombPass);
        img = sprite.getFxImage();
        score = 4;
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
                img = Sprite.movingSprite(Sprite.doll_left1,Sprite.doll_left2,Sprite.doll_left3,frameCount,80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.doll_right1,Sprite.doll_right2,Sprite.doll_right3,frameCount,80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.doll_right1,Sprite.doll_left1,Sprite.doll_right1,frameCount,80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.doll_left1,Sprite.doll_right1,Sprite.doll_left1,frameCount,80).getFxImage();
                break;
        }
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        if (isDead) {
            sprite = Sprite.movingSprite(Sprite.doll_dead, Sprite.mob_dead1,
                    Sprite.mob_dead2, Sprite.mob_dead3,
                    frameCount, TIME_MOVING_DEAD_SPRITE);
            img = sprite.getFxImage();
        }
        gc.drawImage(img, moveRandom.getX(), moveRandom.getY());
        removeEnemyIfDeathAnimationEnds();
    }

    @Override
    protected void move() {

    }
}
