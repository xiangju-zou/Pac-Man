package com.github.xiangjuzou.pacman.scenes;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.Plaatje;
import com.github.xiangjuzou.pacman.entities.Tekst;
import com.github.xiangjuzou.pacman.entities.ValueEntity;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.entities.PacMan;
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
        this.pacMan = new PacMan(new Coordinate2D(16,16), pacManGame, this);

        leven = new ValueEntity(new Coordinate2D(925, getHeight() - 900), "Levens", 3);
        punten = new ValueEntity(new Coordinate2D(925, getHeight() - 800), "Punten", 0);
        hogePunten = new ValueEntity(new Coordinate2D(925, getHeight() - 700), "Hoogste", 0);

        addEntity(pacMan);

        addEntity(leven);
        addEntity(punten);
        addEntity(hogePunten);
    }

    @Override
    public void setupTileMaps() {
        addTileMap(new Bord());
    }

    @Override
    public double getWidth() {
        return 56*16;
    }

    @Override
    public double getHeight() {
        return 62*16;
    }

    @Override
    public void setupTimers() {
        addTimer((new SingleTimer(0, 5000, this)));
    }

    @Override
    public void onTimeReached(int id) {
        pacMan.start();
    }
}
