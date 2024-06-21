package org.limepepper.demo.model;

public class Player {
    // Player has a Tile color
    private final Tile tile;
    private Boolean isHuman;

    public Player(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }
}
