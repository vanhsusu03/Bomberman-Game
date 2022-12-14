package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.io.File;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {
    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    public final int SIZE;
    private int _x, _y;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private SpriteSheet _sheet;
    public static Image backgr_menu = new Image(new File("res/backgrmenu/bomberman.png").toURI().toString());
    public static Image opts_panel = new Image(new File("res/panel/options/options.png").toURI().toString());
    public static Image highsc_panel = new Image(new File("res/highsc/highsc_panel.png").toURI().toString());
    public static Image ins_panel = new Image(new File("res/instruction/ins.png").toURI().toString());
    public static Image control_panel = new Image(new File("res/control_panel/control_panel.png").toURI().toString());
    public static Image paused_panel = new Image(new File("res/panel/pause/paused.png").toURI().toString());
    public static Image completedlevel_panel = new Image(new File("res/panel/winlevel/levelwin.png").toURI().toString());
    public static Image wingame_panel = new Image(new File("res/panel/wingame/wingame.png").toURI().toString());
    public static Image lose_panel = new Image(new File("res/panel/lose/lose.png").toURI().toString());

    //BUTTON
    public static Image cre_first = new Image(new File("res/cre/cre_first.png").toURI().toString());
    public static Image cre_last = new Image(new File("res/cre/cre_last.png").toURI().toString());
    public static Image highsc_first = new Image(new File("res/highsc/highsc_first.png").toURI().toString());
    public static Image highsc_last = new Image(new File("res/highsc/highsc_last.png").toURI().toString());
    public static Image ins_first = new Image(new File("res/instruction/ins_first.png").toURI().toString());
    public static Image ins_last = new Image(new File("res/instruction/ins_last.png").toURI().toString());
    public static Image opts_first = new Image(new File("res/opts/opt_first.png").toURI().toString());
    public static Image opts_last = new Image(new File("res/opts/opt_last.png").toURI().toString());
    public static Image startgame_first = new Image(new File("res/startgame/startgame_first.png").toURI().toString());
    public static Image startgame_last = new Image(new File("res/startgame/startgame_last.png").toURI().toString());
    public static Image quit_first = new Image(new File("res/quit/quit_first.png").toURI().toString());
    public static Image quit_last = new Image(new File("res/quit/quit_last.png").toURI().toString());

    //ICON
    public static Image ic_back_first = new Image(new File("res/icon/back/back_first.png").toURI().toString());
    public static Image ic_back_last = new Image(new File("res/icon/back/back_last.png").toURI().toString());
    public static Image ic_home_first = new Image(new File("res/icon/home/home_first.png").toURI().toString());
    public static Image ic_home_last = new Image(new File("res/icon/home/home_last.png").toURI().toString());
    public static Image ic_mutesound_first = new Image(new File("res/icon/mute sound/mutesound_first.png").toURI().toString());
    public static Image ic_mutesound_last = new Image(new File("res/icon/mute sound/mutesound_last.png").toURI().toString());
    public static Image ic_nextlevel_first = new Image(new File("res/icon/nextlevel/nextlevel_first.png").toURI().toString());
    public static Image ic_nextlevel_last = new Image(new File("res/icon/nextlevel/nextlevel_last.png").toURI().toString());
    public static Image ic_onsound_first = new Image(new File("res/icon/onsound/onsound_first.png").toURI().toString());
    public static Image ic_onsound_last = new Image(new File("res/icon/onsound/onsound_last.png").toURI().toString());
    public static Image ic_pause_first = new Image(new File("res/icon/pause/pause_first.png").toURI().toString());
    public static Image ic_pause_last = new Image(new File("res/icon/pause/pause_last.png").toURI().toString());
    public static Image ic_resume_first = new Image(new File("res/icon/resume/resume_first.png").toURI().toString());
    public static Image ic_resume_last = new Image(new File("res/icon/resume/resume_last.png").toURI().toString());


    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite player_up = new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_down = new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_left = new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15);
    public static Sprite player_right = new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 15);

    public static Sprite player_up_1 = new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 10, 15);
    public static Sprite player_up_2 = new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 10, 15);

    public static Sprite player_down_1 = new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 10, 15);
    public static Sprite player_down_2 = new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 10, 15);

    public static Sprite player_left_1 = new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 10, 15);
    public static Sprite player_left_2 = new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 10, 15);

    public static Sprite player_right_1 = new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 10, 15);
    public static Sprite player_right_2 = new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 10, 15);

    public static Sprite player_dead1 = new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16);
    public static Sprite player_dead2 = new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15);
    public static Sprite player_dead3 = new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Character
    |--------------------------------------------------------------------------
     */
    //BALLON
    public static Sprite balloon_left1 = new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloon_left2 = new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloon_left3 = new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloon_right1 = new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite balloon_right2 = new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite balloon_right3 = new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite balloon_dead = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

    //ONEAL
    public static Sprite oneal_left1 = new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left2 = new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_left3 = new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_right1 = new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right2 = new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite oneal_right3 = new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite oneal_dead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

    //Doll
    public static Sprite doll_left1 = new Sprite(DEFAULT_SIZE, 13, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left2 = new Sprite(DEFAULT_SIZE, 13, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_left3 = new Sprite(DEFAULT_SIZE, 13, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_right1 = new Sprite(DEFAULT_SIZE, 14, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right2 = new Sprite(DEFAULT_SIZE, 14, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite doll_right3 = new Sprite(DEFAULT_SIZE, 14, 2, SpriteSheet.tiles, 16, 16);

    public static Sprite doll_dead = new Sprite(DEFAULT_SIZE, 13, 3, SpriteSheet.tiles, 16, 16);

    //Minvo
    public static Sprite minvo_left1 = new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left2 = new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_left3 = new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_right1 = new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right2 = new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite minvo_right3 = new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

    //Kondoria
    public static Sprite kondoria_left1 = new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left2 = new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_left3 = new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_right1 = new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right2 = new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite kondoria_right3 = new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

    //OVAPI
    public static Sprite ovapi_right1 = new Sprite(DEFAULT_SIZE, 7, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite ovapi_right2 = new Sprite(DEFAULT_SIZE, 7, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite ovapi_right3 = new Sprite(DEFAULT_SIZE, 7, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite ovapi_left1 = new Sprite(DEFAULT_SIZE, 6, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite ovapi_left2 = new Sprite(DEFAULT_SIZE, 6, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite ovapi_left3 = new Sprite(DEFAULT_SIZE, 6, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite ovapi_dead = new Sprite(DEFAULT_SIZE, 6, 8, SpriteSheet.tiles, 16, 16);

    //PASS
    public static Sprite pass_right1 = new Sprite(DEFAULT_SIZE, 5, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_right2 = new Sprite(DEFAULT_SIZE, 5, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_right3 = new Sprite(DEFAULT_SIZE, 5, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite pass_left1 = new Sprite(DEFAULT_SIZE, 4, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_left2 = new Sprite(DEFAULT_SIZE, 4, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_left3 = new Sprite(DEFAULT_SIZE, 4, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite pass_dead = new Sprite(DEFAULT_SIZE, 4, 8, SpriteSheet.tiles, 16, 16);

    //PONTAN
    public static Sprite pontan_right1 = new Sprite(DEFAULT_SIZE, 13, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_right2 = new Sprite(DEFAULT_SIZE, 13, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_right3 = new Sprite(DEFAULT_SIZE, 13, 7, SpriteSheet.tiles, 16, 16);

    public static Sprite pontan_left1 = new Sprite(DEFAULT_SIZE, 12, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_left2 = new Sprite(DEFAULT_SIZE, 12, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_left3 = new Sprite(DEFAULT_SIZE, 12, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite pontan_dead = new Sprite(DEFAULT_SIZE, 12, 8, SpriteSheet.tiles, 16, 16);

    //ALL
    public static Sprite mob_dead1 = new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead2 = new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite mob_dead3 = new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb = new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15);
    public static Sprite bomb_1 = new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15);
    public static Sprite bomb_2 = new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14);

    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Sprite bomb_exploded = new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded1 = new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite bomb_exploded2 = new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical = new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical1 = new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical2 = new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal = new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal1 = new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal2 = new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_left_last = new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last1 = new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_left_last2 = new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_horizontal_right_last = new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last1 = new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_horizontal_right_last2 = new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_top_last = new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last1 = new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_top_last2 = new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16);

    public static Sprite explosion_vertical_down_last = new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last1 = new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16);
    public static Sprite explosion_vertical_down_last2 = new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Sprite brick_exploded = new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded1 = new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16);
    public static Sprite brick_exploded2 = new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_brickpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);
    public static Sprite powerup_mystery = new Sprite(DEFAULT_SIZE, 7, 10, SpriteSheet.tiles, 16, 16);

    /*
    |--------------------------------------------------------------------------
    | Bonus Items
    |--------------------------------------------------------------------------
     */
    public static Sprite bonus_item_bonus_target = new Sprite(DEFAULT_SIZE, 0, 11, SpriteSheet.tiles, 16, 16);
    public static Sprite bonus_item_nakamoto_san = new Sprite(DEFAULT_SIZE, 2, 11, SpriteSheet.tiles, 16, 16);
    public static Sprite bonus_item_dezeniman_san = new Sprite(DEFAULT_SIZE, 5, 11, SpriteSheet.tiles, 16, 16);
    public static Sprite bonus_item_famicom = new Sprite(DEFAULT_SIZE, 3, 11, SpriteSheet.tiles, 16, 16);
    public static Sprite bonus_item_goddess_mask = new Sprite(DEFAULT_SIZE, 1, 11, SpriteSheet.tiles, 16, 16);

    public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Sprite(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public int get_realWidth() {
        return _realWidth * SCALED_SIZE / DEFAULT_SIZE;
    }

    public int get_realHeight() {
        return _realHeight * SCALED_SIZE / DEFAULT_SIZE;
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, Sprite x3, int animate, int time) {
        int calc = animate % time;
        int diff = time / 4;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        if (calc < diff * 3) {
            return x2;
        }

        return x3;
    }

    public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        Image input = new ImageView(wr).getImage();
        return resample(input, SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }
}