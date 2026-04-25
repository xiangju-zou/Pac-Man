package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class BordTegel extends SpriteEntity {

    public BordTegel(Coordinate2D initialLocation, Size size, TegelConfig config) {
        super("sprites/tilemap2.png", initialLocation, size, 9, 4);

        setCurrentFrameIndex(config.getTegelId());
    }
}
