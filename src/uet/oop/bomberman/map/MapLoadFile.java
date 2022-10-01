package uet.oop.bomberman.map;

import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapLoadFile extends LoadLevel {
    public static char[][] map;

    //public List<MovingEntity> BombermanGame.movingEntities = new ArrayList<>();
    //public List<StillEntity> BombermanGame.stillEntities = new ArrayList<>();

    public MapLoadFile(int level) throws FileNotFoundException {
        super(level);
    }
    @Override
    public void inputLevel(int level) throws FileNotFoundException {
        try {
            URL file = MapLoadFile.class.getResource("/levels/Level" + Integer.toString(level) + ".txt");
            BufferedReader in = new BufferedReader( new InputStreamReader(file.openStream() ) );

            String data = in.readLine(); // đọc  hàng đầu tiền
            /**
             * dùng substring để tách  data dòng đầu
             * ví dụ  nó có dạng :1 13 31
             * 1 là level
             *  13 31 là hàng cột
             */
            level = Integer.parseInt(data.substring(0,1));	// tách string đọc  level
            height = Integer.parseInt(data.substring(2,4));// tách string ra đọc hàng
            width = Integer.parseInt(data.substring(5,7));	// tách string đọc cột

            // mình chỉ đọc đủ  số hàng rồi đóng file

            map = new char[height][width];
            for (int i = 0; i < height; i++ ) {
                data = in.readLine();

                for (int j = 0; j < width; j++ ) {
                    map[i][j] = data.charAt(j);
                }
                //this.boardGame.add(data);
            }
            in.close();

        } catch (IOException e) {
            System.out.println("Error loading level " + level);
        }
    }

    @Override
    public void createMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BombermanGame.stillEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                switch (map[i][j]) {
                    case 'p':
                        BombermanGame.movingEntities.add(new Bomber(j, i, 1, Sprite.player_right.getFxImage()));
                        break;
                    case '1':
                        BombermanGame.movingEntities.add(new Balloon(j, i, 0, Sprite.balloon_right1.getFxImage()));
                        break;
                    case '2':
                        BombermanGame.movingEntities.add(new Oneal(j, i, 0, Sprite.oneal_right1.getFxImage()));
                        break;
                    case 'b':
                        break;
                    case 'f':
                        break;
                    case 's':
                        break;
                    case '#':
                        BombermanGame.stillEntities.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        BombermanGame.stillEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        BombermanGame.stillEntities.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        break;
                }
            }
        }
    }
}
