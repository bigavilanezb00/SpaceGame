package com.example.spacegame;

public enum Ship {

    AZUL("file:img/playerShip1_blue.png"),
    VERDE("file:img/playerShip1_green.png"),
    NARANJA("file:img/playerShip1_orange.png"),
    ROJO ("file:img/playerShip1_red.png");

    private String urlShip;

    private Ship(String urlShip) {
        this.urlShip = urlShip;
    }

    public String getUrl() {
        return  this.urlShip;
    }
}
