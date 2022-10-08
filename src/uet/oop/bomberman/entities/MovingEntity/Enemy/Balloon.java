package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.RandomMove;
import uet.oop.bomberman.graphics.SpriteSheet;

import java.awt.*;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Balloon extends Enemy {
    public Balloon(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        img = sprite.getFxImage();
    }
    RandomMove moveRandom = new RandomMove(x,y,speed);

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
        update();
        gc.drawImage(img, moveRandom.getX(), moveRandom.getY());
    }
}
