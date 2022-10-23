package uet.oop.bomberman.Sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class AudioFilePlayer {
    public static String MusicOpenGame = "res/Sound/InGame.wav";
    public static String BombExploded = "res/Sound/BOM_11_L.wav";
    public static String MusicGame1 = "res/Sound/Game1.wav";
    public static String Pause = "res/Sound/pauseoff.wav";
    public static String Continue = "res/Sound/pauseon.wav";
    public static String Win = "res/Sound/win.wav";
    public static String running = "res/Sound/running.wav";
    public static String place_bomb = "res/Sound/placebomb.wav";
    public static String gameOver = "res/Sound/game over.wav";
    public static String eatItem = "res/Sound/eat_Iteam.wav";
    public static final AudioFilePlayer player = new AudioFilePlayer();

    public static boolean isPlaying;

    public static void GameMusic() {
        player.play(MusicOpenGame);
    }

    public static void MusicGame1() {
        player.play(MusicGame1);
    }

    public static void BombExploded() {
        player.play(BombExploded);
    }

    public static void Pause() {
        player.play(Pause);
    }

    public static void Continue() {
        player.play(Continue);
    }

    public static void Win() {
        player.play(Win);
    }

    public static void place_bomb() {
        player.play(place_bomb);
    }

    public static void running() {
        player.play(running);
    }

    public static void gameOver() {
        player.play(gameOver);
    }

    public static void eatItem() {
        player.play(eatItem);
    }

    public void play(String filePath) {
        final File file = new File(filePath);

        try (final AudioInputStream in = getAudioInputStream(file)) {

            final AudioFormat outFormat = getOutFormat(in.getFormat());
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, outFormat);

            try (final SourceDataLine line =
                         (SourceDataLine) AudioSystem.getLine(info)) {
                isPlaying = true;
                if (line != null) {
                    while (isPlaying) {
                        line.open(outFormat);
                        line.start();
                        stream(getAudioInputStream(outFormat, in), line);
                        line.drain();
                        line.stop();
                    }
                }
            }

        } catch (UnsupportedAudioFileException
                 | LineUnavailableException
                 | IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void stop() {
        isPlaying = false;
    }
    private AudioFormat getOutFormat(AudioFormat inFormat) {
        final int ch = inFormat.getChannels();

        final float rate = inFormat.getSampleRate();
        return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line)
            throws IOException {
        final byte[] buffer = new byte[4096];
        for (int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }
    }
}
