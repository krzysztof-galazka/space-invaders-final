package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceSubscene extends SubScene {

    private final static String FONT_DESTINATION = "src/model/resources/ToleyHand_PERSONAL_USE_ONLY.ttf";
    private final static String BACKROUND_IMAGE = "/model/resources/blue_panel.png";

    private boolean isHidden;

    public SpaceSubscene() {
        super(new AnchorPane(), 600, 400);
        prefWidth(600);
        prefHeight(400);

//        BackgroundImage image = new BackgroundImage(new Image(BACKROUND_IMAGE, 600,400, false, true),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.backgroundProperty();

        isHidden=true;

        setLayoutX(1024);
        setLayoutY(180);

    }

    public void moveSubscene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.1));
        transition.setNode(this);
        transition.setToX(-676);

        if(isHidden){
            transition.setToX(-676);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = false;
        }

        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }

}
