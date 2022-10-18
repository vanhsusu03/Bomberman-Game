package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.Astar_FindingPath;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.ChasingBomberlv2;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {
    public Kondoria(int xUnit, int yUnit, int speed, Sprite sprite) {
        super(xUnit, yUnit, speed, sprite);
        img = sprite.getFxImage();
    }
    ChasingBomberlv2 chasingBomberlv2 = new ChasingBomberlv2(x,y,speed);

    @Override
    public void update() {
        chasingBomberlv2.updateChasingmoveLv2();
        setX(chasingBomberlv2.getX());
        setY(chasingBomberlv2.getY());
        switch (chasingBomberlv2.getDirection()) {
            case 0:
                img = Sprite.movingSprite(Sprite.kondoria_left1,Sprite.kondoria_left2,Sprite.kondoria_left3,frameCount,80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.kondoria_right1,Sprite.kondoria_right2,Sprite.kondoria_right3,frameCount,80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.kondoria_left1,Sprite.kondoria_right2,Sprite.kondoria_left3,frameCount,80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.kondoria_right1,Sprite.kondoria_left2,Sprite.kondoria_right3,frameCount,80).getFxImage();
                break;
        }
        updateFrameCount();
    }

}
