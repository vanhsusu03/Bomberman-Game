package uet.oop.bomberman.Menu2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HighScore {
    private final Image highsc = new Image("/final/final res bomberman/product/panel/highsc/highsc.png");

    public int[] index_highsc = new int[6];

    private Image[][] images = new Image[1][2];

    private void loadImgs() {
        images[0][0] = new Image("/final/final res bomberman/product/icon/back/back_first.png");
        images[0][1] = new Image("/final/final res bomberman/product/icon/back/back_last.png");
    }

    public HighScore() {
        loadImgs();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(highsc, 310, 150);
        gc.drawImage(images[0][index_highsc[0]], 560 , 180);
    }

    public void update() {
    }
}
