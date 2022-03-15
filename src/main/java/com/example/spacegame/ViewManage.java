package com.example.spacegame;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManage {

    private static final int WITDH = 800;
    private static final int HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    List<SpaceInvadersButton> menuButtons;

    public ViewManage () {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WITDH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceInvadersButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
    }

    private void createButtons() {
        SpaceInvadersButton button = new SpaceInvadersButton("click me!");
        mainPane.getChildren().add(button);

        button.setLayoutX(200);
        button.setLayoutY(200);
    }

    private void createBackground() {
        Image backgroundImage = new Image("file:img/darkPurple.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

}
