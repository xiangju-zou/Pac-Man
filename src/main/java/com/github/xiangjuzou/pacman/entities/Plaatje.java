package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Plaatje extends SpriteEntity {

    public Plaatje(String resource, Coordinate2D initialLocation) {
        super(resource, initialLocation);
    }

    public Plaatje(String resource, Coordinate2D initialLocation, Size size) {
        super(resource, initialLocation, size);
    }
}
