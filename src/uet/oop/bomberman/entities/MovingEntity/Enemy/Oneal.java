package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.ChasingBomberlv1;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.RandomMove;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        img = sprite.getFxImage();
    }
    ChasingBomberlv1 chasingBomberlv1 = new ChasingBomberlv1(x,y,speed);
    @Override
    public void update() {
        chasingBomberlv1.updateChasingmoveLv1();
        setX(chasingBomberlv1.getX());
        setY(chasingBomberlv1.getY());
        img = Sprite.movingSprite(sprite, Sprite.oneal_right2,Sprite.oneal_right3, frameCount, 80).getFxImage();
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, chasingBomberlv1.getX(), chasingBomberlv1.getY());
    }
}
