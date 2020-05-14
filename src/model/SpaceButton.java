package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceButton extends Button {

    private final String FONT = "src/model/resources/ToleyHand_PERSONAL_USE_ONLY.ttf";
    private final String BUTTON_PRESSED = "/model/resources/buttonpress.png";
    private final String BUTTON_NOT_PRESSED = "/model/resources/button.png";

    public SpaceButton(String txt){
        setText(txt);
        setButtonText();
        setPrefWidth(190);
        setPrefHeight(50);
        setStyle(String.valueOf(BUTTON_NOT_PRESSED));
        initializeButtonListeners();
    }

    private void setButtonText(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT),23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY()+4);
    }

    private void setButtonNotPressedStyle(){
        setStyle(BUTTON_NOT_PRESSED);
        setPrefHeight(49);
        setLayoutY(getLayoutY()-4);
    }

    private void initializeButtonListeners(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonNotPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }



}
