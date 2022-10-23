package uet.oop.bomberman.Menu2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class MenuButton {

    public MenuButton() {
        loadImgs();
    }
    private Image[][] images = new Image[6][2];
    public int[] index = new int[6];
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
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(images[0][index[0]], 620, 120);
        gc.drawImage(images[1][index[1]], 620, 190);
        gc.drawImage(images[2][index[2]], 620, 260);
        gc.drawImage(images[3][index[3]], 620, 330);
        gc.drawImage(images[4][index[4]], 620, 400);
        //gc.drawImage(images[5][index], 10, 10);

    }

    public void update() {

    }
}
