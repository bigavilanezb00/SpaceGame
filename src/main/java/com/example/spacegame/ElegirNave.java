package com.example.spacegame;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ElegirNave extends VBox {

    private ImageView circleImage;
    private ImageView naveImage;

    private String circleNotChoosen = "file:img/grey_circle.png";
    private String circleChoosen = "file:img/yellow_boxTick.png";

    private Nave nave;

    private boolean isCircleChoosen;

    public ElegirNave(Nave nave) {
        circleImage = new ImageView(circleNotChoosen);
        naveImage = new ImageView(nave.getUrlNave());
        this.nave = nave;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(naveImage);
    }

    public Nave getNave() {
        return nave;
    }

    public boolean getIsCircleChoosen() {
        return isCircleChoosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen) {
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleChoosen;
        circleImage.setImage(new Image(imageToSet));
    }

}
