package uet.oop.bomberman.Menu2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Options {
    private final Image options = new Image("final/final res bomberman/product/panel/options/options.png");

    public int[] index_opt = new int[6];

    private Image[][] images = new Image[5][2];

    private void loadImgs() {
        images[0][0] = new Image("/final/final res bomberman/product/icon/back/back_first.png");
        images[0][1] = new Image("/final/final res bomberman/product/icon/back/back_last.png");
        images[1][0] = new Image("/final/final res bomberman/product/icon/onsound/onsound_first.png");
        images[1][1] = new Image("/final/final res bomberman/product/icon/onsound/onsound_last.png");
        images[2][0] = new Image("/final/final res bomberman/product/icon/mute sound/mutesound_first.png");
        images[2][1] = new Image("/final/final res bomberman/product/icon/mute sound/mutesound_last.png");
        images[3][0] = images[1][0];
        images[3][1] = images[1][1];
        images[4][0] = images[2][0];
        images[4][1] = images[2][1];
    }

    public Options() {
        loadImgs();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(options, 260, 100);
        gc.drawImage(images[0][index_opt[0]], 650 , 150);
        gc.drawImage(images[1][index_opt[1]], 480, 230);
        gc.drawImage(images[3][index_opt[2]], 485, 310);
    }

    public void update() {
    }
}
