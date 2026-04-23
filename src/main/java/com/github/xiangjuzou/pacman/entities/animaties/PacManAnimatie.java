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
                Soort.DOOD, new FiniteAnimationWithCallBack(0,0,0,11, 125, callback),
                Soort.STILSTAAN, new LoopingAnimation(1, 0,1,0, 125),
                Soort.RECHTS, new LoopingAnimation(1, 0, 1, 1, 125),
                Soort.LINKS, new LoopingAnimation(1,2, 1,3,125),
                Soort.BOVEN, new LoopingAnimation(1,4, 1, 5, 125),
                Soort.BENEDEN, new LoopingAnimation(1, 6, 1, 7, 125)
        );
    }

    public Animation getAnimatie(Soort soort) {
        return Animations.get(soort);
    }
}
