package uet.oop.bomberman.Sound;

import uet.oop.bomberman.BombermanGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    private static final String DATA_PATH = "res/sound/";
    private static final String AUDIO_EXTENSION = "wav";
    private Clip clip;
    private boolean isPlaying;
    public static boolean isMuted;

    public Sound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(DATA_PATH + fileName + "." + AUDIO_EXTENSION));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int numberOfLoops, boolean isForced) {
        if (isMuted) {
            return;
        }
        if (!isPlaying || isForced
                || clip.getFramePosition() >= clip.getFrameLength()) {
            isPlaying = true;
            clip.setFramePosition(0);
            clip.loop(numberOfLoops);
            clip.start();
        }
    }

    public void stop() {
        clip.stop();
        isPlaying = false;
    }


    public static void stopStageSound() {
        BombermanGame.stageSound.stop();
        BombermanGame.stageSound2.stop();
    }
}