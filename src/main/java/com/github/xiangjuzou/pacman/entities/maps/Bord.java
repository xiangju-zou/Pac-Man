package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.scenes.TileMap;

public class Bord extends TileMap {
    //  28 breed, 31 hoog
    private final int[][] map = new int[][] {
            { 4,13,13,13,13,13,13,13,13,13,13,13,13, 1, 2,13,13,13,13,13,13,13,13,13,13,13,13, 5},
            {16,39,39,39,39,39,39,39,39,39,39,39,39,33,32,39,39,39,39,39,39,39,39,39,39,39,39,17},
            {16,39,22,28,28,23,39,22,28,28,28,23,39,33,32,39,22,28,28,28,23,39,22,28,28,23,39,17},
            {16,37,33, 0, 0,32,39,33, 0, 0, 0,32,39,33,32,39,33, 0, 0, 0,32,39,33, 0, 0,32,37,17},
            {16,39,26,29,29,27,39,26,29,29,29,27,39,26,27,39,26,29,29,29,27,39,26,29,29,27,39,17},
            {16,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,17},
            {16,39,22,28,28,23,39,22,23,39,22,28,28,28,28,28,28,23,39,22,23,39,22,28,28,23,39,17},
            {16,39,26,29,29,27,39,33,32,39,26,29,29,21,20,29,29,27,39,33,32,39,26,29,29,27,39,17},
            {16,39,39,39,39,39,39,33,32,39,39,39,39,33,32,39,39,39,39,33,32,39,39,39,39,39,39,17},
            { 8,12,12,12,12, 7,39,33,24,28,28,23, 0,33,32, 0,22,28,28,25,32,39, 6,12,12,12,12, 9},
            { 0, 0, 0, 0, 0,16,39,33,20,29,29,27, 0,26,27, 0,26,29,29,21,32,39,17, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0,16,39,33,32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,33,32,39,17, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0,16,39,33,32, 0, 6,12,12, 3, 3,12,12, 7, 0,33,32,39,17, 0, 0, 0, 0, 0},
            {19,13,13,13,13,11,39,26,27, 0,17, 0, 0, 0, 0, 0, 0,16, 0,26,27,39,10,13,13,13,13,15},
            { 0, 0, 0, 0, 0, 0,39, 0, 0, 0,17, 0, 0, 0, 0, 0, 0,16, 0, 0, 0,39, 0, 0, 0, 0, 0, 0},
            {18,12,12,12,12, 7,39,22,23, 0,17, 0, 0, 0, 0, 0, 0,16, 0,22,23,39, 6,12,12,12,12,14},
            { 0, 0, 0, 0, 0,16,39,33,32, 0,10,13,13,13,13,13,13,11, 0,33,32,39,17, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0,16,39,33,32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,33,32,39,17, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0,16,39,33,32, 0,22,28,28,28,28,28,28,23, 0,33,32,39,17, 0, 0, 0, 0, 0},
            { 4,13,13,13,13,11,39,26,27, 0,26,29,29,21,20,29,29,27, 0,26,27,39,10,13,13,13,13, 5},
            {16,39,39,39,39,39,39,39,39,39,39,39,39,33,32,39,39,39,39,39,39,39,39,39,39,39,39,17},
            {16,39,22,28,28,23,39,22,28,28,28,23,39,33,32,39,22,28,28,28,23,39,22,28,28,23,39,17},
            {16,39,26,29,21,32,39,26,29,29,29,27,39,26,27,39,26,29,29,29,27,39,33,20,29,27,39,17},
            {16,37,39,39,33,32,39,39,39,39,39,39,39, 0, 0,39,39,39,39,39,39,39,33,32,39,39,37,17},
            {30,28,23,39,33,32,39,22,23,39,22,28,28,28,28,28,28,23,39,22,23,39,33,32,39,22,28,31},
            {34,29,27,39,26,27,39,33,32,39,26,29,29,21,20,29,29,27,39,33,32,39,26,27,39,26,29,35},
            {16,39,39,39,39,39,39,33,32,39,39,39,39,33,32,39,39,39,39,33,32,39,39,39,39,39,39,17},
            {16,39,22,28,28,28,28,25,24,28,28,23,39,33,32,39,22,28,28,25,24,28,28,28,28,23,39,17},
            {16,39,26,29,29,29,29,29,29,29,29,27,39,26,27,39,26,29,29,29,29,29,29,29,29,27,39,17},
            {16,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,39,17},
            { 8,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12, 9},
    };

    public boolean heeftMuur(Locatie2D locatie, Direction richting, Boolean isSpook) {
        // Controleert of een tile een muur naast zich heeft in de opgegeven richting

        switch (richting) {
            case Direction.DOWN -> {
                return controleerMuur(new Locatie2D(locatie.getX(), locatie.getY() + 1), isSpook);
            }
            case Direction.UP -> {
                return controleerMuur(new Locatie2D(locatie.getX(), locatie.getY() - 1), isSpook);
            }
            case Direction.RIGHT -> {
                return controleerMuur(new Locatie2D(locatie.getX()+1, locatie.getY()), isSpook);
            }
            case Direction.LEFT -> {
                return controleerMuur(new Locatie2D(locatie.getX()-1, locatie.getY()), isSpook);
            }
        }

        return true;
    }

    public Gegeten eetStip(Locatie2D locatie) {
        var y = locatie.getY();
        var x = locatie.getX();

        if (getInstanceMap()[y][x] instanceof DotTegel) {
            getInstanceMap()[y][x].remove();

            return Gegeten.DOT;
        }
        if (getInstanceMap()[y][x] instanceof PowerPelletTegel) {
            getInstanceMap()[y][x].remove();

            return Gegeten.POWERPELLET;
        }

        return Gegeten.NIETS;
    }

    private boolean controleerMuur(Locatie2D locatie, Boolean isSpook) {
        int tileId = map[locatie.getY()][locatie.getX()];

        // TitleId 0 is zwarte tegel, 37 is PowerPelletTegel, 39 is DotTegel
        if (tileId == 0 || tileId == 37 || tileId == 39) {
            return false;
        }

        if (tileId == 3 && isSpook) {
            return false;
        }

        return true;
    }

    @Override
    public void setupEntities() {
        for (int i = 1; i < 36; i++) {
            addEntity(i, BordTegel.class, new TegelConfig(i));
        }

        addEntity(37, PowerPelletTegel.class);
        addEntity(39, DotTegel.class);
    }

    @Override
    public int[][] defineMap() {
        return  map;
    }
}
