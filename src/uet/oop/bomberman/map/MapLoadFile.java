package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.*;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.io.*;


/**
 * ERROR
 */
public class MapLoadFile extends LoadLevel {
    private int width, height;

    private char[][] readMap;

    public MapLoadFile(int level) throws FileNotFoundException {
        super(level);
    }


    @Override
    public void inputLevel(int level) throws FileNotFoundException {
        try {
            File file = new File("res/levels/Level" + Integer.toString(level) + ".txt");
            BufferedReader in = new BufferedReader(new FileReader(file));
            String data = in.readLine();
            level = Integer.parseInt(data.substring(0, 1));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createMap() {
        Grass.grassImg = Sprite.grass.getFxImage();
//            bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
//             bonusItem = new NakamotoSan(Sprite.bonus_item_nakamoto_san);
//            bonusItem = new DezenimanSan(Sprite.bonus_item_dezeniman_san);
//            bonusItem = new Famicom(Sprite.bonus_item_famicom);
//            bonusItem = new GoddessMask(Sprite.bonus_item_goddess_mask);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (readMap[i][j]) {
                    case 'p':
                        BombermanGame.bomber = new Bomber(j, i, 2, Sprite.player_right);
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
                        BombermanGame.movingEntities.add(new Doll(j, i, 2,Sprite.doll_right1,false,false,false));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
                        break;
                    case '4':
                        BombermanGame.movingEntities.add(new Minvo(j, i, 2,Sprite.minvo_right1,false,false,false));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
                        break;
                    case '5':
                        BombermanGame.movingEntities.add(new Kondoria(j, i, 1,Sprite.kondoria_right1,true, true,false));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
                        break;
                    case '6':
                        BombermanGame.movingEntities.add(new Ovapi(j, i, 2,Sprite.ovapi_right1,false,true,false));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
                        break;
                    case '7':
                        BombermanGame.movingEntities.add(new Pass(j, i, 1,Sprite.pass_right1,true, true, true));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
                        break;
                    case '8':
                        BombermanGame.movingEntities.add(new Pontan(j, i, 2,Sprite.pontan_right1,true,false,false));
                        BombermanGame.map[i][j] = new Grass(j,i,Sprite.grass);
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

    public void goNewMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BombermanGame.hiddenEntities[i][j] = null;
            }
        }
        BombermanGame.movingEntities.clear();
        Brick.isAnythingDestroyed = false;
        Enemy.isAnyoneKilled = false;
        this.createMap();
    }
}