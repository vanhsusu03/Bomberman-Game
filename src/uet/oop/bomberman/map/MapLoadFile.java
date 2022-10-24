package uet.oop.bomberman.map;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Kondoria;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * ERROR
 */
public class MapLoadFile extends LoadLevel {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static long score = 0;
    private Entity[][] map = new Entity[HEIGHT][WIDTH];
    private Entity[][] hiddenEntities = new Entity[HEIGHT][WIDTH];
    private List<MovingEntity> movingEntities = new ArrayList<>();
    private static Bomber bomber;
    private static BonusItem bonusItem = null;
    private GraphicsContext gc;
    private Canvas canvas;
    private int level, width, height;

    private char[][] readMap = new char[HEIGHT][WIDTH];

    public MapLoadFile(int level) throws FileNotFoundException {
        super(level);
    }

    public static long getScore() {
        return score;
    }

    public Entity[][] getMap() {
        return map;
    }

    public Entity[][] getHiddenEntities() {
        return hiddenEntities;
    }

    public List<MovingEntity> getMovingEntities() {
        return movingEntities;
    }

    public static Bomber getBomber() {
        return bomber;
    }

    public static BonusItem getBonusItem() {
        return bonusItem;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public char[][] getReadMap() {
        return readMap;
    }

    @Override
    public void inputLevel(int level) throws FileNotFoundException {
        try {
            File file = new File("res/levels/Level" + Integer.toString(level) + ".txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String data = in.readLine();
            level = Integer.parseInt(data.substring(0,1));
            height = Integer.parseInt(data.substring(2,4));
            width = Integer.parseInt(data.substring(5,7));
            readMap = new char[height][width];
            for (int i = 0; i < height; i++ ) {
                data = in.readLine();
                for (int j = 0; j < width; j++ ) {
                    readMap[i][j] = data.charAt(j);
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Error loading map level " + level);
        }
    }

    @Override
    public void createMap() {
            Grass.grassImg = Sprite.grass.getFxImage();
//            bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
            // bonusItem = new NakamotoSan(Sprite.bonus_item_nakamoto_san);
//            bonusItem = new DezenimanSan(Sprite.bonus_item_dezeniman_san);
//            bonusItem = new Famicom(Sprite.bonus_item_famicom);
//            bonusItem = new GoddessMask(Sprite.bonus_item_goddess_mask);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    switch (readMap[i][j]) {
                        case 'p':
                            bomber = new Bomber(j, i, 2, Sprite.player_right);
                            movingEntities.add(bomber);
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i, 1, Sprite.balloon_right1,false,false,false));
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 1, Sprite.oneal_right1,false,false,false));
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case 'b':
                            hiddenEntities[i][j] = new BombItem(j, i, Sprite.powerup_bombs);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'f':
                            hiddenEntities[i][j] = new FlameItem(j, i, Sprite.powerup_flames);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 's':
                            hiddenEntities[i][j] = new SpeedItem(j, i, Sprite.powerup_speed);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'l':
                            hiddenEntities[i][j] = new FlamepassItem(j, i, Sprite.powerup_flamepass);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'o':
                            hiddenEntities[i][j] = new BombpassItem(j, i, Sprite.powerup_bombpass);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'r':
                            hiddenEntities[i][j] = new BrickpassItem(j, i, Sprite.powerup_brickpass);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'd':
                            hiddenEntities[i][j] = new DetonatorItem(j, i, Sprite.powerup_detonator);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'm':
                            hiddenEntities[i][j] = new MysteryItem(j, i, Sprite.powerup_mystery);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case '#':
                            map[i][j] = new Wall(j, i, Sprite.wall);
                            break;
                        case '*':
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        case 'x':
                            hiddenEntities[i][j] = new Portal(j, i, Sprite.portal);
                            map[i][j] = new Brick(j, i, Sprite.brick);
                            break;
                        default:
                            map[i][j] = new Grass(j, i, Sprite.grass);
                    }
                }
            }
    }
}