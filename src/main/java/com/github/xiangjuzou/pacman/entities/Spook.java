package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.LoopingAnimation;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Spook extends TravelingSpriteEntity implements Collider {

    protected Bord bord;
    protected Direction huidigeRichting = Direction.LEFT;
    protected final int snelheid = 2;
    protected final Random random = new Random();

    public Spook(final String spritemap, final Coordinate2D location) {
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
    public abstract void onDistanceReached();
        /* deze later verwijderen
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
*/
    protected boolean nietTerug(Direction richting){
        switch (huidigeRichting) {
            case Direction.UP -> { return richting != Direction.DOWN;}
            case Direction.DOWN -> { return richting != Direction.UP;}
            case Direction.RIGHT -> { return richting != Direction.LEFT;}
            case Direction.LEFT -> { return richting != Direction.RIGHT;}
        }
        return true;
    }
    protected List<Direction> getMogelijkeRichtingen() {
        var locatie = new Locatie2D(getAnchorLocation()
                .add(new Coordinate2D(16, 16)));
        List<Direction> mogelijkeRichtingen = new ArrayList<>();
        for (Direction richting : new Direction[]{
                Direction.UP, Direction.DOWN,
                Direction.LEFT, Direction.RIGHT}) {
            if (!bord.heeftMuur(locatie, richting, true)
                    && nietTerug(richting)) {
                mogelijkeRichtingen.add(richting);
            }
        }
        return mogelijkeRichtingen;
    }
}