package com.example.spacegame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    List<ElegirNave> naveList;
    private Nave elegirNave;

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

        shipChooserScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(shipChooserScene);

        createElegirNaveSubScene();
    }

    private void createElegirNaveSubScene() {
        shipChooserScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("ELIGE TU NAVE");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipsToChoose());
    }

    private HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        naveList = new ArrayList<>();
        for (Nave nave : Nave.values()) {
            ElegirNave naveAElegir = new ElegirNave(nave);
            naveList.add(naveAElegir);
            box.getChildren().add(naveAElegir);
            naveAElegir.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (ElegirNave nave : naveList) {
                        nave.setIsCircleChoosen(false);
                    }
                    naveAElegir.setIsCircleChoosen(true);
                    elegirNave = naveAElegir.getNave();

                }
            });
        }
        box.setLayoutX(300 - (118*2));
        box.setLayoutY(100);
        return box;

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
