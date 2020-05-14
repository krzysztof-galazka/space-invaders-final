package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;

public class ShipChoser  extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private String circleNotChosen = "view/resources/choose/grey_circle.png";
    private String circleChosen = "view/resources/choose/circle_choose.png";

    private SHIP ship;

    private boolean isCircleChoosen;

    public ShipChoser (SHIP ship){
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(ship.getUrlShip());
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }

    public SHIP getShip(){
        return ship;
    }

    public boolean getCircleChoose(){
        return isCircleChoosen;
    }

    public void setCircleChoosen(boolean isCircleChoosen){
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }

}
