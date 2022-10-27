package uet.oop.bomberman.GameStatus;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventAction.MouseAction;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.UI.Button.StartGame;
import uet.oop.bomberman.UI.Button.HighScores;
import uet.oop.bomberman.UI.Button.Instruction;
import uet.oop.bomberman.UI.Button.Options;
import uet.oop.bomberman.UI.Button.Credits;
import uet.oop.bomberman.UI.Button.Quit;
import uet.oop.bomberman.UI.Icon.Back;
import uet.oop.bomberman.UI.Icon.MuteSound;
import uet.oop.bomberman.UI.Icon.OnSound;
import uet.oop.bomberman.UI.Panels.HighSc;
import uet.oop.bomberman.graphics.Sprite;
import javafx.application.Platform;

import java.io.FileInputStream;

public class Menu {

    private int xButton = 600;
    private int yButton = 135;

    private MenuStatus status;
    private Image menuBackGr;
    private StartGame startButton;
    private Instruction insButton;
    private HighScores highScButton;
    private Options optsButton;
    private Quit quitButton;
    private Credits creButton;

    private Back backIconOpts, backIconIns;
    private Back backIconHighSc;

    private uet.oop.bomberman.UI.Panels.Options opts_panel;
    private uet.oop.bomberman.UI.Panels.Instruction ins_panel;
    private HighSc highSc_panel;
    private uet.oop.bomberman.UI.Panels.Credits cre_panel;

    private MuteSound muteSoundIcon;
    private OnSound onSoundIcon;
    public static Font font;

    /**
     * Init menu.
     */
    public Menu() {
        menuBackGr = Sprite.backgr_menu;
        startButton = new StartGame(xButton, yButton, Sprite.startgame_first);
        insButton = new Instruction(xButton, yButton + 68, Sprite.ins_first);
        highScButton = new HighScores(xButton, yButton + 68 * 2, Sprite.highsc_first);
        optsButton = new Options(xButton, yButton + 68 * 3, Sprite.opts_first);
        quitButton = new Quit(xButton, yButton + 68 * 4, Sprite.quit_first);
        creButton = new Credits(xButton, yButton + 68 * 5, Sprite.cre_first);
        ins_panel = new uet.oop.bomberman.UI.Panels.Instruction(0, 0, Sprite.ins_panel);
        opts_panel = new uet.oop.bomberman.UI.Panels.Options(240, 120, Sprite.opts_panel);
        highSc_panel = new HighSc(0, 0, Sprite.highsc_panel);
        cre_panel = new uet.oop.bomberman.UI.Panels.Credits(0, 0, Sprite.ins_panel);
        backIconOpts = new Back(0, 0, Sprite.ic_back_first);
        backIconIns = new Back(0, 0, Sprite.ic_back_first);
        backIconHighSc = new Back(0, 0, Sprite.ic_back_first);
        muteSoundIcon = new MuteSound(530, 290, Sprite.ic_mutesound_first);
        onSoundIcon = new OnSound(600, 290, Sprite.ic_onsound_first);
        status = MenuStatus.MENU_STATUS;
        try {
            font = Font.loadFont(new FileInputStream("res/font/score.ttf"), 50);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStatus(MenuStatus status) {
        this.status = status;
    }

    /**
     * Update menu in some status.
     */
    public void updateMenu() {
        if (status == MenuStatus.MENU_STATUS) {
            if (startButton.checkActive() && MouseAction.isClicked) {
                status = MenuStatus.START_GAME;
                BombermanGame.startGame.createNewGame(1);
                BombermanGame.status = 1;
                return;
            } else if (insButton.checkActive() && MouseAction.isClicked) {
                status = MenuStatus.INSTRUCTION;
                ins_panel.setRunning(true);
            } else if (highScButton.checkActive() && MouseAction.isClicked) {
                status = MenuStatus.HIGH_SCORE;
                highSc_panel.setRunning(true);
                BombermanGame.read3HighestScores();
            } else if (optsButton.checkActive() && MouseAction.isClicked) {
                status = MenuStatus.OPTIONS;
                opts_panel.setRunning(true);
            } else if (creButton.checkActive() && MouseAction.isClicked) {
                status = MenuStatus.QUIT;
                cre_panel.setRunning(true);
            } else if (quitButton.checkActive() && MouseAction.isClicked) {
                Platform.exit();
            }
        }
        if (ins_panel.getRunning()) {
            updateInstruction();
        } else if (highSc_panel.getRunning()) {
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

            if (highScButton.checkActive()) {
                highScButton.setImage(Sprite.highsc_last);
            } else {
                highScButton.setImage(Sprite.highsc_first);
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

    /**
     * Render menu.
     */
    public void renderMenu() {
        if (ins_panel.getRunning()) {
            renderInstruction();
        } else if (highSc_panel.getRunning()) {
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
            highScButton.render();
            optsButton.render();
            quitButton.render();
        }
    }

    private void updateInstruction() {
        backIconIns.setX(30);
        backIconIns.setY(10);
        backIconIns.update();
        if (MouseAction.isClicked && backIconIns.checkActive()) {
            ins_panel.setRunning(false);
            status = MenuStatus.MENU_STATUS;
        }

    }

    private void renderInstruction() {
        ins_panel.render();
        backIconIns.render();
    }

    private void updateOptions() {
        backIconOpts.setX(260);
        backIconOpts.setY(150);
        backIconOpts.update();
        muteSoundIcon.update();
        onSoundIcon.update();

        if (backIconOpts.checkActive() && MouseAction.isClicked) {

            opts_panel.setRunning(false);
            status = MenuStatus.MENU_STATUS;
        }
        if (muteSoundIcon.checkActive() && MouseAction.isClicked) {
            Sound.isMuted = true;
            BombermanGame.menuStartSound.stop();
        }
        if (onSoundIcon.checkActive() && MouseAction.isClicked) {
            Sound.isMuted = false;
            BombermanGame.menuStartSound.play(-1, false);
        }
    }

    private void renderOptions() {
        opts_panel.render();
        backIconOpts.render();
        muteSoundIcon.render();
        onSoundIcon.render();
    }

    private void updateHighSc() {
        backIconHighSc.setX(30);
        backIconHighSc.setY(10);
        backIconHighSc.update();
        if (MouseAction.isClicked && backIconHighSc.checkActive()) {
            highSc_panel.setRunning(false);
            status = MenuStatus.MENU_STATUS;
        }
    }

    private void renderHighSc() {
        highSc_panel.render();
        backIconHighSc.render();
        for (int i = 0; i < BombermanGame.top3HighestScores.length; i++) {
            BombermanGame.gc.fillText(BombermanGame.top3HighestScores[i], 400, 231 + 87 * i);
        }
    }

    private void updateCredits() {

    }

    private void renderCredits() {

    }

    /**
     * Statuses of Menu.
     */
    public enum MenuStatus {
        MENU_STATUS, START_GAME, INSTRUCTION, HIGH_SCORE, OPTIONS, QUIT
    }
}