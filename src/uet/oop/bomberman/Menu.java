package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.UI.Button.*;
import uet.oop.bomberman.UI.Button.StartGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.MapLoadFile;

public class Menu {

    private final int xButton = 600;
    private final int yButton = 135;
    private Image menuBackGr;
    private StartGame startButton;
    private Instruction insButton;
    private HighScores highscButton;
    private Options optsButton;
    private Quit quitButton;
    private Credits creButton;

    public Menu() {
        menuBackGr = Sprite.backgr_menu;
        startButton = new StartGame(xButton,yButton,Sprite.startgame_first);
        insButton = new Instruction(xButton,yButton + 68, Sprite.ins_first);
        highscButton = new HighScores(xButton, yButton + 68*2, Sprite.highsc_first);
        optsButton = new Options(xButton, yButton + 68*3, Sprite.opts_first);
        quitButton = new Quit(xButton, yButton + 68*4, Sprite.quit_first);
        creButton = new Credits(xButton, yButton + 68*5, Sprite.cre_first);
    }

    public void updateMenu() {


        if(startButton.checkActive()) {
            startButton.setImage(Sprite.startgame_last);
        } else {
            startButton.setImage(Sprite.startgame_first);
        }

        if(insButton.checkActive()) {
            insButton.setImage(Sprite.ins_last);
        } else {
            insButton.setImage(Sprite.ins_first);
        }

        if(highscButton.checkActive()) {
            highscButton.setImage(Sprite.highsc_last);
        } else {
            highscButton.setImage(Sprite.highsc_first);
        }

        if(optsButton.checkActive()) {
            optsButton.setImage(Sprite.opts_last);
        } else {
            optsButton.setImage(Sprite.opts_first);
        }

        if(quitButton.checkActive()) {
            quitButton.setImage(Sprite.quit_last);
        } else {
            quitButton.setImage(Sprite.quit_first);
        }

        if(creButton.checkActive()) {
            creButton.setImage(Sprite.cre_last);
        } else {
            creButton.setImage(Sprite.cre_first);
        }

        if(startButton.checkActive() && MouseAction.isClicked) {
            BombermanGame.status = 1;
            return;
        }
//
//        if(insButton.checkActive(xMouse,yMouse)) {
//            insButton.setImage(Sprite.ins_last);
//        } else {
//            insButton.setImage(Sprite.ins_first);
//        }
//
//        if(highscButton.checkActive(xMouse,yMouse)) {
//            highscButton.setImage(Sprite.highsc_last);
//        } else {
//            highscButton.setImage(Sprite.highsc_first);
//        }
//
//        if(optsButton.checkActive(xMouse,yMouse)) {
//            optsButton.setImage(Sprite.opts_last);
//        } else {
//            optsButton.setImage(Sprite.opts_first);
//        }
//
//        if(quitButton.checkActive(xMouse,yMouse)) {
//            quitButton.setImage(Sprite.quit_last);
//        } else {
//            quitButton.setImage(Sprite.quit_first);
//        }
//
//        if(creButton.checkActive(xMouse,yMouse)) {
//            creButton.setImage(Sprite.cre_last);
//        } else {
//            creButton.setImage(Sprite.cre_first);
//        }
    }

    public void renderMenu() {
        BombermanGame.gc.drawImage(menuBackGr,0,0);
        startButton.render();
        insButton.render();
        highscButton.render();
        optsButton.render();
        quitButton.render();
    }
}