package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;
import com.github.xiangjuzou.pacman.scenes.GameLevel;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;
import com.github.xiangjuzou.pacman.entities.animaties.PacManAnimatie;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class PacMan extends TravelingSpriteEntity implements AnimationCallback, KeyListener {
    private final PacManAnimatie Animaties = new PacManAnimatie(this);
    private final GameLevel scene;
    private KeyCode LaatsteCommando = null;
    private boolean isBegonnen = false;
    private Bord bord;
    private int snelheid;

    public PacMan(final Coordinate2D location, GameLevel scene ) {
        super("sprites/spritemap.png", location, new Size(64,64), 7,14);

        this.scene = scene;
        snelheid = 3;

        setAutoCycle(125);
        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.STILSTAAN));
    }

    public void start() {
        isBegonnen = true;

        bord = (Bord)scene.getTileMaps().get(0);

        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.RECHTS), true);
        setMotion(snelheid, Direction.RIGHT);
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        for (var key : pressedKeys) {
            LaatsteCommando = key;
            return;
        }
    }

    // Deze callback gaat af bij elke 32 pixels (2 stappen op de kaart) en als je stil staat
    @Override
    public void onDistanceReached() {
        if (!isBegonnen) {
            return;
        }
        var pacmanTileCoordinate = getAnchorLocation().add(new Coordinate2D(16,16));
        var locatie = new Locatie2D(pacmanTileCoordinate);

        // Eet stip
        var y = locatie.getY();
        var x = locatie.getX();
        if (bord.getInstanceMap()[y][x] != null) {
            bord.getInstanceMap()[y][x].remove();
            scene.punten.increase();
        }

        // controleer muur
        var heeftMuur = bord.heeftMuur(locatie, Direction.valueOf(getDirection()), false);
        if (heeftMuur) {
            playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.STILSTAAN));
            setSpeed(0);
        }

        if (LaatsteCommando != null) {
            switch (LaatsteCommando) {
                case UP -> {
                    if (!bord.heeftMuur(locatie, Direction.UP, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.BOVEN), true);
                        setDirection((Direction.UP));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case DOWN -> {
                    if (!bord.heeftMuur(locatie, Direction.DOWN, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.BENEDEN), true);
                        setDirection((Direction.DOWN));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case RIGHT -> {
                    if (!bord.heeftMuur(locatie, Direction.RIGHT, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.RECHTS), true);
                        setDirection((Direction.RIGHT));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case LEFT -> {
                    if (!bord.heeftMuur(locatie, Direction.LEFT, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatie.Soort.LINKS), true);
                        setDirection((Direction.LEFT));
                        setSpeed(snelheid);
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
        }
    }

    @Override
    public void call() {
        System.out.println("DOODGEGAAN");
        scene.gameOver();
    }
}
