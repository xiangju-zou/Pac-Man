package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

public class Fruit extends DynamicSpriteEntity implements Collider {

    public Fruit(final Coordinate2D location, int level) {
        super("sprites/spritemap.png", location, new Size(64, 64), 7, 14);

        setCurrentFrameIndex((int)Math.min(27+level, 27+8));
    }
}