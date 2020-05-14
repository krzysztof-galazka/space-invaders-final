package model;

public enum SHIP {

    BLUE("file:src/view/resources/choose/player_blue.png", "view/resources/choose/blue_life.png"),
    GREEN("file:src/view/resources/choose/player_green.png", "view/resources/choose/green_life.png"),
    RED("file:src/view/resources/choose/player_red.png", "view/resources/choose/red_life.png");


    private String urlShip;
    private String urlLife;

    SHIP(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrlShip(){
        return this.urlShip;
    }

    public String getUrlLife(){
        return this.urlLife;
    }
}
