package uet.oop.bomberman.entities.MovingEntity.Enemy;


import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.MovingEntity.Enemy.PathFinding.ChasingBomberAStar;
import uet.oop.bomberman.graphics.Sprite;

//Speed 4 (fatest) - Smart 4 (highest)?
public class Pass extends Enemy {
    public Pass(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite, wallPass, brickPass, bombPass);
        img = sprite.getFxImage();
    }

    ChasingBomberAStar chasingBomberAStar = new ChasingBomberAStar(x,y,speed, wallPass,brickPass,bombPass);

    @Override
    public void update() {
        chasingBomberAStar.updateChasingmoveAStar();
        setX(chasingBomberAStar.getX());
        setY(chasingBomberAStar.getY());
        switch (chasingBomberAStar.getDirection()) {
            case 0:
                img = Sprite.movingSprite(Sprite.pass_left1,Sprite.pass_left2,Sprite.pass_left3,frameCount,80).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.pass_right1,Sprite.pass_right2,Sprite.pass_right3,frameCount,80).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.pass_left1,Sprite.pass_right2,Sprite.pass_left3,frameCount,80).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.pass_right1,Sprite.pass_left2,Sprite.pass_right3,frameCount,80).getFxImage();
                break;
        }
        updateFrameCount();
    }

    @Override
    public void render(GraphicsContext gc) {
        //System.out.println(chasingBomberAStar.getX() + " "+chasingBomberAStar.getY());
        gc.drawImage(img,chasingBomberAStar.getX(),chasingBomberAStar.getY());
    }
}
