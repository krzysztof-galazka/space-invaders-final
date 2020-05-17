package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;
import model.SmallLabel;

import java.util.Random;


public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_HEIGHT = 720;
    private static final int GAME_WIDTH = 1024;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer animationTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:src/view/resources/background.png";

    static final Image METEOR_BROWN = new Image("file:src/view/resources/meteor_brown.png");
    static final Image METEOR_GREY = new Image("file:src/view/resources/meteor_grey.png");

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;
    Random random;

    private ImageView star;
    private SmallLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private final static String GOLD_STAR = "file:src/view/resources/star_gold.png";

    private final static int STAR_RADIUS = 10;
    private final static int SHIP_RADIUS = 25;
    private final static int METEOR_RADIUS = 18;
    private SHIP choosenShip;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        random = new Random();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }

            }
        });
        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                }

            }
        });

    }

    public void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_HEIGHT, GAME_WIDTH);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void startNewGame(Stage menuStage, SHIP choosenShip) {
        this.choosenShip = choosenShip;
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(choosenShip);
        createGameElements(choosenShip);
        createGameLoop();
        checkIfElementsMeetsTogether();
        gameStage.show();
    }

    private void createGameElements(SHIP choosenShip) {
        playerLife = 2;
        star = new ImageView(GOLD_STAR);
        setElementsPossition(star);
        gamePane.getChildren().add(star);
        pointsLabel = new SmallLabel("Points: 00");
        pointsLabel.setLayoutX(450);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        playerLifes = new ImageView[3];


        for (int i = 0; i < playerLifes.length; i++) {
            playerLifes[i] = new ImageView(choosenShip.getUrlLife());
            playerLifes[i].setLayoutX(455 + (i * 50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);
        }
        brownMeteors = new ImageView[3];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(METEOR_BROWN);
            setElementsPossition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }
        greyMeteors = new ImageView[3];
        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i] = new ImageView(METEOR_GREY);
            setElementsPossition(greyMeteors[i]);
            gamePane.getChildren().add(greyMeteors[i]);
        }
    }

    private void moveElements() {
        star.setLayoutY(star.getLayoutY() + 3);

        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY()+5);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate()+4);
        }

        for (int i = 0; i < greyMeteors.length; i++) {
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY()+5);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate()+4);
        }
    }

    private void checkIfElementsAreBehindShip() {
        if (star.getLayoutY() > 1100) {
            setElementsPossition(star);
        }

        for (int i = 0; i < brownMeteors.length; i++) {
            if (brownMeteors[i].getLayoutY() > 1100) {
                setElementsPossition(brownMeteors[i]);
            }
        }

        for (int i = 0; i < greyMeteors.length; i++) {
            if (greyMeteors[i].getLayoutY() > 1100) {
                setElementsPossition(greyMeteors[i]);
            }
        }
    }

    private void setElementsPossition(ImageView image) {
        image.setLayoutX(random.nextInt(GAME_WIDTH));
        image.setLayoutY(random.nextInt(GAME_HEIGHT));
    }


    private void createShip(SHIP choosenShip) {

        ship = new ImageView(choosenShip.getUrlShip());
        ship.setLayoutX((GAME_WIDTH)/2);
        ship.setLayoutY((GAME_HEIGHT)-90 );
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //moveBackground();
                moveElements();
                checkIfElementsAreBehindShip();
                moveShip();
                checkIfElementsMeetsTogether();
            }
        };
        animationTimer.start();
    }

    private void moveShip() {
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 3);
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() < 522) {
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }

    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroudImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroudImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroudImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroudImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroudImage1);
            gridPane2.getChildren().add(backgroudImage2);

        }
        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane1.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }

    }

    private void checkIfElementsMeetsTogether() {
        if (SHIP_RADIUS + STAR_RADIUS > distanceCalculator(ship.getLayoutX() + 40, star.getLayoutX() + 15, ship.getLayoutY()
                + 37, star.getLayoutY() + 15)) {
            setElementsPossition(star);

            points++;
            String textToSet = "Points: ";
            if (points < 10) {
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points);
        }
        for (int i = 0; i < brownMeteors.length; i++) {
            if (METEOR_RADIUS + SHIP_RADIUS > distanceCalculator(ship.getLayoutX() + 40,
                    brownMeteors[i].getLayoutX() + 20, ship.getLayoutY() + 37, brownMeteors[i].getLayoutY() + 20)) {
                loseLife();
                setElementsPossition(brownMeteors[i]);

            }
        }

        for (int i = 0; i < greyMeteors.length; i++) {
            if (METEOR_RADIUS + SHIP_RADIUS > distanceCalculator(ship.getLayoutX() + 40,
                    greyMeteors[i].getLayoutX(), ship.getLayoutY(), greyMeteors[i].getLayoutY())) {
                loseLife();
                setElementsPossition(greyMeteors[i]);

            }
        }
    }

    private void loseLife() {
        gamePane.getChildren().remove(playerLife);
        playerLife--;
        if (playerLife < 0) {
            gameStage.close();
            menuStage.show();
        }
        createShip(choosenShip);
    }

    private double distanceCalculator(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}

