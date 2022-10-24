package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.*;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.Camera;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    public static GraphicsContext gc;
    private Canvas canvas;

    Camera camera = new Camera(0,0,WIDTH*Sprite.SCALED_SIZE,HEIGHT*Sprite.SCALED_SIZE, 2*WIDTH*Sprite.SCALED_SIZE, 2*HEIGHT*Sprite.SCALED_SIZE);
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
                for (int j = 0; j < width*2; j++) {
                    stillEntities.get(i).add(j,new Grass(j, i, Sprite.grass));
                    switch (row.charAt(j)) {
                        case 'p':
                            movingEntities.add(new Bomber(j, i, 3, Sprite.player_right));
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i,2 , Sprite.balloon_right1,false,false,false));
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 3, Sprite.oneal_right1,false,false,false));
                            break;
                        case '3':
                            movingEntities.add(new Kondoria(j,i,1,Sprite.kondoria_right1,true,true,false));
                            break;
                        case '4':
                            movingEntities.add(new Doll(j,i,3,Sprite.doll_right1,false,false,false));
                            break;
                        case '5':
                            movingEntities.add(new Minvo(j,i,4,Sprite.minvo_right1,false,false,false));
                            break;
                        case '6':
                            movingEntities.add(new Ovapi(j,i,2,Sprite.ovapi_right1,false,false,false));
                            break;
                        case '7':
                            movingEntities.add(new Pass(j,i,1,Sprite.pass_right1,true,true,true));
                            break;
                        case '8':
                            movingEntities.add(new Pontan(j,i,2,Sprite.pass_right1,true,true,true));
                            break;
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
        camera.setCenter(Bomber.getxBomber(),Bomber.getyGridBomber());
        //camera.move(1,1);
        camera.update();
        System.out.println("PR" + camera.getStartX() + " "+ camera.getStartY());
        movingEntities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        camera.render(camera.getStartX(), camera.getStartY(),gc);
        for(int i=0;i < HEIGHT; i++) {
            for(int j= camera.getStartX();j < WIDTH*2; j++) {
                if(stillEntities.get(i).get(j) != null) stillEntities.get(i).get(j).render(gc);
            }
        }
        movingEntities.forEach(g->g.render(gc));
        //gc.drawImage(img,300,100);
        Bomber.bombs.forEach(g -> g.render(gc));
//        camera.render(camera.getStartX(), camera.getStartY(),gc);
    }
}
