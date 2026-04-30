package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.LoopingAnimation;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Spook extends TravelingSpriteEntity {

    private Bord bord;
    private Direction huidigeRichting = Direction.LEFT;
    private final int snelheid = 2;
    private final Random random = new Random();

    public Spook(final String color, final Coordinate2D location) {
        super("sprites/spritemap.png", location, 7, 14);
        setAutoCycle(250);
        var animation = new LoopingAnimation(4, 0, 4, 1, 250);
        this.playAnimation(animation);
    }

    public void start(Bord bord) {
        this.bord = bord;
        setMotion(snelheid, huidigeRichting);
    }

    @Override
    public void onDistanceReached() {
        if (bord == null) return;

        // Huidige positie op het grid
        var locatie = new Locatie2D(getAnchorLocation().add(new Coordinate2D(16, 16)));

        // Verzamel alle richtingen zonder muur
        List<Direction> mogelijkeRichtingen = new ArrayList<>();
        for (Direction richting : new Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT}) {
            if (!bord.heeftMuur(locatie, richting, true) && nietTerug(richting)) {
                mogelijkeRichtingen.add(richting);
            }
        }

        // Kies een random richting
        if (!mogelijkeRichtingen.isEmpty()) {
            huidigeRichting = mogelijkeRichtingen.get(random.nextInt(mogelijkeRichtingen.size()));
            setMotion(snelheid, huidigeRichting);
        }
    }

    private boolean nietTerug(Direction richting){
        switch (huidigeRichting) {
            case Direction.UP -> { return richting != Direction.DOWN;}
            case Direction.DOWN -> { return richting != Direction.UP;}
            case Direction.RIGHT -> { return richting != Direction.LEFT;}
            case Direction.LEFT -> { return richting != Direction.RIGHT;}
        }
        return true;
    }
}