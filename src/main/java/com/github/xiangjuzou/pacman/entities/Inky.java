package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;

public class Inky extends Spook {
    public Inky (final Coordinate2D location) {
        super("sprites/spritemap.png", location);
    }

    @Override
    public void onDistanceReached() {
        if (bord == null) return;

        var mogelijkeRichtingen = getMogelijkeRichtingen();

        if (!mogelijkeRichtingen.isEmpty()) {
            huidigeRichting = mogelijkeRichtingen
                    .get(random.nextInt(mogelijkeRichtingen.size()));
            setMotion(snelheid, huidigeRichting);
        }
    }
}
