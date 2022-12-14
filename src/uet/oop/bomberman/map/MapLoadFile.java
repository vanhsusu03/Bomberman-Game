package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.*;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusTarget;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.DezenimanSan;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.Famicom;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.GoddessMask;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.NakamotoSan;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.SpriteSheet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MapLoadFile extends LoadLevel {
    private int width, height;

    private char[][] readMap;

    /**
     * Load level from file.
     *
     * @param level update level
     */
    public MapLoadFile(int level) throws FileNotFoundException {
        super(level);
    }

    /**
     * Initialize map level.
     *
     * @param level update level
     */
    @Override
    public void inputLevel(int level) {
        try {
            File file = new File("res/levels/Level" + level + ".txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String data = in.readLine();
            this.level = Integer.parseInt(data.substring(0, 1));
            height = Integer.parseInt(data.substring(2, 4));
            width = Integer.parseInt(data.substring(5, 7));
            readMap = new char[height][width];
            for (int i = 0; i < height; i++) {
                data = in.readLine();
                for (int j = 0; j < width; j++) {
                    readMap[i][j] = data.charAt(j);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Create new map.
     */

    private void createNewMap() {
        Sprite.grass = new Sprite(Sprite.DEFAULT_SIZE, 6, 0, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 16, 16);
        Sprite.brick = new Sprite(Sprite.DEFAULT_SIZE, 7, 0, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 16, 16);
        Sprite.wall = new Sprite(Sprite.DEFAULT_SIZE, 5, 0, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 16, 16);
        Sprite.bomb = new Sprite(Sprite.DEFAULT_SIZE, 0, 3, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 15, 15);
        Sprite.bomb_1 = new Sprite(Sprite.DEFAULT_SIZE, 1, 3, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 13, 15);
        Sprite.bomb_2 = new Sprite(Sprite.DEFAULT_SIZE, 2, 3, new SpriteSheet("/textures/classic" + this.level + ".png", 256), 12, 14);
    }

    /**
     * Create map level and entities.
     *
     * @param level update level
     */
    @Override
    public void createMap(int level) {
        Grass.grassImg = Sprite.grass.getFxImage();
        switch (level) {
            case 1:
                BombermanGame.bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
                break;
            case 2:
                BombermanGame.bonusItem = new DezenimanSan(Sprite.bonus_item_dezeniman_san);
                break;
            case 3:
                BombermanGame.bonusItem = new NakamotoSan(Sprite.bonus_item_nakamoto_san);
                break;
            case 4:
                BombermanGame.bonusItem = new Famicom(Sprite.bonus_item_famicom);
                break;
            case 5:
                BombermanGame.bonusItem = new GoddessMask(Sprite.bonus_item_goddess_mask);
                break;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (readMap[i][j]) {
                    case 'p':
                        BombermanGame.bomber = new Bomber(j, i, 1, Sprite.player_right);
                        BombermanGame.movingEntities.add(BombermanGame.bomber);
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '1':
                        BombermanGame.movingEntities.add(new Balloon(j, i, 1, Sprite.balloon_right1, false, false, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '2':
                        BombermanGame.movingEntities.add(new Oneal(j, i, 1, Sprite.oneal_right1, false, false, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '3':
                        BombermanGame.movingEntities.add(new Doll(j, i, 2, Sprite.doll_right1, false, false, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '4':
                        BombermanGame.movingEntities.add(new Minvo(j, i, 1, Sprite.minvo_right1, false, false, true));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '5':
                        BombermanGame.movingEntities.add(new Kondoria(j, i, 1, Sprite.kondoria_right1, false, true, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '6':
                        BombermanGame.movingEntities.add(new Ovapi(j, i, 1, Sprite.ovapi_right1, false, true, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '7':
                        BombermanGame.movingEntities.add(new Pass(j, i, 1, Sprite.pass_right1, false, true, true));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '8':
                        BombermanGame.movingEntities.add(new Pontan(j, i, 1, Sprite.pontan_right1, false, false, false));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case 'b':
                        BombermanGame.hiddenEntities[i][j] = new BombItem(j, i, Sprite.powerup_bombs);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'f':
                        BombermanGame.hiddenEntities[i][j] = new FlameItem(j, i, Sprite.powerup_flames);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 's':
                        BombermanGame.hiddenEntities[i][j] = new SpeedItem(j, i, Sprite.powerup_speed);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'l':
                        BombermanGame.hiddenEntities[i][j] = new FlamepassItem(j, i, Sprite.powerup_flamepass);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'o':
                        BombermanGame.hiddenEntities[i][j] = new BombpassItem(j, i, Sprite.powerup_bombpass);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'r':
                        BombermanGame.hiddenEntities[i][j] = new BrickpassItem(j, i, Sprite.powerup_brickpass);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'd':
                        BombermanGame.hiddenEntities[i][j] = new DetonatorItem(j, i, Sprite.powerup_detonator);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'm':
                        BombermanGame.hiddenEntities[i][j] = new MysteryItem(j, i, Sprite.powerup_mystery);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case '#':
                        BombermanGame.map[i][j] = new Wall(j, i, Sprite.wall);
                        break;
                    case '*':
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    case 'x':
                        BombermanGame.hiddenEntities[i][j] = new Portal(j, i, Sprite.portal);
                        BombermanGame.map[i][j] = new Brick(j, i, Sprite.brick);
                        break;
                    default:
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                }
            }
        }
    }

    /**
     * Initialize new map for each level.
     *
     * @param level update new level
     */
    public void goNewMap(int level) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BombermanGame.hiddenEntities[i][j] = null;
            }
        }
        BombermanGame.movingEntities.clear();
        Brick.isAnythingDestroyed = false;
        Enemy.isAnyoneKilled = false;

        if (level == 1) {
            BombermanGame.score = 0;
        }
        createNewMap();
        createMap(level);
        BombermanGame.bomber.setHeart(3);
        //BombermanGame.bomber.setDead(false);
    }

    /**
     * Update when time is up.
     */
    public void updateWhenTimeIsUp() {
        for (int i = 0; i < BombermanGame.movingEntities.size(); i++) {
            if (BombermanGame.movingEntities.get(i) instanceof Enemy) {
                BombermanGame.movingEntities.set(i, new Pontan(BombermanGame.movingEntities.get(i).getXUnit(), BombermanGame.movingEntities.get(i).getYUnit(), 1, Sprite.pontan_right1, false, true, false));
            }
        }
    }
}