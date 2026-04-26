package com.github.xiangjuzou.pacman.entities.maps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.LoopingAnimation;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.xiangjuzou.pacman.scenes.GameLevel;

public class PowerPelletTegel extends DynamicSpriteEntity {

    public PowerPelletTegel(Coordinate2D location, Size size) {
        super("sprites/dotmap.png", location, size, 1, 3);

        setAutoCycle(500);
        var knipperen = new LoopingAnimation(0, 0, 0, 1, 500);
        playAnimation(knipperen);
    }
}



