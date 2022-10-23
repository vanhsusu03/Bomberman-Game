package uet.oop.bomberman.Sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundEffect1 {
    static String MusicOpenGame = "res/Sound/InGame.wav";
    static String BombExploded = "res/Sound/BOM_11_L.wav";
    static String MusicGame1 = "res/Sound/Game1.wav";
    static String Pause = "res/Sound/pauseoff.wav";
    static String Continue = "res/Sound/pauseon.wav";
    static String Win = "res/Sound/win.wav";
    static String running = "res/Sound/running.wav";
    static String place_bomb = "res/Sound/place_bomb.wav";
    static String gameOver = "res/Sound/game over.wav";
    static String eatItem = "res/Sound/eat_Item.wav";

    /**
     * Media.
     */
    static Media MusicOpenGame_media = new Media(new File(MusicOpenGame).toURI().toString());
    static Media BombExploded_media = new Media(new File(BombExploded).toURI().toString());
    static Media MusicGame1_media = new Media(new File(MusicGame1).toURI().toString());
    static Media Pause_media = new Media(new File(Pause).toURI().toString());
    static Media Continue_media = new Media(new File(Continue).toURI().toString());
    static Media Win_media = new Media(new File(Win).toURI().toString());
    static Media running_media = new Media(new File(running).toURI().toString());
    static Media place_bomb_media = new Media(new File(place_bomb).toURI().toString());
    static Media gameOver_media = new Media(new File(gameOver).toURI().toString());
    static Media eatItem_media = new Media(new File(eatItem).toURI().toString());

    /**
     * MediaPlayer.
     */
    static MediaPlayer MusicOpenGame_play = new MediaPlayer(MusicOpenGame_media);
    static MediaPlayer BombExploded_play = new MediaPlayer(BombExploded_media);
    static MediaPlayer MusicGame1_play = new MediaPlayer(MusicGame1_media);
    static MediaPlayer Pause_play = new MediaPlayer(Pause_media);
    static MediaPlayer Continue_play = new MediaPlayer(Continue_media);
    static MediaPlayer Win_play = new MediaPlayer(Win_media);
    static MediaPlayer running_play = new MediaPlayer(running_media);
    static MediaPlayer place_bomb_play = new MediaPlayer(place_bomb_media);
    static MediaPlayer gameOver_play = new MediaPlayer(gameOver_media);
    static MediaPlayer eatItem_play = new MediaPlayer(eatItem_media);

    public static void GameMusic() {
        MusicOpenGame_play.setAutoPlay(true);
    }

    public static void BombExploded() {
        BombExploded_play.setAutoPlay(true);
    }

    public static void MusicGame1() {
        MusicGame1_play.setAutoPlay(true);
    }

    public static void Pause() {
        Pause_play.setAutoPlay(true);
    }

    public static void Continue() {
        Continue_play.setAutoPlay(true);
    }

    public static void Win() {
        Win_play.setAutoPlay(true);
    }

    public static void running() {
        running_play.setAutoPlay(true);
    }

    public static void place_bomb() {
        place_bomb_play.setAutoPlay(true);
    }

    public static void gameOver() {
        gameOver_play.setAutoPlay(true);
    }

    public static void eatItem() {
        eatItem_play.setAutoPlay(true);
    }
}
