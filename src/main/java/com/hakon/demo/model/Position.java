package com.hakon.demo.model;

public class Position {
    private final int x;
    private final int y;
    public boolean triedEast = false;
    public boolean tiredSouth = false;
    public boolean triedNorth = false;
    public boolean triedWest = false;
    public boolean isBackTrack = false;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Position position){
        return  this.x == position.x && this.y == position.y;
    }

}
