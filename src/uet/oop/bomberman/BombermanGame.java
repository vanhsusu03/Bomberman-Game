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
import uet.oop.bomberman.entities.MovingEntity.Enemy.Balloon;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Enemy;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Kondoria;
import uet.oop.bomberman.entities.MovingEntity.Enemy.Oneal;
import uet.oop.bomberman.entities.MovingEntity.MovingEntity;
import uet.oop.bomberman.entities.StillEntity.Brick;
import uet.oop.bomberman.entities.StillEntity.Grass;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusItem;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.BonusTarget;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.DezenimanSan;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.Famicom;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.GoddessMask;
import uet.oop.bomberman.entities.StillEntity.Item.BonusItem.NakamotoSan;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BombItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BombpassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.BrickpassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.DetonatorItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.FlameItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.FlamepassItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.MysteryItem;
import uet.oop.bomberman.entities.StillEntity.Item.PowerUpItem.SpeedItem;
import uet.oop.bomberman.entities.StillEntity.Portal;
import uet.oop.bomberman.entities.StillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;

public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static long score = 0;
    public static Entity[][] map = new Entity[HEIGHT][WIDTH];
    public static Entity[][] hiddenEntities = new Entity[HEIGHT][WIDTH];
    public static List<MovingEntity> movingEntities = new ArrayList<>();
    public static Bomber bomber;
    public static BonusItem bonusItem = null;
    private GraphicsContext gc;
    private Canvas canvas;
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

    private Control control_panel = new Control(0, 416, Sprite.control_panel);
    public static int maxTime = 210;

    private int DEFAULT_FONT_SIZE = 25;

    private Label time;
    //private Label score;
    private Label hp;
    private Label level;
    Image image = (Image) new Image(new File("res/textures/control_panel.png").toURI().toString());

    public static void main(String[] args) {
        timeKeeper = new Time();
        Application.launch(BombermanGame.class);
    }

    public static List<MovingEntity> getMovingEntities() {
        return movingEntities;
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

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
//                System.out.println(score);
            }
        };
        timer.start();
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(mouseEvent.getX() + " "+ mouseEvent.getY());
                control_panel.setCoordinatesMouse(mouseEvent.getX(),mouseEvent.getY());
            }
        });

        goNewMap();
        //createLabels();
        //root.getChildren().addAll(time);

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

    public void goNewMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                hiddenEntities[i][j] = null;
            }
        }
        movingEntities.clear();
        Brick.isAnythingDestroyed = false;
        Enemy.isAnyoneKilled = false;
        createMap();
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

            Grass.grassImg = Sprite.grass.getFxImage();
//            bonusItem = new BonusTarget(Sprite.bonus_item_bonus_target);
//             bonusItem = new NakamotoSan(Sprite.bonus_item_nakamoto_san);
//            bonusItem = new DezenimanSan(Sprite.bonus_item_dezeniman_san);
//            bonusItem = new Famicom(Sprite.bonus_item_famicom);
            bonusItem = new GoddessMask(Sprite.bonus_item_goddess_mask);
            for (int i = 0; i < height; i++) {
                String row = scanner.nextLine();
                for (int j = 0; j < width; j++) {
                    switch (row.charAt(j)) {
                        case 'p':
                            bomber = new Bomber(j, i, 1, Sprite.player_right);
                            movingEntities.add(bomber);
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '1':
                            movingEntities.add(new Balloon(j, i, 1, Sprite.balloon_right1, false, false, false));
                            map[i][j] = new Grass(j, i, Sprite.grass);
                            break;
                        case '2':
                            movingEntities.add(new Oneal(j, i, 1, Sprite.oneal_right1, false, false, false));
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

        stageSound.play(-1, false);
    }

    private void createLabels() {
        time = new Label(new Integer(timeKeeper.countSecond()).toString());
        time.setFont(Font.font("Segoe UI Black", FontWeight.BOLD, DEFAULT_FONT_SIZE));
        time.setTextFill(Color.RED);
        time.setLayoutX(675);
        time.setLayoutY(480);
    }

    public static void convertSoundStage() {
        stageSound.stop();
        stageSound2.play(-1, false);
    }

    private void updateMoveSound() {
        if (!KeyAction.keys[KeyEvent.VK_LEFT]
                && !KeyAction.keys[KeyEvent.VK_RIGHT]) {
            moveLeftRightSound.stop();
        }
        if (!KeyAction.keys[KeyEvent.VK_UP]
                && !KeyAction.keys[KeyEvent.VK_DOWN]) {
            moveUpDownSound.stop();
        }
    }

    public void update() {
        //time.setText("Time: " + (maxTime - timeKeeper.countSecond()));
        movingEntities.forEach(Entity::update);
        control_panel.update();
        int n = bomber.getBombs().size();
        for (int i = 0; i < bomber.getBombs().size(); i++) {
            bomber.getBombs().get(i).update();
            if (n > bomber.getBombs().size()) {
                i--;
                n = bomber.getBombs().size();
            }
        }

        updateMoveSound();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (map[i][j] instanceof Portal || map[i][j] instanceof Brick
                        || map[i][j] instanceof BonusItem) {
                    gc.drawImage(Grass.grassImg,
                            j * Sprite.SCALED_SIZE, i * Sprite.SCALED_SIZE);
                }
                map[i][j].render(gc);
            }
        }

        control_panel.render(gc);

        bomber.getBombs().forEach(bomb -> bomb.render(gc));

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