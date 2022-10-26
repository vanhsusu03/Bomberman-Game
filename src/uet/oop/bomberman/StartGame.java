package uet.oop.bomberman;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartGame {

    private Time timeKeeper;

    private int DEFAULT_FONT_SIZE = 25;
    private MapLoadFile map;
    private Control control_panel;
    private CompletedLevel completed_level_panel;
    private Lose lose_panel;
    private Paused paused_panel;
    private WinGame winGame_panel;
    private Back ic_back;
    private Home ic_home;
    private MuteSound ic_muteSound;
    private NextLevel ic_nextLevel;
    private OnSound ic_onSound;
    private Pause ic_pause;
    private Resume ic_resume;

    private int level;
    private int maxTime = 210;

    private Font font = Font.loadFont(new FileInputStream("res/font/alarm_clock.ttf"), 30);

    public StartGame() throws FileNotFoundException {
    }

    public void createNewGame(int level) throws FileNotFoundException {
        this.level = level;
        map = new MapLoadFile(level);
        map.goNewMap();
        createPanels();
        createIcons();
        BombermanGame.gc.setFont(font);
        BombermanGame.gc.setFill(Color.INDIGO);
        timeKeeper = new Time();
    }

    public void updateGamePlay() throws FileNotFoundException {
        if (BombermanGame.bomber.isDead()) {
            lose_panel.setRunning(true);
        } else if (BombermanGame.numOfEnemies == 0 && BombermanGame.bomber.usePortal() && level < 5) {
            completed_level_panel.setRunning(true);
        } else if (KeyAction.keys[KeyEvent.VK_ESCAPE] || (ic_pause.checkActive() && MouseAction.isClicked)) {
            paused_panel.setRunning(true);
        }
        if (lose_panel.getRunning()) {
            updateLoseGame();
        } else if (completed_level_panel.getRunning()) {
            updateCompletedLevel();
        } else if (paused_panel.getRunning()) {
            updatePausedGame();
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
    }

    public void renderGamePlay() throws FileNotFoundException {
        if (lose_panel.getRunning()) {
            renderLoseGame();
        } else if (completed_level_panel.getRunning()) {
            renderCompletedLevel();
        } else if (paused_panel.getRunning()) {
            renderPausedGame();
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
            BombermanGame.gc.fillText(Integer.toString(maxTime - timeKeeper.countSecond()),365,475);
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
        winGame_panel = new WinGame(320, 160, Sprite.wingame_panel);
    }

    private void createIcons() {
        ic_home = new Home(500, 260, Sprite.ic_home_first);
        ic_pause = new Pause(900, 430, Sprite.ic_pause_first);
        ic_resume = new Resume(390, 260, Sprite.ic_resume_first);
        ic_muteSound = new MuteSound(0, 0, Sprite.ic_mutesound_first);
        ic_onSound = new OnSound(0, 0, Sprite.ic_onsound_first);
        ic_nextLevel = new NextLevel(440, 240, Sprite.ic_nextlevel_first);
    }

    private void updatePausedGame() throws FileNotFoundException {
        ic_resume.update();
        ic_home.update();
        if (MouseAction.isClicked && ic_resume.checkActive()) {
            //|| KeyAction.keys[KeyEvent.VK_ESCAPE]) {
            paused_panel.setRunning(false);
        }
        if (MouseAction.isClicked && ic_home.checkActive()) {
            paused_panel.setRunning(false);
            BombermanGame.status = 0;
            BombermanGame.getStartGame().createNewGame(1);
            return;
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
            return;
        }
    }

    private void updateLoseGame() {
        ic_home.setX(440);
        ic_home.setY(230);
        ic_home.update();
    }

    private void renderLoseGame() throws FileNotFoundException {
        lose_panel.render();
        ic_home.render();
        if (MouseAction.isClicked && ic_home.checkActive()) {
            BombermanGame.status = 0;
            lose_panel.setRunning(false);
            BombermanGame.getStartGame().createNewGame(1);
            return;
        }
    }

    private void updateWinGame() {
        ic_home.setX(440);
        ic_home.setY(230);
        ic_home.update();
    }

    private void renderWinGame() throws FileNotFoundException {
        winGame_panel.render();
        ic_home.render();
        if (MouseAction.isClicked && ic_home.checkActive()) {
            BombermanGame.status = 0;
            winGame_panel.setRunning(false);
            BombermanGame.getStartGame().createNewGame(1);
            return;
        }
    }
}
