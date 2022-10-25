package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.GoddessMask;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.*;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapLoadFile {

    public static void createMap(int level) {
        int width, height;
        try {
            File file = new File("res/levels/Level" + level + ".txt");
            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();
            scanner.nextLine();

            Grass.grassImg = Sprite.grass.getFxImage();
//            bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
//            bonusItem = new NakamotoSan(Sprite.bonus_item_nakamoto_san);
//            bonusItem = new DezenimanSan(Sprite.bonus_item_dezeniman_san);
//            bonusItem = new Famicom(Sprite.bonus_item_famicom);
//              bonusItem = new GoddessMask(Sprite.bonus_item_goddess_mask);
            for (int i = 0; i < height; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    switch (row.charAt(j)) {
                        case 'p':
                            BombermanGame.movingEntities.add(new Bomber(j, i, 1, Sprite.player_right));
                            BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '1':
                            BombermanGame.movingEntities.add(new Balloon(j, i, 0, Sprite.balloon_right1, false, false, false));
                            BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '2':
                            BombermanGame.movingEntities.add(new Oneal(j, i, 0, Sprite.oneal_right1, false, false, false));
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
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Map file was not found.");
            e.printStackTrace();
        }
    }
}
