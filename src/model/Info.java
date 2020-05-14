package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Info extends Label {

    public final String FONT_PATH = "src/model/resources/ToleyHand_PERSONAL_USE_ONLY.ttf";


    public Info(String text) {
        setPrefWidth(600);
        setPrefHeight(400);
        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }

    private void setLabelFont(){
       try {
           setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
       } catch (FileNotFoundException e){
           setFont(Font.font("Verdana", 23));
       }
       }

}
