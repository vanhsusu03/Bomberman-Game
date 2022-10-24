package uet.oop.bomberman.Menu2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Instruction {
    private final Image ins = new Image("final/final res bomberman/product/panel/highsc/highsc.png");

    public int[] index_ins = new int[6];

    private Image[][] images = new Image[3][2];

    private void loadImgs() {
        images[0][0] = new Image("/final/final res bomberman/product/icon/back/back_first.png");
        images[0][1] = new Image("/final/final res bomberman/product/icon/back/back_last.png");
    }

    public Instruction() {
        loadImgs();
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(ins, 310, 150);
        gc.drawImage(images[0][index_ins[0]], 560 , 180);
    }

    public void update() {
    }
}
