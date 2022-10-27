package uet.oop.bomberman.UI.Panels;


import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;

public abstract class Panel {

    protected int x;
    protected int y;
    protected boolean isRunning = false;
    protected Image img;

    public Panel(int x, int y, Image img) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public void setRunning(boolean running) {
        isRunning = running;

        if (this instanceof CompletedLevel) {
            if (isRunning) {
                BombermanGame.levelCompleteSound.play(-1, false);
                Sound.stopStageSound();
            } else {
                BombermanGame.levelCompleteSound.stop();
            }
        } else if (this instanceof Lose) {
            if (isRunning) {
                BombermanGame.gameOverSound.play(-1, false);
                Sound.stopStageSound();
            } else {
                BombermanGame.gameOverSound.stop();

            }
        } else if (this instanceof WinGame) {
            if (isRunning) {
                BombermanGame.winGameSound.play(-1, false);
                Sound.stopStageSound();
            } else {
                BombermanGame.winGameSound.stop();
            }
        }
    }

    public boolean getRunning() {
        return this.isRunning;
    }

    public void render() {
        BombermanGame.gc.drawImage(img, x, y);
    }

}
