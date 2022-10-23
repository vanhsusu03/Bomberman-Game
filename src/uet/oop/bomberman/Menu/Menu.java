package uet.oop.bomberman.Menu;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethod {
    private MenuButton[] buttons = new MenuButton[5];
    private final Image backgroundImg = new Image("final/final res bomberman/product/backgrmenu/bomberman.png");
    private int menuX, menuY, menuWidth, menuHeiht;

    public Menu() {
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        menuWidth = 992;
        menuHeiht = 512;
        //menuX = BombermanGame.WIDTH / 2 - menuWidth / 2;
        //menuY = (int) ( 45 * Sprite.SCALED_SIZE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(620, 120, Gamestate.PLAYING);
        buttons[1] = new MenuButton(620, 190, Gamestate.OPTION);
        buttons[2] = new MenuButton(620, 260, Gamestate.INSTRUCTION);
        buttons[3] = new MenuButton(620, 330, Gamestate.HIGHSCORE);
        buttons[4] = new MenuButton(620, 400, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(backgroundImg, 0, 0);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons) {
            if(isIn(e, mb)) {
                mb.setMousePress(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons) {
            if(isIn(e, mb)) {
                if(mb.isMousePress()) {
                    mb.applyGamestate();
                }
                break;
            }
        }

        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb : buttons) {
            mb.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }
        for(MenuButton mb : buttons) {
            if(isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }
}