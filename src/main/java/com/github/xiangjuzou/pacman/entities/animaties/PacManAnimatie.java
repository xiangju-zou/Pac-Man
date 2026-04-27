package com.github.xiangjuzou.pacman.entities.animaties;

import com.github.hanyaeger.api.entities.*;
import java.util.Map;

public class PacManAnimatie {

    private final Map<PacManAnimatieSoort, Animation> Animations;

    public PacManAnimatie(AnimationCallback callback) {
        Animations = Map.of(
                // Sprite wisselen van het plaatje binnen cycleTime
                PacManAnimatieSoort.DOOD, new FiniteAnimationWithCallBack(0, 0, 0, 13, 250, callback),
                // todo: we kunnen in 4 richtingen stilstaan!

                PacManAnimatieSoort.STILSTAAN, new LoopingAnimation(1, 0,1,0, 75),
                PacManAnimatieSoort.RECHTS, new LoopingAnimation(1, 0, 1, 1, 75),
                PacManAnimatieSoort.LINKS, new LoopingAnimation(1,2, 1, 3,75),
                PacManAnimatieSoort.BOVEN, new LoopingAnimation(1,4, 1, 5, 75),
                PacManAnimatieSoort.BENEDEN, new LoopingAnimation(1, 6, 1, 7, 75)
        );
    }

    public Animation getAnimatie(PacManAnimatieSoort soort) {
        return Animations.get(soort);
    }
}
