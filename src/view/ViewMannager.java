package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class ViewMannager {

    private static final int HEIGHT = 800;
    private static final int WIDTH = 1000;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;

    private  SpaceSubscene sceneToHide;

    private final static int BUTTON_STARTX = 150;
    private final static int BUTTON_STARTY = 150;

    private SpaceSubscene creditsSubscene;
    private SpaceSubscene helpSubscene;
    private SpaceSubscene scoreSubscene;
    private SpaceSubscene shipPickerSubscene;


    List<SpaceButton> menuButtons;

    List<ShipChoser> shipChoserList;
    private SHIP choosenShip;

    public ViewMannager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
        createBackground();
        createSubscene();
//        SpaceSubscene subscene = new SpaceSubscene();
//        subscene.setLayoutX(200);
//        subscene.setLayoutY(100);
//        mainPane.getChildren().add(subscene);
    }

    private void showSubscene(SpaceSubscene subscene){
        if(sceneToHide != null){
            sceneToHide.moveSubscene();
        }
        subscene.moveSubscene();
        sceneToHide = subscene;
    }

    private void createSubscene(){
        creditsSubscene = new SpaceSubscene();
        mainPane.getChildren().add(creditsSubscene);

        helpSubscene = new SpaceSubscene();
        mainPane.getChildren().add(helpSubscene);

        scoreSubscene = new SpaceSubscene();
        mainPane.getChildren().add(scoreSubscene);

        createShipPickerSubscene();


    }

    private void createShipPickerSubscene() {
        shipPickerSubscene = new SpaceSubscene();
        mainPane.getChildren().add(shipPickerSubscene);

        Info choseShipLabel = new Info("Choose ship:");
        choseShipLabel.setLayoutX(25);
        choseShipLabel.setLayoutY(-150);
        shipPickerSubscene.getPane().getChildren().add(choseShipLabel);
        shipPickerSubscene.getPane().getChildren().add(createShipToChose());
        shipPickerSubscene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createShipToChose(){
        HBox box = new HBox();
        box.setSpacing(20);

        shipChoserList = new ArrayList<>();
        for (SHIP ship : SHIP.values()){
            ShipChoser shipToChose = new ShipChoser(ship);
            shipChoserList.add(shipToChose);
            box.getChildren().add(shipToChose);
            shipToChose.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ShipChoser ship:shipChoserList){
                        ship.setCircleChoosen(false);
                    }
                    shipToChose.setCircleChoosen(true);
                    choosenShip = shipToChose.getShip();
                }
            });

        }
        box.setLayoutX((300-(110*2)));
        box.setLayoutY(100);
        return box;
    }

    private SpaceButton createButtonToStart(){
        SpaceButton startButton = new SpaceButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (choosenShip != null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.startNewGame(mainStage, choosenShip);
                }
            }
        });

        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addButton(SpaceButton button) {
        button.setLayoutY(BUTTON_STARTY);
        button.setLayoutY(BUTTON_STARTX + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButton() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        SpaceButton startButton = new SpaceButton("START");
        addButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubscene(shipPickerSubscene);
            }
        });
    }

    private void createScoreButton() {
        SpaceButton scoreButton = new SpaceButton("SCORE");
        addButton(scoreButton);

        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubscene(scoreSubscene);
            }
        });
    }

    private void createHelpButton() {
        SpaceButton helpButton = new SpaceButton("HELP");
        addButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubscene(helpSubscene);
            }
        });
    }

    private void createCreditsButton() {
        SpaceButton creditsButton = new SpaceButton("CREDITS");
        addButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubscene(creditsSubscene);
            }
        });
    }

    private void createExitButton() {
        SpaceButton exitButton = new SpaceButton("EXIT");
        addButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }


    private void createBackground() {
        Image backgroundImage = new Image("view/resources/background.png", 256, 256, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }


}
