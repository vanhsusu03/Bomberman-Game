package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Doll;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Kondoria;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.image.Image;
import java.util.concurrent.locks.StampedLock;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    public static GraphicsContext gc;
    private Canvas canvas;
    public static List<MovingEntity> movingEntities = new ArrayList<>();

    public static List<List<StillEntity>> stillEntities = new ArrayList<List<StillEntity>>();

    public static int[][] mapParsed = new int[HEIGHT][WIDTH];
    int level, width, height;

    private Image img = (Image) new Image("/textures/paused.png");

    public static List<MovingEntity> getMovingEntities() {
        return movingEntities;
    }

    public static List<List<StillEntity>> getStillEntities() {
        return stillEntities;
    }

    public static void main(String[] args) {
        //System.setProperty("quantum.multithreaded", "false");
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
        stage.setTitle("BombermanGame");
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
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
        try {
            File file = new File("res/levels/Level1.txt");
            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < height; i++) {
                String row = scanner.nextLine();
                stillEntities.add(new ArrayList<>());
                for (int j = 0; j < width; j++) {
                    stillEntities.get(i).add(j,new Grass(j, i, Sprite.grass));
                    switch (row.charAt(j)) {
                        case 'p':
                            movingEntities.add(new Bomber(j, i, 2, Sprite.player_right));
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i, 1, Sprite.balloon_right1));
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 1, Sprite.oneal_right1));
                            break;
                        case '3':
                            movingEntities.add(new Kondoria(j,i,1,Sprite.kondoria_right1));
                        case 'b':
                            stillEntities.get(i).add(j,new BombItem(j, i, Sprite.powerup_bombs));
                            break;
                        case 'f':
                            stillEntities.get(i).add(j,new FlameItem(j, i, Sprite.powerup_flames));
                            break;
                        case 's':
                            stillEntities.get(i).add(j,new SpeedItem(j, i, Sprite.powerup_speed));
                            break;
                        case '#':
                            stillEntities.get(i).add(j, new Wall(j, i, Sprite.wall));
                            break;
                        case '*':
                            stillEntities.get(i).add(j,new Brick(j, i, Sprite.brick));
                            break;
                        case 'x':
                            stillEntities.get(i).add(j,new Portal(j, i, Sprite.portal));
                            break;
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
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i=0;i < HEIGHT; i++) {
            for(int j=0;j < WIDTH; j++) {
                if(stillEntities.get(i).get(j) != null) stillEntities.get(i).get(j).render(gc);
            }
        }
        movingEntities.forEach(g->g.render(gc));
        gc.drawImage(img,300,100);
        Bomber.bombs.forEach(g -> g.render(gc));
    }
}
