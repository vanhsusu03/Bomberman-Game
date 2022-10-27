package uet.oop.bomberman.GameStatus;

import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventAction.KeyAction;
import uet.oop.bomberman.EventAction.MouseAction;
import uet.oop.bomberman.UI.Icon.*;
import uet.oop.bomberman.UI.Panels.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.MapLoadFile;

import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class StartGame {

    private Time timeKeeper;

    private MapLoadFile map;
    private Control control_panel;
    private CompletedLevel completed_level_panel;
    private Lose lose_panel;
    private Paused paused_panel;
    private WinGame winGame_panel;
    private Home ic_home;
    private NextLevel ic_nextLevel;
    private Pause ic_pause;
    private Resume ic_resume;

    private int level;
    private final int maxTime = 210;

    private boolean checkIfHasChanged = false;

    public static Font font;

    public StartGame() throws FileNotFoundException {
        font = Font.loadFont(new FileInputStream("res/font/alarm_clock.ttf"), 30);
    }

    public void createNewGame(int level) {
        try {
            this.level = level;
            map = new MapLoadFile(level);
            map.goNewMap(level);
            createPanels();
            createIcons();
            timeKeeper = new Time();
            checkIfHasChanged = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void updateMoveSound() {
        if (!KeyAction.keys[KeyEvent.VK_LEFT] && !KeyAction.keys[KeyEvent.VK_RIGHT]) {
            BombermanGame.moveLeftRightSound.stop();
        }
        if (!KeyAction.keys[KeyEvent.VK_UP] && !KeyAction.keys[KeyEvent.VK_DOWN]) {
            BombermanGame.moveUpDownSound.stop();
        }
    }

    private void writeScoreToFile() {
        BombermanGame.read3HighestScores();
        for (int i = 0; i < BombermanGame.top3HighestScores.length; i++) {
            if (Long.parseLong(BombermanGame.top3HighestScores[i]) < BombermanGame.score) {
                for (int j = i + 1; j < BombermanGame.top3HighestScores.length; j++) {
                    BombermanGame.top3HighestScores[j] = BombermanGame.top3HighestScores[j - 1];
                }
                BombermanGame.top3HighestScores[i] = String.valueOf(BombermanGame.score);
                break;
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(BombermanGame.SCORES_PATH));

            for (int i = 0; i < BombermanGame.top3HighestScores.length; i++) {
                bufferedWriter.write(BombermanGame.top3HighestScores[i]);
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGamePlay() {
        if(timeKeeper.countSecond() >= maxTime && !checkIfHasChanged) {
            map.updateWhenTimeIsUp();
            checkIfHasChanged = true;
        }
        if (BombermanGame.bomber.isDead()) {
            if (!lose_panel.getRunning()) {
                writeScoreToFile();
            }
            lose_panel.setRunning(true);
        } else if (BombermanGame.numOfEnemies == 0
                && BombermanGame.bomber.isUsedPortal() && level < 5) {
            completed_level_panel.setRunning(true);
        } else if (KeyAction.keys[KeyEvent.VK_ESCAPE] || (ic_pause.checkActive() && MouseAction.isClicked)) {
            paused_panel.setRunning(true);
            timeKeeper.stop();
        } else if (BombermanGame.numOfEnemies == 0
                && BombermanGame.bomber.isUsedPortal() && level == 5) {
            if (!winGame_panel.getRunning()) {
                writeScoreToFile();
            }
            winGame_panel.setRunning(true);
        }
        if (lose_panel.getRunning()) {
            updateLoseGame();
        } else if (completed_level_panel.getRunning()) {
            updateCompletedLevel();
        } else if (paused_panel.getRunning()) {
            updatePausedGame();
        } else if (winGame_panel.getRunning()) {
            updateWinGame();
        } else {
            ic_pause.update();
            BombermanGame.movingEntities.forEach(Entity::update);
            int n = BombermanGame.bomber.getBombs().size();
            for (int i = 0; i < BombermanGame.bomber.getBombs().size(); i++) {
                BombermanGame.bomber.getBombs().get(i).update();
                if (n > BombermanGame.bomber.getBombs().size()) {
                    i--;
                    n = BombermanGame.bomber.getBombs().size();
                }
            }
        }
        updateMoveSound();
    }

    public void renderGamePlay() throws FileNotFoundException {
        if (lose_panel.getRunning()) {
            renderLoseGame();
        } else if (completed_level_panel.getRunning()) {
            renderCompletedLevel();
        } else if (paused_panel.getRunning()) {
            renderPausedGame();
        } else if (winGame_panel.getRunning()) {
            renderWinGame();
        } else {
            BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
            for (int i = 0; i < BombermanGame.HEIGHT; i++) {
                for (int j = 0; j < BombermanGame.WIDTH; j++) {
                    if (BombermanGame.map[i][j] instanceof Portal || BombermanGame.map[i][j] instanceof Brick
                            || BombermanGame.map[i][j] instanceof BonusItem) {
                        BombermanGame.gc.drawImage(Grass.grassImg,
                                j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE);
                    }
                    BombermanGame.map[i][j].render(BombermanGame.gc);
                }
            }
            control_panel.render();
            ic_pause.render();
            if(timeKeeper.countSecond() >= maxTime) {
                BombermanGame.gc.fillText(Integer.toString(0),340,477);
            } else {
                BombermanGame.gc.fillText(Integer.toString(maxTime - timeKeeper.countSecond()), 340, 477);
            }
            BombermanGame.gc.fillText(Long.toString(BombermanGame.score), 570, 477);
            BombermanGame.gc.fillText(Integer.toString(level), 160, 477);
            BombermanGame.gc.fillText(Integer.toString(BombermanGame.bomber.getHeart()), 790, 477);
            BombermanGame.bomber.getBombs().forEach(bomb -> bomb.render(BombermanGame.gc));
            int n = BombermanGame.movingEntities.size();
            for (int i = 0; i < BombermanGame.movingEntities.size(); i++) {
                BombermanGame.movingEntities.get(i).render(BombermanGame.gc);
                if (n > BombermanGame.movingEntities.size()) {
                    i--;
                    n = BombermanGame.movingEntities.size();
                }
            }
        }
    }

    private void createPanels() {
        control_panel = new Control(0, 416, Sprite.control_panel);
        completed_level_panel = new CompletedLevel(320, 160, Sprite.completedlevel_panel);
        lose_panel = new Lose(320, 110, Sprite.lose_panel);
        paused_panel = new Paused(280, 120, Sprite.paused_panel);
        winGame_panel = new WinGame(320, 110, Sprite.wingame_panel);
    }

    private void createIcons() {
        ic_home = new Home(500, 260, Sprite.ic_home_first);
        ic_pause = new Pause(900, 430, Sprite.ic_pause_first);
        ic_resume = new Resume(390, 260, Sprite.ic_resume_first);
        ic_nextLevel = new NextLevel(440, 240, Sprite.ic_nextlevel_first);
    }

    private void updatePausedGame() {
        ic_resume.update();
        ic_home.update();
        if (MouseAction.isClicked && ic_resume.checkActive()) {
            paused_panel.setRunning(false);
            timeKeeper.present();
        }
        if (MouseAction.isClicked && ic_home.checkActive()) {
            paused_panel.setRunning(false);
            BombermanGame.status = 0;
            BombermanGame.menu.setStatus(Menu.MenuStatus.MENU_STATUS);
            BombermanGame.getStartGame().createNewGame(1);
        }
    }

    private void renderPausedGame() {
        paused_panel.render();
        ic_resume.render();
        ic_home.render();
    }

    private void updateCompletedLevel() {
        ic_nextLevel.update();
    }

    private void renderCompletedLevel() throws FileNotFoundException {
        completed_level_panel.render();
        ic_nextLevel.render();
        if (MouseAction.isClicked && ic_nextLevel.checkActive()) {
            completed_level_panel.setRunning(false);
            this.level++;
            BombermanGame.getStartGame().createNewGame(this.level);
        }
    }

    private void updateLoseGame() {
        ic_home.setX(440);
        ic_home.setY(230);
        ic_home.update();
    }

    private void renderLoseGame() {
        lose_panel.render();
        ic_home.render();
        BombermanGame.gc.fillText(Long.toString(BombermanGame.score), 445, 225);
        if (MouseAction.isClicked && ic_home.checkActive()) {
            BombermanGame.status = 0;
            lose_panel.setRunning(false);
            BombermanGame.menu.setStatus(Menu.MenuStatus.MENU_STATUS);
            BombermanGame.getStartGame().createNewGame(1);
        }
    }

    private void updateWinGame() {
        ic_home.setX(440);
        ic_home.setY(230);
        ic_home.update();
    }

    private void renderWinGame() {
        winGame_panel.render();
        ic_home.render();
        BombermanGame.gc.fillText(Long.toString(BombermanGame.score), 445, 225);
        if (MouseAction.isClicked && ic_home.checkActive()) {
            BombermanGame.status = 0;
            winGame_panel.setRunning(false);
            BombermanGame.menu.setStatus(Menu.MenuStatus.MENU_STATUS);
            BombermanGame.getStartGame().createNewGame(1);
        }
    }
}
