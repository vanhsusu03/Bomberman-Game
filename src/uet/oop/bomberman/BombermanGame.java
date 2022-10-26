package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.UI.Panels.Control;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MovingEntity.Bomber.Bomber;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;


import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.MapLoadFile;


public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static long score = 0;
    public static Entity[][] map = new Entity[HEIGHT][WIDTH];
    public static Entity[][] hiddenEntities = new Entity[HEIGHT][WIDTH];
    public static List<MovingEntity> movingEntities = new ArrayList<>();
    public static Bomber bomber;
    public static BonusItem bonusItem = null;
    public static int status = 0;
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Time timeKeeper;
    public static MouseEvent e;
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


    public static int numOfEnemies = 0;

    private static StartGame startGame;

    static {
        try {
            startGame = new StartGame();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Menu menu = new Menu();

    public static int maxTime = 210;

    public static StartGame getStartGame() {
        return startGame;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT + 96);
        gc = canvas.getGraphicsContext2D();
        startGame.createNewGame(1);
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
        scene.setOnMouseClicked(event -> {
            MouseAction.setIsClicked(true);
        });


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

    public void update() throws FileNotFoundException {
        if (status == 0) {
            menu.updateMenu();
            menuStartSound.play(-1, false);
        } else {
            menuStartSound.stop();
        }

        if (status == 1) {
            startGame.updateGamePlay();
            stageSound.play(-1, false);
        } else {
            stageSound.stop();
            stageSound2.stop();
        }

//        switch (status) {
//            case 1:
//                startGame.updateGamePlay();
//                stageSound.play(-1, false);
//                break;
//            case 2:
//
//            default:
//                menu.updateMenu();
//                menuStartSound.play(-1, false);
//                break;
//        }
    }

    public void render() throws FileNotFoundException {
        switch (status) {
            case 1:
                startGame.renderGamePlay();
                break;
            default:
                menu.renderMenu();
                break;
        }
    }
}