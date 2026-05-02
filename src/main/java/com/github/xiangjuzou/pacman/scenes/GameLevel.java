package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.Fruit;
import com.github.xiangjuzou.pacman.entities.PacMan;
import com.github.xiangjuzou.pacman.entities.ValueEntity;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.timers.DelayedTimer;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import javafx.scene.paint.Color;

public class GameLevel extends DynamicScene implements TileMapContainer, TimerContainer, TimerCallback {
    private final PacManGame pacManGame;
    private final SoundClip startSound = new SoundClip("audio/pacman_beginning.mp3");
    private final int totaalAantalStippen = 244;  // inclusief 4 powerpellets

    public ValueEntity punten;
    public ValueEntity hogePunten;
    public ValueEntity leven;
    private Fruit fruit;

    private PacMan pacMan;
    private int aantalDotsGegeten;
    private SpookStatus spookStatus;
    private int aantalDodeSpoken;
    private int level;

    public GameLevel(PacManGame pacManGame) {
        this.pacManGame = pacManGame;
    }

    @Override
    public void setupScene() {
        aantalDotsGegeten = 0;
        aantalDodeSpoken = 0;
        setBackgroundColor(Color.BLACK);
        startSound.play();
        level = pacManGame.getLevel();
    }

    @Override
    public void setupEntities() {
        //addEntity(new Spook("pink", new Coordinate2D(30,18)));
        pacMan = new PacMan(PacMan.startLocatie, this, 3+level*0.5);
        leven = new ValueEntity(new Coordinate2D(925, getHeight() - 900), "Levens", 3);
        punten = new ValueEntity(new Coordinate2D(925, getHeight() - 800), "Punten", pacManGame.getPunten());
        hogePunten = new ValueEntity(new Coordinate2D(925, getHeight() - 700), "Hoogste", 0);
        fruit = new Fruit(new Coordinate2D(13*32-16, 17*32-16), level);
        fruit.setVisible(false);

        addEntity(fruit);
        addEntity(pacMan);
        addEntity(leven);
        addEntity(punten);
        addEntity(hogePunten);
    }

    // TileMap registreren
    @Override
    public void setupTileMaps() {
        addTileMap(new Bord());
    }

    @Override
    public double getWidth() {
        return 28*32; // TileMap wordt niet de gehele scherm
    }

    @Override
    public double getHeight() {
        return 31*32; //TileMap wordt niet de gehele scherm
    }

    @Override
    public void setupTimers() {
        // Het liedje wordt eerst afgespeeld en duurt 5 seconden
        var startTimer = new SingleTimer(0, 5000, this);
        addTimer(startTimer);

        var doodTimer = new DelayedTimer(1, 6000, this);
        addTimer(doodTimer);

        var fruitTimer = new DelayedTimer(2, 10000, this);
        addTimer(fruitTimer);
    }

    @Override
    public void onTimeReached(int id) {
        if (id == 0 ) {
            pacMan.start();
        }

        if (id == 1) {
            pacManGame.setActiveScene(2);
        }

        if (id == 2) {
            fruit.setVisible(false);
        }
    }

    public void gameOver(){
        this.getTimers().get(1).resume();
        pacManGame.resetLevel();
    }

    // todo: door te tunnel lopen met spook (spook gaat langzamer door de tunnel)  [would have?]
    public void processEvent(GameEvents event) {
        switch (event) {
            case DOTGEGETEN -> {
                punten.increase();
                aantalDotsGegeten++;

                if (aantalDotsGegeten == 170) {
                    fruit.setVisible(true);
                    getTimers().get(2).reset();
                    getTimers().get(2).resume();
                }
                if (aantalDotsGegeten == totaalAantalStippen) {
                    pacMan.nextLevel();
                    pacManGame.increaseLevel();
                    pacManGame.setPunten(punten.getValue());
                    pacManGame.setActiveScene(3);
                }
            }
            case POWERPELLETGEGETEN -> {
                aantalDotsGegeten++;
                spookStatus = SpookStatus.VLUCHTEN;
                punten.setValue(punten.getValue() + 10);
                // todo: vertel spookjes!
                // todo: timer
            }
            case SPOOKGEPAKT -> {
                spookStatus = SpookStatus.DOOD;
                aantalDodeSpoken++;
                punten.setValue(punten.getValue() + 100 * (int)Math.pow(2,aantalDodeSpoken));

                // todo: spookgeluidjes (in de scene, niet spook entity) op basis van spookStatus setSpookStatus()??
            }
            case SPOOKINHUIS -> {
                aantalDodeSpoken--;
                if (aantalDodeSpoken == 0) {
                    spookStatus = SpookStatus.VLUCHTEN;
                }
            }
            case FRUITGEGETEN -> {
                  punten.setValue(punten.getValue() + 100 * (int)Math.pow(2,level));
            }
            case PACMANGEPAKT -> {
                leven.decrease();
                pacMan.gaDood();
                if (leven.getValue() == 0) {
                    gameOver();
                }
            }
        }
    }
}
