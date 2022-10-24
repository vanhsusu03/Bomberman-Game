package uet.oop.bomberman.entities.MovingEntity.Enemy;

import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Entity;
public abstract class Enemy extends MovingEntity {

    protected boolean wallPass;
    protected boolean brickPass;
    protected boolean bombPass;

    public Enemy(int xUnit, int yUnit, int speed, Sprite sprite, boolean wallPass, boolean brickPass, boolean bombPass) {
        super(xUnit, yUnit, speed, sprite);
        this.wallPass = wallPass;
        this.brickPass = brickPass;
        this.bombPass = bombPass;
    }

}
