package uet.oop.bomberman.Menu2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Menu {
    public Menu() {}
    private final Image backgroundImg = new Image("final/final res bomberman/product/backgrmenu/bomberman.png");

    public void draw(GraphicsContext gc) {
        gc.drawImage(backgroundImg, 0, 0);
    }

    public void update() {
    }
}
