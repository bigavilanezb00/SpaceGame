package com.example.spacegame;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private String circleNotChoosen = "file:img/grey_circle.png";
    private String circleChoosen = "file:img/yellow_boxTick.png";

    private Ship ship;

    private boolean isCircleChoosen;

    public ShipPicker(Ship ship) {
        circleImage = new ImageView(circleNotChoosen);
        shipImage = new ImageView(ship.getUrl());
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }

    public Ship getShip() {
        return ship;
    }

    public boolean getIsCircleChoseen() {
        return isCircleChoosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen) {
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }

}
