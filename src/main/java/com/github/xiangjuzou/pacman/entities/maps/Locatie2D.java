package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.Coordinate2D;

public class Locatie2D {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Locatie2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Locatie2D(Coordinate2D coordinate) {
        this.x = (int)(coordinate.getX() / 16);
        this.y = (int)(coordinate.getY() /16);
    }

    public Coordinate2D GetCoordinate() {
        return new Coordinate2D(x*16, y*16);
    }
}
