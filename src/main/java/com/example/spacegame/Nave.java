package com.example.spacegame;

public enum Nave {

    AZUL("file:img/playerShip1_blue.png"),
    VERDE("file:img/playerShip1_green.png"),
    NARANJA("file:img/playerShip1_orange.png"),
    ROJO ("file:img/playerShip1_red.png");

    private String urlNave;

    private Nave(String urlNave) {
        this.urlNave = urlNave;
    }

    public String getUrlNave() {
        return  this.urlNave;
    }
}
