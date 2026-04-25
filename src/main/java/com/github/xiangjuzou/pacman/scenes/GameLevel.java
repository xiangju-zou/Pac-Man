package com.github.xiangjuzou.pacman.scenes;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.*;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import javafx.scene.paint.Color;


public class GameLevel extends DynamicScene implements TileMapContainer, TimerContainer, TimerCallback {
    public ValueEntity punten;
    public ValueEntity hogePunten;
    public ValueEntity leven;
    private final PacManGame pacManGame;
    private PacMan pacMan;


    public GameLevel(PacManGame pacManGame) {
        this.pacManGame = pacManGame;
    }

    @Override
    public void setupScene() {
        setBackgroundColor(Color.BLACK);
        var startSound = new SoundClip("audio/pacman_beginning.mp3");
        startSound.play();
    }

    @Override
    public void setupEntities() {
        //addEntity(new Ghost("pink", new Coordinate2D(30,18)));
        this.pacMan = new PacMan(new Coordinate2D(16,16), this);

        leven = new ValueEntity(new Coordinate2D(925, getHeight() - 900), "Levens", 3);
        punten = new ValueEntity(new Coordinate2D(925, getHeight() - 800), "Punten", 0);
        hogePunten = new ValueEntity(new Coordinate2D(925, getHeight() - 700), "Hoogste", 0);

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
        SingleTimer startTimer = new SingleTimer(0, 5000, this);
        addTimer(startTimer);

        SingleTimer doodTimer = new SingleTimer(1, 2000, this);
        doodTimer.pause();
        addTimer(doodTimer);
    }

    @Override
    public void onTimeReached(int id) {
        if (id == 0 ) {
            pacMan.start();
        }

        if (id == 1) {
            pacManGame.setActiveScene(2);
        }
    }

    public void gameOver(){
        this.getTimers().get(1).resume();
    }
}
