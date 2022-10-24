package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import uet.oop.bomberman.Menu2.*;
import uet.oop.bomberman.Sound.AudioFilePlayer;
import uet.oop.bomberman.Sound.SoundEffect1;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.*;
import uet.oop.bomberman.entities.StillEntity.Item.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 16;
    private GraphicsContext gc;
    private Canvas canvas;
    Menu menu = new Menu();
    MenuButton menuButton = new MenuButton();

    Options options = new Options();

    Instruction ins = new Instruction();

    HighScore highScore = new HighScore();

    public static List<MovingEntity> movingEntities = new ArrayList<>();
    public static List<StillEntity> stillEntities = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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


        //SoundEffect1.MusicGame1();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        System.out.println(GameState.state);
        switch (GameState.state) {
            case MENU:
                scene.setOnMouseMoved(mouseEvent -> {
                    System.out.println(mouseEvent.getX() + " " + mouseEvent.getY());
                    if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 120 && mouseEvent.getY() <= 188) {
                        menuButton.index[0] = 1;
                    } else {
                        menuButton.index[0] = 0;
                    }
                    if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 190 && mouseEvent.getY() <= 258) {
                        menuButton.index[1] = 1;
                    } else {
                        menuButton.index[1] = 0;
                    }
                    if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 260 && mouseEvent.getY() <= 328) {
                        menuButton.index[2] = 1;
                    } else {
                        menuButton.index[2] = 0;
                    }
                    if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 330 && mouseEvent.getY() <= 398) {
                        menuButton.index[3] = 1;
                    } else {
                        menuButton.index[3] = 0;
                    }
                    if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 400 && mouseEvent.getY() <= 468) {
                        menuButton.index[4] = 1;
                    } else {
                        menuButton.index[4] = 0;
                    }
                });

                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 120 && mouseEvent.getY() <= 188) {
                            GameState.state = GameState.PLAYING;
                            System.out.println(GameState.state);
                        } else if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 190 && mouseEvent.getY() <= 258) {
                            GameState.state = GameState.OPTION;
                            System.out.println(GameState.state);
                        }
                        if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 260 && mouseEvent.getY() <= 328) {
                            GameState.state = GameState.INSTRUCTION;
                            System.out.println(GameState.state);
                        }
                        if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 330 && mouseEvent.getY() <= 398) {
                            GameState.state = GameState.HIGHSCORE;
                            System.out.println(GameState.state);
                        }
                        if (mouseEvent.getX() >= 640 && mouseEvent.getX() <= 938 && mouseEvent.getY() >= 400 && mouseEvent.getY() <= 468) {
                            GameState.state = GameState.QUIT;
                            System.out.println(GameState.state);
                            Platform.exit();
                        }
                    }
                });
                break;
            case PLAYING:
                break;
            case OPTION:
                scene.setOnMouseMoved(mouseEvent -> {
                    if (mouseEvent.getX() >= 650 && mouseEvent.getX() <= 670 && mouseEvent.getY() >= 150 && mouseEvent.getY() <= 170) {
                        options.index_opt[0] = 1;
                    } else {
                        options.index_opt[0] = 0;
                    }
                    if (mouseEvent.getX() >= 650 && mouseEvent.getX() <= 670 && mouseEvent.getY() >= 150 && mouseEvent.getY() <= 170) {
                        options.index_opt[0] = 1;
                    } else {
                        options.index_opt[0] = 0;
                    }
                });
        }

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
        int level, width, height;
        try {
            File file = new File("res/levels/Level1.txt");
            Scanner scanner = new Scanner(file);
            level = scanner.nextInt();
            height = scanner.nextInt();
            width = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < height; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    stillEntities.add(new Grass(j, i, Sprite.grass));
                    switch (row.charAt(j)) {
                        case 'p':
                            movingEntities.add(new Bomber(j, i, 1, Sprite.player_right));
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i, 0, Sprite.balloon_right1));
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 0, Sprite.oneal_right1));
                            break;
                        case 'b':
                            stillEntities.add(new BombItem(j, i, Sprite.powerup_bombs));
                            break;
                        case 'f':
                            stillEntities.add(new FlameItem(j, i, Sprite.powerup_flames));
                            break;
                        case 's':
                            stillEntities.add(new SpeedItem(j, i, Sprite.powerup_speed));
                            break;
                        case '#':
                            stillEntities.add(new Wall(j, i, Sprite.wall));
                            break;
                        case '*':
                            stillEntities.add(new Brick(j, i, Sprite.brick));
                            break;
                        case 'x':
                            stillEntities.add(new Portal(j, i, Sprite.portal));
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
        switch (GameState.state) {
            case MENU:
                break;
            case PLAYING:
                movingEntities.forEach(Entity::update);
                break;
            case OPTION:
                break;
            case INSTRUCTION:
                break;
            case HIGHSCORE:
                break;
        }

        //menu.update();
        //menuButton.update();
    }

    public void render() {

        switch (GameState.state) {
            case MENU:
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                menu.draw(gc);
                menuButton.draw(gc);
                break;
            case PLAYING:
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                stillEntities.forEach(g -> g.render(gc));
                movingEntities.forEach(g -> g.render(gc));
                Bomber.bombs.forEach(g -> g.render(gc));
                break;
            case OPTION:
                options.draw(gc);
                break;
            case INSTRUCTION:
                ins.draw(gc);
                break;
            case HIGHSCORE:
                highScore.draw(gc);
                break;
            case QUIT:
                break;

        }
    }
}