package com.github.xiangjuzou.pacman.entities.animaties;

import com.github.hanyaeger.api.entities.*;
import java.util.Map;

public class PacManAnimatie {

    public enum Soort {
        DOOD,
        STILSTAAN,
        RECHTS,
        LINKS,
        BOVEN,
        BENEDEN
    }

    private final Map<Soort, Animation> Animations;

    public PacManAnimatie(AnimationCallback callback) {
        Animations = Map.of(
                // Sprite wisselen van het plaatje binnen cycleTime
                Soort.DOOD, new FiniteAnimationWithCallBack(0,0,0,13, 250, callback),
                // todo: we kunnen in 4 richtingen stilstaan!
                Soort.STILSTAAN, new LoopingAnimation(1, 0,1,0, 75),
                Soort.RECHTS, new LoopingAnimation(1, 0, 1, 1, 75),
                Soort.LINKS, new LoopingAnimation(1,2, 1,3,75),
                Soort.BOVEN, new LoopingAnimation(1,4, 1, 5, 75),
                Soort.BENEDEN, new LoopingAnimation(1, 6, 1, 7, 75)
        );
    }

    public Animation getAnimatie(Soort soort) {
        return Animations.get(soort);
    }
}
