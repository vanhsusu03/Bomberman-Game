package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.*;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusTarget;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.*;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static long score = 0;
    public static Entity[][] map = new Entity[HEIGHT][WIDTH];
    public static Entity[][] hiddenEntities = new Entity[HEIGHT][WIDTH];
    public static List<MovingEntity> movingEntities = new ArrayList<>();
    public static BonusItem bonusItem = null;
    private GraphicsContext gc;
    private Canvas canvas;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                System.out.println(score);
            }
        };
        timer.start();

        createMap();

        scene.setOnKeyPressed(event -> {
            KeyAction.setKeptKey(String.valueOf(event.getCode()), true);
        });

        scene.setOnKeyReleased(event -> {
            KeyAction.setKeptKey(String.valueOf(event.getCode()), false);
        });

        scene.setOnKeyTyped(event -> {
            KeyAction.setTypedKey(event.getCharacter(), true);
        });
    }

    public void createMap() {
        // Con` thieu' lam` moi' moi. thu' (dua ve` rong~)
        int level, width, height;
        try {
            File file = new File("res/levels/Level1.txt");
            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();
            scanner.nextLine();

            Grass.grassImg = Sprite.grass.getFxImage();
            bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
            for (int i = 0; i < height; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    switch (row.charAt(j)) {
                        case 'p':
                            movingEntities.add(new Bomber(j, i, 1, Sprite.player_right));
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i, 0, Sprite.balloon_right1));
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 0, Sprite.oneal_right1));
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
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Map file was not found.");
            e.printStackTrace();
        }
    }

    public void update() {
        movingEntities.forEach(Entity::update);

        int n = Bomber.bombs.size();
        for (int i = 0; i < Bomber.bombs.size(); i++) {
            Bomber.bombs.get(i).update();
            if (n > Bomber.bombs.size()) {
                i--;
                n = Bomber.bombs.size();
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] instanceof Portal || map[i][j] instanceof Brick) {
                    gc.drawImage(Grass.grassImg,
                            j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE);
                }
                map[i][j].render(gc);
            }
        }

        Bomber.bombs.forEach(bomb -> bomb.render(gc));

        int n = movingEntities.size();
        for (int i = 0; i < movingEntities.size(); i++) {
            movingEntities.get(i).render(gc);
            if (n > movingEntities.size()) {
                i--;
                n = movingEntities.size();
            }
        }
    }
}