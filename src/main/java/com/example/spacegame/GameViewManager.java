package com.example.spacegame;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private AnimationTimer gameTimer;
    private int angle;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "file:img/darkPurple.png";

    public GameViewManager() {
        initializaStage();
        createKeyListeners();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = true;
                }

            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = false;
                }

            }
        });

    }

    private void initializaStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage, Ship choosenChip){
        this.menuStage = menuStage;
        this.menuStage.hide();
        createShip(choosenChip);
        createGameLoop();
        gameStage.show();
    }

    private void createShip(Ship choosenShip) {
        ship = new ImageView(choosenShip.getUrl());
        ship.setLayoutX(GAME_WIDTH/2);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveShip();
            }
        };
        gameTimer.start();
    }

    private void moveShip(){
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30){
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20){
                ship.setLayoutX(ship.getLayoutX() -3);
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30){
                angle += 5;
            }
            ship.setRotate(angle);

            if (ship.getLayoutX() < 522){
                ship.setLayoutX(ship.getLayoutX() +3);
            }

        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle +=5;
            } else if (angle > 0){
                angle -=5;
            }
            ship.setRotate(angle);

        }

        if (isLeftKeyPressed && isRightKeyPressed){
            if (angle < 0) {
                angle +=5;
            } else if (angle > 0) {
                angle -=5;
            }
            ship.setRotate(angle);

        }
    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

    }

}
