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

    List<SpaceInvadersButton> menuButtons;

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

    private void createSubScenes() {
        creditsSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(scoreSubScene);

        shipChooserScene = new SpaceInvadersSubScene();
        mainPane.getChildren().add(shipChooserScene);
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
                shipChooserScene.moveSubScene();
            }
        });
    }

    private void createScoresButton() {
        SpaceInvadersButton scoresButton = new SpaceInvadersButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scoreSubScene.moveSubScene();
            }
        });
    }

    private void createHelpButton() {
        SpaceInvadersButton helpButton = new SpaceInvadersButton("AYUDA");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                helpSubScene.moveSubScene();
            }
        });
    }

    private void createCreditsButton() {
        SpaceInvadersButton creditsButton = new SpaceInvadersButton("CREDITOS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                creditsSubScene.moveSubScene();

            }
        });

    }

    private void createExitButton() {
        SpaceInvadersButton exitButton = new SpaceInvadersButton("SALIR");
        addMenuButton(exitButton);
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
