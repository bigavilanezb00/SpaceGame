package com.example.spacegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SpaceInvaders extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            ViewManage manage = new ViewManage();
            primaryStage = manage.getMainStage();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
