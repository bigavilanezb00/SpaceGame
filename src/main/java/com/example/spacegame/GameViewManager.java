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

import java.util.Random;

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

    private final static String METEORO_MARRON_IMAGE = "file:img/meteorBrown_big4.png";
    private final static String METEORO_GRIS_IMAGE = "file:img/meteorGrey_big4.png";

    private ImageView[] MeteorosMarrones;
    private ImageView[] MeteorosGrises;
    Random randomPositionGenerator;

    public GameViewManager() {
        initializaStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
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

    public void createNewGame(Stage menuStage, Ship choosenShip){
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(choosenShip);
        createGameElements(choosenShip);
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements( Ship choosenShip){
        MeteorosMarrones = new ImageView[3];
        for (int i = 0; i < MeteorosMarrones.length; i++){
            MeteorosMarrones[i] = new ImageView(METEORO_MARRON_IMAGE);
            setNewElementsPosition(MeteorosMarrones[i]);
            gamePane.getChildren().add(MeteorosMarrones[i]);
        }

        MeteorosGrises = new ImageView[3];
        for (int i = 0; i < MeteorosGrises.length; i++){
            MeteorosGrises[i] = new ImageView(METEORO_GRIS_IMAGE);
            setNewElementsPosition(MeteorosGrises[i]);
            gamePane.getChildren().add(MeteorosGrises[i]);
        }
    }

    private void moveGameElements(){
        for (int i = 0; i < MeteorosMarrones.length; i++){
            MeteorosMarrones[i].setLayoutY(MeteorosMarrones[i].getLayoutY()+7);
            MeteorosMarrones[i].setRotate(MeteorosMarrones[i].getRotate()+4);
        }
        for (int i = 0; i < MeteorosGrises.length; i++){
            MeteorosGrises[i].setLayoutY(MeteorosGrises[i].getLayoutY()+7);
            MeteorosGrises[i].setRotate(MeteorosGrises[i].getRotate()+4);
        }
    }

    private void checkIfElementsAreBehindTheShipAndRelocate(){

        for (int i = 0; i < MeteorosMarrones.length; i++){
            if (MeteorosMarrones[i].getLayoutY() > 900) {
                setNewElementsPosition(MeteorosMarrones[i]);
            }
        }

        for (int i = 0; i < MeteorosGrises.length; i++){
            if (MeteorosGrises[i].getLayoutY() > 900){
                setNewElementsPosition(MeteorosGrises[i]);
            }
        }
    }

    private void setNewElementsPosition(ImageView image) {
        image.setLayoutX(randomPositionGenerator.nextInt(370));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200)+600));
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
                moveBackground();
                moveGameElements();
                checkIfElementsAreBehindTheShipAndRelocate();
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

        for (int i = 0; i < 12; i++){
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i% 3, i /3);
            GridPane.setConstraints(backgroundImage2, i% 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1,gridPane2);
    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024){
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024){
            gridPane2.setLayoutY(-1024);
        }

    }

}
