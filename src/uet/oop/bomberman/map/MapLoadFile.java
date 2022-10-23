package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*public class MapLoadFile extends LoadLevel {
    public static char[][] map;

    public MapLoadFile(int level) throws FileNotFoundException {
        super(level);
    }
    @Override
    public void inputLevel(int level) throws FileNotFoundException {
        try {
            URL file = uet.oop.bomberman.map.MapLoadFile.class.getResource("/levels/Level" + Integer.toString(level) + ".txt");
            BufferedReader in = new BufferedReader( new InputStreamReader(file.openStream() ) );

            String data = in.readLine();
            level = Integer.parseInt(data.substring(0,1));
            height = Integer.parseInt(data.substring(2,4));
            width = Integer.parseInt(data.substring(5,7));

            map = new char[height][width];
            for (int i = 0; i < height; i++ ) {
                data = in.readLine();

                for (int j = 0; j < width; j++ ) {
                    map[i][j] = data.charAt(j);
                }
            }
            in.close();

        } catch (IOException e) {
            System.out.println("Error loading map level " + level);
        }
    }

    @Override
    public void createMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (map[i][j]) {
                    case 'p':
                        BombermanGame.movingEntities.add(new Bomber(j, i, 1, Sprite.player_right));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '1':
                        BombermanGame.movingEntities.add(new Balloon(j, i, 0, Sprite.balloon_right1));
                        BombermanGame.map[i][j] = new Grass(j, i, Sprite.grass);
                        break;
                    case '2':
                        BombermanGame.movingEntities.add(new Oneal(j, i, 0, Sprite.oneal_right1));
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
}*/