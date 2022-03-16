package com.example.spacegame;

public enum Ship {

    AZUL("file:img/playerShip1_blue.png", "file:img/playerLife1_blue.png"),
    VERDE("file:img/playerShip1_green.png", "file:img/playerLife1_green.png"),
    NARANJA("file:img/playerShip1_orange.png", "file:img/playerLife1_orange.png"),
    ROJO ("file:img/playerShip1_red.png", "file:img/playerLife1_red.png");

    private String urlShip;
    private String urlLife;

    private Ship(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrl() {
        return  this.urlShip;
    }

    public String getUrlLife() {
        return urlLife;
    }
}
