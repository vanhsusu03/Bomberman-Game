package uet.oop.bomberman.UI.Panels;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Time;
import uet.oop.bomberman.UI.Icon.Icon;
import uet.oop.bomberman.UI.Icon.Pause;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//import static com.sun.java.accessibility.util.AWTEventMonitor.*;


public class Control extends Panel implements MouseListener {
    private Label time;
    private Label score;
    private Label hp;

    private Time timeKeeper;

    private Icon pauseIcon;
    private int DEFAULT_FONT_SIZE = 25;
    public Control(int x, int y, Image img) {
        super(x, y, img);
        timeKeeper = new Time();
        pauseIcon = new Pause(915,430, Sprite.ic_pause_first);
    }

    @Override
    public void setCoordinatesMouse(double x, double y) {
        pauseIcon.setMouseGrid(x,y);
    }


    private void createButton() {

    }
    private void createLabels() {
        time = new javafx.scene.control.Label(new Integer(timeKeeper.countSecond()).toString());
        time.setFont(Font.font("Segoe UI Black", FontWeight.BOLD, DEFAULT_FONT_SIZE));
        time.setTextFill(Color.RED);
        time.setLayoutX(675);
        time.setLayoutY(480);
    }

    @Override
    public void update() {
        createLabels();
        createButton();
        pauseIcon.update();
    }

    @Override
    public void render(GraphicsContext gc) {
        update();
        gc.drawImage(img,x,y);
        time.setText("Time: " + (BombermanGame.maxTime - timeKeeper.countSecond()));
        pauseIcon.render(gc);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
