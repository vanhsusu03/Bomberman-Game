package uet.oop.bomberman.UI.Panels;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.UI.Icon.Home;
import uet.oop.bomberman.UI.Icon.Icon;
import uet.oop.bomberman.UI.Icon.Resume;
import uet.oop.bomberman.graphics.Sprite;

public class Paused extends Panel {

    private Resume resume;
    private Home home;

    public Paused(int x, int y, Image img) {
        super(x, y, img);
        resume = new Resume(350,180, Sprite.ic_resume_first);
        home = new Home(410,180,Sprite.ic_home_first);
    }

    @Override
    public void setCoordinatesMouse(double x, double y) {
        resume.setMouseGrid(x,y);
        home.setMouseGrid(x,y);
    }

    @Override
    public void setMouseClicked(boolean isClicked) {
        resume.setClicked(isClicked);
        home.setClicked(isClicked);
    }

    @Override
    public void update() {
        resume.update();
        home.update();
    }

    @Override
    public void render(GraphicsContext gc) {
            gc.drawImage(img,250,90);
            resume.render(gc);
            home.render(gc);
    }

    @Override
    public void runningPanel(GraphicsContext gc) {
        if(isRunning) {
            System.out.println("ISSRUNING IFFF");
            while (isRunning) {
                System.out.println("ISRUNNIN +G WHILE");
                update();
                render(gc);
            }
        }
    }


}
