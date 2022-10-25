package uet.oop.bomberman.UI.Panels;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.KeyAction;
import uet.oop.bomberman.Time;
import uet.oop.bomberman.UI.Icon.Icon;
import uet.oop.bomberman.UI.Icon.Pause;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

//import static com.sun.java.accessibility.util.AWTEventMonitor.*;


public class Control extends Panel implements MouseListener {
    private Time timeKeeper;
    private Icon pauseIcon;

    public Paused pausedMenu = new Paused(250, 150, Sprite.paused_panel);//Panel pause

    private AnchorPane containers;
    private int DEFAULT_FONT_SIZE = 25;

    public Control(int x, int y, Image img) {
        super(x, y, img);
        timeKeeper = new Time();
        pauseIcon = new Pause(915, 430, Sprite.ic_pause_first);
    }

    @Override
    public void setCoordinatesMouse(double x, double y) {
        pauseIcon.setMouseGrid(x, y);

    }

    @Override
    public void setMouseClicked(boolean isClicked) {
        pauseIcon.setClicked(isClicked);
    }


    private void createButton() {

    }

    @Override
    public void update() {
        createButton();
        pauseIcon.update();
        if (pauseIcon.canTransfer() || KeyAction.keys[KeyEvent.VK_ESCAPE]) {
            System.out.println("CLICKED");
//            pauseIcon.setClicked(false);
//            KeyAction.keys[KeyEvent.VK_ESCAPE] = false;
            pausedMenu.setRunning(true);
        } else {
            pausedMenu.setRunning(false);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        pauseIcon.render(gc);
        //pausedMenu.render(gc);
    }

    @Override
    public void runningPanel(GraphicsContext gc) {
        update();
        render(gc);
        pausedMenu.runningPanel(gc);
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
