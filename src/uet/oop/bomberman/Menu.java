package uet.oop.bomberman;

import javafx.scene.image.Image;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.UI.Button.*;
import uet.oop.bomberman.UI.Button.StartGame;
import uet.oop.bomberman.UI.Icon.Back;
import uet.oop.bomberman.UI.Icon.MuteSound;
import uet.oop.bomberman.UI.Icon.OnSound;
import uet.oop.bomberman.UI.Panels.HighSc;
import uet.oop.bomberman.graphics.Sprite;

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

    private Back backIcon;

    private uet.oop.bomberman.UI.Panels.Options opts_panel;
    private uet.oop.bomberman.UI.Panels.Instruction ins_panel;
    private HighSc highsc_panel;
    private uet.oop.bomberman.UI.Panels.Credits cre_panel;

    private MuteSound muteSoundIcon;
    private OnSound onSoundIcon;

    public Menu() {
        menuBackGr = Sprite.backgr_menu;
        startButton = new StartGame(xButton, yButton, Sprite.startgame_first);
        insButton = new Instruction(xButton, yButton + 68, Sprite.ins_first);
        highscButton = new HighScores(xButton, yButton + 68 * 2, Sprite.highsc_first);
        optsButton = new Options(xButton, yButton + 68 * 3, Sprite.opts_first);
        quitButton = new Quit(xButton, yButton + 68 * 4, Sprite.quit_first);
        creButton = new Credits(xButton, yButton + 68 * 5, Sprite.cre_first);
        ins_panel = new uet.oop.bomberman.UI.Panels.Instruction(0, 0, Sprite.ins_panel);
        opts_panel = new uet.oop.bomberman.UI.Panels.Options(240, 120, Sprite.opts_panel);
        highsc_panel = new HighSc(0,0,Sprite.ins_panel);
        cre_panel = new uet.oop.bomberman.UI.Panels.Credits(0,0,Sprite.ins_panel);
        backIcon = new Back(0,0, Sprite.ic_back_first);
        muteSoundIcon = new MuteSound(530, 290, Sprite.ic_mutesound_first);
        onSoundIcon = new OnSound(600, 290, Sprite.ic_onsound_first);
    }

    public void updateMenu() {
        if (startButton.checkActive() && MouseAction.isClicked) {
            BombermanGame.status = 1;
            return;
        } else if (insButton.checkActive() && MouseAction.isClicked) {
            ins_panel.setRunning(true);
        } else if (highscButton.checkActive() && MouseAction.isClicked) {
            highsc_panel.setRunning(true);
        } else if (optsButton.checkActive() && MouseAction.isClicked) {
            opts_panel.setRunning(true);
        } else if (creButton.checkActive() && MouseAction.isClicked) {
            cre_panel.setRunning(true);
        }
        if (ins_panel.getRunning()) {
            updateInstruction();
        } else if (highsc_panel.getRunning()) {
            updateHighSc();
        } else if (opts_panel.getRunning()) {
            updateOptions();
        } else if (cre_panel.getRunning()) {
            updateCredits();
        } else {
            if (startButton.checkActive()) {
                startButton.setImage(Sprite.startgame_last);
            } else {
                startButton.setImage(Sprite.startgame_first);
            }

            if (insButton.checkActive()) {
                insButton.setImage(Sprite.ins_last);
            } else {
                insButton.setImage(Sprite.ins_first);
            }

            if (highscButton.checkActive()) {
                highscButton.setImage(Sprite.highsc_last);
            } else {
                highscButton.setImage(Sprite.highsc_first);
            }

            if (optsButton.checkActive()) {
                optsButton.setImage(Sprite.opts_last);
            } else {
                optsButton.setImage(Sprite.opts_first);
            }

            if (quitButton.checkActive()) {
                quitButton.setImage(Sprite.quit_last);
            } else {
                quitButton.setImage(Sprite.quit_first);
            }

            if (creButton.checkActive()) {
                creButton.setImage(Sprite.cre_last);
            } else {
                creButton.setImage(Sprite.cre_first);
            }
        }
    }

    public void renderMenu() {
        if (ins_panel.getRunning()) {
            renderInstruction();
        } else if (highsc_panel.getRunning()) {
            renderHighSc();
        } else if (opts_panel.getRunning()) {
            renderOptions();
        } else if (cre_panel.getRunning()) {
            renderCredits();
        } else {
            BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
            BombermanGame.gc.drawImage(menuBackGr, 0, 0);
            startButton.render();
            insButton.render();
            highscButton.render();
            optsButton.render();
            quitButton.render();
        }
    }


    private void updateInstruction() {
        backIcon.setX(30);
        backIcon.setY(10);
        backIcon.update();
        if(MouseAction.isClicked && backIcon.checkActive()) {
            ins_panel.setRunning(false);
        }

    }

    private void renderInstruction() {
        ins_panel.render();
        backIcon.render();
    }

    private void updateOptions() {
        backIcon.setX(260);
        backIcon.setY(150);
        backIcon.update();
        muteSoundIcon.update();
        onSoundIcon.update();
        if(backIcon.checkActive() && MouseAction.isClicked) {
            opts_panel.setRunning(false);
        }
        if(MouseAction.isClicked && muteSoundIcon.checkActive()) {
//            ins_panel.setRunning(false);
            Sound.isMuted = true;
            BombermanGame.menuStartSound.stop();
        }
        if(MouseAction.isClicked && onSoundIcon.checkActive()) {
//            ins_panel.setRunning(false);
            Sound.isMuted = false;
            BombermanGame.menuStartSound.play(-1, false);
        }
    }

    private void renderOptions() {
        opts_panel.render();
        backIcon.render();
        muteSoundIcon.render();
        onSoundIcon.render();
    }

    private void updateHighSc() {

    }

    private void renderHighSc() {

    }

    private void updateCredits() {

    }

    private void renderCredits() {

    }
}