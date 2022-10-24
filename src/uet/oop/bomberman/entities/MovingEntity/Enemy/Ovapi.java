package uet.oop.bomberman.entities.MovingEntity.Enemy;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.ChasingBomberlv2;
import uet.oop.bomberman.graphics.Sprite;

//Speed 2 (Low) - Smart 3 (Low) - Brick Pass (consider Wall Pass)
public class Ovapi extends Enemy {
    public Ovapi(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite, wallPass, brickPass, bombPass);
        img = sprite.getFxImage();
    }

    ChasingBomberlv2 chasingBomberlv2 = new ChasingBomberlv2(x,y,speed,wallPass,brickPass,bombPass);
    @Override
    public void update() {
        chasingBomberlv2.updateChasingmoveLv2();
        setX(chasingBomberlv2.getX());
        setY(chasingBomberlv2.getY());
        switch (chasingBomberlv2.getDirection()) {
            case 0:
                img = Sprite.movingSprite(Sprite.ovapi_left1,Sprite.ovapi_left2,Sprite.ovapi_left3,frameCount,80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.ovapi_right1,Sprite.ovapi_right2,Sprite.ovapi_right3,frameCount,80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.ovapi_right1,Sprite.ovapi_left1,Sprite.ovapi_right1,frameCount,80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.ovapi_left1,Sprite.ovapi_right1,Sprite.ovapi_left1,frameCount,80).getFxImage();
                break;
        }
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, chasingBomberlv2.getX(), chasingBomberlv2.getY());
    }
}
