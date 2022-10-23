/*package uet.oop.bomberman.Menu;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.Graphics;
import java.awt.image.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    public static final int B_WIDTH_DEFAULT = 140;
    public static final int B_HEIGHT_DEFAULT = 56;
    public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Sprite.SCALED_SIZE);
    public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Sprite.SCALED_SIZE);
    private int xPos;
    private int yPos;
    private int index;
    private Gamestate state;
    private Image[][] images = new Image[6][2];
    //private Image img , img1, img2, img3, img4;
    private boolean mouseOver;
    private boolean mousePress;
    private Rectangle bounds;
    public MenuButton(int xPos, int yPos, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos, yPos, B_WIDTH, B_HEIGHT);
    }

    private void loadImgs() {
        images[0][0] = new Image("/final/final res bomberman/product/startgame/startgame_first.png");
        images[0][1] = new Image("/final/final res bomberman/product/startgame/startgame_last.png");
        images[1][0] = new Image("/final/final res bomberman/product/opts/opt_first.png");
        images[1][1] = new Image("/final/final res bomberman/product/opts/opt_last.png");
        images[2][0] = new Image("/final/final res bomberman/product/instruction/ins_first.png");
        images[2][1] = new Image("/final/final res bomberman/product/instruction/ins_last.png");
        images[3][0] = new Image("/final/final res bomberman/product/highsc/highsc_first.png");
        images[3][1] = new Image("/final/final res bomberman/product/highsc/highsc_last.png");
        images[4][0] = new Image("/final/final res bomberman/product/quit/quit_first.png");
        images[4][1] = new Image("/final/final res bomberman/product/quit/quit_last.png");
        images[5][0] = new Image("/final/final res bomberman/product/cre/cre_first.png");
        images[5][1] = new Image("/final/final res bomberman/product/cre/cre_last.png");
        img = new Image("/final/final res bomberman/product/startgame/startgame_first.png");
        img1 = new Image("/final/final res bomberman/product/opts/opt_first.png");
        img2 = new Image("/final/final res bomberman/product/instruction/ins_first.png");
        img3 = new Image("/final/final res bomberman/product/cre/cre_first.png");
        img4 = new Image("/final/final res bomberman/product/quit/quit_first.png");
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0][index], 620, 120);
        gc.drawImage(images[1][index], 620, 190);
        gc.drawImage(images[2][index], 620, 260);
        gc.drawImage(images[3][index], 620, 330);
        gc.drawImage(images[4][index], 620, 400);
        //gc.drawImage(images[5][index], 10, 10);

    }

    public void update() {
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /*public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePress() {
        return mousePress;
    }

    public void setMousePress(boolean mousePress) {
        this.mousePress = mousePress;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mousePress = true;
        mouseOver = false;
    }
}*/
