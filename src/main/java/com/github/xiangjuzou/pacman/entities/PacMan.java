package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;
import com.github.xiangjuzou.pacman.scenes.GameLevel;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;
import com.github.xiangjuzou.pacman.entities.animaties.PacManAnimatie;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class PacMan extends TravelingSpriteEntity implements AnimationCallback, KeyListener {
    private final PacManAnimatie Animaties = new PacManAnimatie(this);
    private final GameLevel scene;
    private KeyCode LaatsteCommando = null;

    public PacMan(final Coordinate2D location, GameLevel scene ) {
        super("sprites/spritemap.png",location, new Size(64,64), 7,12);

        this.scene = scene;

        setAutoCycle(125);
       // setAnchorLocation(new Coordinate2D(16,16));
        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.STILSTAAN));
    }

    public void start() {
        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.RECHTS), true);
        setMotion(2, Direction.RIGHT);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        for (var key : pressedKeys) {
            LaatsteCommando = key;
            return;
        }
    }

    @Override
    public void onDistanceReached() {
        scene.punten.increase();

        // controleer muur
        var bord = (Bord)scene.getTileMaps().get(0);
        var huidigeLocatie = new Locatie2D(getAnchorLocation());
        //System.out.println(huidigeLocatie.getX() + "," + huidigeLocatie.getY() + " == " + getAnchorLocation().toString());
        var heeftMuur = bord.heeftMuur(huidigeLocatie, Direction.valueOf(getDirection()));
        if (heeftMuur) {
            setSpeed(0);
        }

        if (LaatsteCommando != null) {
            switch (LaatsteCommando) {
                case UP -> {
                    if (!bord.heeftMuur(huidigeLocatie, Direction.UP)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.BOVEN), true);
                        setDirection((Direction.UP));
                        setSpeed(2);
                        LaatsteCommando = null;
                    }
                }
                case DOWN -> {
                    if (!bord.heeftMuur(huidigeLocatie, Direction.DOWN)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.BENEDEN), true);
                        setDirection((Direction.DOWN));
                        setSpeed(2);
                        LaatsteCommando = null;
                    }
                }
                case RIGHT -> {
                    if (!bord.heeftMuur(huidigeLocatie, Direction.RIGHT)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.RECHTS), true);
                        setDirection((Direction.RIGHT));
                        setSpeed(2);
                        LaatsteCommando = null;
                    }
                }
                case LEFT -> {
                    if (!bord.heeftMuur(huidigeLocatie, Direction.LEFT)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.LINKS), true);
                        setDirection((Direction.LEFT));
                        setSpeed(2);
                        LaatsteCommando = null;
                    }
                }
            }
        }

// voorbeeld doodgaan
        if(getAnchorLocation().getY() > 344 && getSpeed() > 0) {
            scene.leven.setValue(0);
            setSpeed(0);
            var geluidDood = new SoundClip("audio/pacman_death.mp3");
            geluidDood.play();
            playAnimation((Animaties.getAnimatie(PacManAnimatie.Soort.DOOD)));
            return;
        }

//        if (getAnchorLocation().getX() == 16+64+64+32) {
//            setDirection(Direction.DOWN);
//            var animatieBeneden = new LoopingAnimation(1,6,1,7,250);
//            playAnimation(animatieBeneden);
//        }
     //   System.out.println("LOC:" + this.getAnchorLocation().toString());
    }

    @Override
    public void call() {
        System.out.println("DOODGEGAAN");
        scene.gameOver();
    }
}
