package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class BordTegel extends SpriteEntity {

    public BordTegel(Coordinate2D initialLocation, Size size, TegelConfig config) {
        super("sprites/tilemap.png", initialLocation, size, 4, 5);

        setCurrentFrameIndex(config.getTegelId());
    }
}
