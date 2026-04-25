package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class DotTegel extends SpriteEntity {

    public DotTegel(Coordinate2D location, Size size) {
        super("sprites/dotmap.png", location, size, 1, 3);
        setCurrentFrameIndex(2);
    }
}






