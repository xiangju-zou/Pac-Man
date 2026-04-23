package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.LoopingAnimation;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;


public class Spook extends TravelingSpriteEntity {

    public Spook(final String color, final Coordinate2D location) {
        super("sprites/spritemap.png", location, 7,12);

        setAutoCycle(250);

        var animation = new LoopingAnimation(4,0, 4, 1, 250); // <- getallen kloppen niet!
        this.playAnimation(animation);
    }
}
