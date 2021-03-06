package com.example.spacegame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManage {

    private static final int WITDH = 1024;
    private static final int HEIGHT = 768;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private SpaceInvadersSubScene creditsSubScene;
    private SpaceInvadersSubScene helpSubScene;
    private SpaceInvadersSubScene scoreSubScene;
    private SpaceInvadersSubScene shipChooserScene;

    private SpaceInvadersSubScene sceneToHide;

    List<SpaceInvadersButton> menuButtons;

    List<ShipPicker> shipsList;
    private Ship choosenShip;

    public ViewManage () {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WITDH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();

    }

    private void showSubScene(SpaceInvadersSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        creditsSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(scoreSubScene);

        createShipChooserSubScene();
    }

    private void createShipChooserSubScene() {
        shipChooserScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("ELIGE TU NAVE");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipsToChoose());
        shipChooserScene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for (Ship ship : Ship.values()) {
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    for (ShipPicker ship : shipsList) {
                        ship.setIsCircleChoosen(false);
                    }
                    shipToPick.setIsCircleChoosen(true);
                    choosenShip = shipToPick.getShip();
                }
            });
        }

        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;
    }

    private SpaceInvadersButton createButtonToStart() {
        SpaceInvadersButton startButton = new SpaceInvadersButton("EMPEZAR");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (choosenShip!= null){
                    GameViewManager gameManager = new GameViewManager();
                    gameManager.createNewGame(mainStage, choosenShip);
                }
            }
        });

        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceInvadersButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();

    }

    private void createStartButton() {
        SpaceInvadersButton startButton = new SpaceInvadersButton("JUGAR");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(shipChooserScene);
            }
        });
    }

    private void createScoresButton() {
        SpaceInvadersButton scoresButton = new SpaceInvadersButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubScene);
            }
        });
    }

    private void createHelpButton() {
        SpaceInvadersButton helpButton = new SpaceInvadersButton("AYUDA");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubScene);
            }
        });
    }

    private void createCreditsButton() {
        SpaceInvadersButton creditsButton = new SpaceInvadersButton("CREDITOS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);

            }
        });

    }

    private void createExitButton() {
        SpaceInvadersButton exitButton = new SpaceInvadersButton("SALIR");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("file:img/darkPurple.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("file:img/space_invaders.png");
        logo.setLayoutX(470);
        logo.setLayoutY(50);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent Event) {
                logo.setEffect(new DropShadow());

            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent Event) {
                logo.setEffect(null);

            }
        });

        mainPane.getChildren().add(logo);
    }

}
