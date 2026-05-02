package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.xiangjuzou.pacman.entities.animaties.PacManAnimatie;
import com.github.xiangjuzou.pacman.entities.animaties.PacManAnimatieSoort;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.maps.Gegeten;
import com.github.xiangjuzou.pacman.entities.maps.Locatie2D;
import com.github.xiangjuzou.pacman.scenes.GameEvents;
import com.github.xiangjuzou.pacman.scenes.GameLevel;
import com.github.xiangjuzou.pacman.yaegerExtensions.MonoPhoneSoundClip;
import com.github.xiangjuzou.pacman.yaegerExtensions.TravelingSpriteEntity;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Set;

public class PacMan extends TravelingSpriteEntity implements AnimationCallback, KeyListener, Collided {
    private final PacManAnimatie Animaties = new PacManAnimatie(this);

    private final GameLevel scene;
    private final double snelheid;
    private final MonoPhoneSoundClip chomp = new MonoPhoneSoundClip("audio/pacman_chomp.wav", SoundClip.INDEFINITE);

    private KeyCode LaatsteCommando = null;
    private boolean isBegonnen = false;
    private Bord bord;
    public static Coordinate2D startLocatie = new Coordinate2D(13*32-16, 23*32-16);

    public PacMan(final Coordinate2D location, GameLevel scene, double snelheid) {
        super("sprites/spritemap.png", location, new Size(64, 64), 7, 14);

        this.scene = scene;
        this.snelheid = snelheid;
        setAutoCycle(125);
        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.STILSTAANLINKS));
    }

    public void start() {
        isBegonnen = true;

        setAnchorLocation(PacMan.startLocatie);
        bord = (Bord)scene.getTileMaps().get(0);
        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.LINKS), true);
        setMotion(snelheid, Direction.LEFT);
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
        super.onDistanceReached();

        if (!isBegonnen) {
            return;
        }

        var pacmanTileCoordinate = getAnchorLocation().add(new Coordinate2D(16, 16));
        var locatie = new Locatie2D(pacmanTileCoordinate);

        verwerkEten(locatie);    // Eet Dot of POWERPELLET
        controleerMuur(locatie); // controleer muur
        verwerkCommando(locatie);  // verwerkCommando(locatie);

         // voorbeeld doodgaan
//       if(getAnchorLocation().getY() > 344 && getSpeed() > 0) {
//          gaDood();
//       }
    }

    @Override
    public void call() {
        // Dit is de callback van de "gaDood"  animatie.
        //todo: op startpunt opnieuw beginnen.
    }

    public void nextLevel(){
        chomp.stop();
    }

    public void gaDood() {
        setSpeed(0);
        chomp.stop();
        var geluidDood = new SoundClip("audio/pacman_death.mp3");
        geluidDood.play();
        playAnimation((Animaties.getAnimatie(PacManAnimatieSoort.DOOD)));
    }

    // Eet dot of powerpellet, verwerken punten, leven enz
    private void verwerkEten(Locatie2D locatie) {
        var gegeten = bord.eetStip(locatie);

        if (gegeten == Gegeten.DOT) {
            chomp.play();
            scene.processEvent(GameEvents.DOTGEGETEN);
        }
        else if (gegeten == Gegeten.POWERPELLET) {
            chomp.play();
            scene.processEvent(GameEvents.POWERPELLETGEGETEN);
        }
        else {
            chomp.stop();
        }
    }

    @Override
    public void onCollision(List<Collider> colliders) {
        // Controleer of het fruit in de colliders zit
        for (var collider : colliders) {
            if ((collider instanceof Fruit fruit) && (fruit.isVisible())) {
                // Eat fruit
                var eatFruit = new SoundClip("audio/pacman_eatfruit.mp3");
                eatFruit.play();
                scene.processEvent(GameEvents.FRUITGEGETEN);
                fruit.remove();
            }
        }
    }

    // Tegen een muur, stop beweging en gebruik eerste animatie-sprite
    private void controleerMuur(Locatie2D locatie) {
        var heeftMuur = bord.heeftMuur(locatie, Direction.valueOf(getDirection()), false);

        if (heeftMuur) {
            switch (Direction.valueOf(getDirection())) {
                case Direction.LEFT -> playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.STILSTAANLINKS));
                case Direction.RIGHT -> playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.STILSTAANRECHTS));
                case Direction.DOWN -> playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.STILSTAANBENEDEN));
                case Direction.UP -> playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.STILSTAANBOVEN));
            }
            setSpeed(0);
        }
    }

    // Een nieuwe richting ingedrukt, en mag Pac-Man daarheen bewegen
    private void verwerkCommando(Locatie2D locatie) {
        if (LaatsteCommando != null) {
            switch (LaatsteCommando) {
                case UP -> {
                    if (!bord.heeftMuur(locatie, Direction.UP, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.BOVEN), true);
                        setDirection((Direction.UP));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case DOWN -> {
                    if (!bord.heeftMuur(locatie, Direction.DOWN, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.BENEDEN), true);
                        setDirection((Direction.DOWN));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case RIGHT -> {
                    if (!bord.heeftMuur(locatie, Direction.RIGHT, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.RECHTS), true);
                        setDirection((Direction.RIGHT));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
                case LEFT -> {
                    if (!bord.heeftMuur(locatie, Direction.LEFT, false)) {
                        playAnimation(Animaties.getAnimatie(PacManAnimatieSoort.LINKS), true);
                        setDirection((Direction.LEFT));
                        setSpeed(snelheid);
                        LaatsteCommando = null;
                    }
                }
            }
        }
    }
}
