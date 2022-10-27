package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import uet.oop.bomberman.Sound.Sound;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;


import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {
    public static final String SCORES_PATH = "res/top3_highest_score.log";
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int MAX_LEVEL = 5;
    public static long score = 0;
    public static Entity[][] map = new Entity[HEIGHT][WIDTH];
    public static Entity[][] hiddenEntities = new Entity[HEIGHT][WIDTH];
    public static List<MovingEntity> movingEntities = new ArrayList<>();
    public static Bomber bomber;
    public static BonusItem bonusItem = null;
    public static int status = 0;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Sound moveLeftRightSound = new Sound("move_left_right");
    public static Sound moveUpDownSound = new Sound("move_up_down");
    public static Sound putBombSound = new Sound("put_bomb");
    public static Sound eatItemSound = new Sound("eat_item");
    public static Sound bombExplosionSound = new Sound("bomb_explosion");
    public static Sound bomberDeathSound = new Sound("bomber_death");
    public static Sound stageSound = new Sound("stage");
    public static Sound stageSound2 = new Sound("stage2");
    public static Sound levelCompleteSound = new Sound("level_complete");
    public static Sound menuStartSound = new Sound("menu_start");
    public static Sound gameOverSound = new Sound("game_over");
    public static Sound winGameSound = new Sound("win_game");
    public static String[] top3HighestScores = new String[3];


    public static int numOfEnemies = 0;

    public static StartGame startGame;

    static {
        try {
            startGame = new StartGame();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Menu menu = new Menu();

    public static StartGame getStartGame() {
        return startGame;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override

    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 96);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);


        // Tao scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    update();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    render();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.start();
        scene.setOnMouseMoved(event -> {
            MouseAction.setMouseCoordinates(event.getX(), event.getY());
            MouseAction.setIsClicked(false);
        });
        scene.setOnMouseClicked(event -> MouseAction.setIsClicked(true));


        scene.setOnKeyPressed(event -> KeyAction.setKeptKey(String.valueOf(event.getCode()), true));

        scene.setOnKeyReleased(event -> KeyAction.setKeptKey(String.valueOf(event.getCode()), false));

        scene.setOnKeyTyped(event -> KeyAction.setTypedKey(event.getCharacter(), true));
    }

    public void update() throws FileNotFoundException {
        if (status == 0) {
            menu.updateMenu();
            gc.setFont(Menu.font);
            gc.setFill(Color.BEIGE);
            menuStartSound.play(-1, false);
        } else {
            menuStartSound.stop();
        }

        if (status == 1) {
            startGame.updateGamePlay();
            gc.setFont(StartGame.font);
            gc.setFill(Color.GOLD);
            stageSound.play(-1, false);
        } else {
            Sound.stopStageSound();
        }

    }

    public static void read3HighestScores() {
        try {
            FileInputStream fileInputStream = new FileInputStream(SCORES_PATH);
            Scanner scanner = new Scanner(fileInputStream);
            for (int i = 0; i < top3HighestScores.length; i++) {
                top3HighestScores[i] = scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render() throws FileNotFoundException {
        if (status == 1) {
            startGame.renderGamePlay();
        } else {
            menu.renderMenu();
        }
    }
}