/*package uet.oop.bomberman.Menu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadImage {
    public static final String back_ground = "/backgrmenu/bomberman.png";
    public static final String start_game = "startgame/startgame_first.png";
    public static final String option = "opts/opt_first.png";
    public static final String instruction = "instruction/ins_first.png";
    public static final String credit = "cre/cre_first.png";
    public static final String quit = "quit/quit_first.png";


    public static BufferedImage GetSprite(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadImage.class.getResourceAsStream(fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}
*/