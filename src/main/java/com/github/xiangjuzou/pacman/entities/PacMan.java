package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;
import com.github.xiangjuzou.pacman.entities.animaties.PacManAnimatie;

public class PacMan extends TravelingSpriteEntity implements AnimationCallback{
    private final PacManAnimatie Animaties = new PacManAnimatie(this);
    private final PacManGame game;

    public PacMan(final Coordinate2D location, PacManGame game){
        super("sprites/spritemap.png",location, new Size(64,64), 7,12);

        this.game = game;
        setAutoCycle(125);

       // setAnchorLocation(new Coordinate2D(16,16));
        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.STILSTAAN));
    }

    public void start() {
        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.RECHTS), true);
        setMotion(1, Direction.RIGHT);
    }

    @Override
    public void onDistanceReached() {

        if(getAnchorLocation().getY() > 144) {
            setSpeed(0);
            var geluidDood = new SoundClip("audio/pacman_death.mp3");
            geluidDood.play();
            playAnimation((Animaties.getAnimatie(PacManAnimatie.Soort.DOOD)));
            return;
        }

        System.out.println("LOC:" + this.getAnchorLocation().toString());
        if (getAnchorLocation().getX() == 16+64+64+32) {
            setDirection(Direction.DOWN);
            var animatieBeneden = new LoopingAnimation(1,6,1,7,250);
            playAnimation(animatieBeneden);
        }
    }

    @Override
    public void call() {
        System.out.println("DOODGEGAAN");
        game.setActiveScene(2);
    }
}
