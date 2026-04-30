package com.github.xiangjuzou.pacman.scenes;
import com.github.xiangjuzou.pacman.entities.Spook;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.scenes.TileMapContainer;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.PacMan;
import com.github.xiangjuzou.pacman.entities.Spook;
import com.github.xiangjuzou.pacman.entities.ValueEntity;
import com.github.xiangjuzou.pacman.entities.maps.Bord;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import com.github.xiangjuzou.pacman.yaegerExtensions.MonoPhoneSoundClip;
import javafx.scene.paint.Color;


public class GameLevel extends DynamicScene implements TileMapContainer, TimerContainer, TimerCallback {
    private final PacManGame pacManGame;
    private final MonoPhoneSoundClip startSound = new MonoPhoneSoundClip("audio/pacman_beginning.mp3");
    private final int totaalAantalStippen = 244;  // inclusief 4 powerpellets

    public ValueEntity punten;
    public ValueEntity hogePunten;
    public ValueEntity leven;

    private PacMan pacMan;
    private int aantalDotsGegeten;
    private SpookStatus spookStatus;
    private int aantalDodeSpoken;
    private Spook spook;

    public GameLevel(PacManGame pacManGame) {
        this.pacManGame = pacManGame;
    }

    @Override
    public void setupScene() {
        aantalDotsGegeten = 0;
        aantalDodeSpoken = 0;
        setBackgroundColor(Color.BLACK);
        startSound.play();
    }

    @Override
    public void setupEntities() {
        //addEntity(new Ghost("pink", new Coordinate2D(30,18)));
        this.pacMan = new PacMan(new Coordinate2D(16,16), this, 3);

        leven = new ValueEntity(new Coordinate2D(925, getHeight() - 900), "Levens", 3);
        punten = new ValueEntity(new Coordinate2D(925, getHeight() - 800), "Punten", 0);
        hogePunten = new ValueEntity(new Coordinate2D(925, getHeight() - 700), "Hoogste", 0);

        addEntity(pacMan);
        spook = new Spook("pink", new Coordinate2D(13*32, 11*32));
        addEntity(spook);
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

        SingleTimer doodTimer = new SingleTimer(1, 6000, this);
        doodTimer.pause();
        addTimer(doodTimer);
    }

    @Override
    public void onTimeReached(int id) {
        if (id == 0 ) {
            pacMan.start();
            var bord = (Bord) getTileMaps().get(0);
            spook.start(bord);
        }

        if (id == 1) {
            pacManGame.setActiveScene(2);
        }
    }

    public void gameOver(){
        this.getTimers().get(1).resume();
    }

    // todo: door te tunnel lopen met pacman en spook (spook gaat langzamer door de tunnel)  [would have?]
    public void processEvent(GameEvents event){
        switch (event) {
            case DOTGEGETEN -> {
                punten.increase();
                aantalDotsGegeten++;
                // todo: fruit tonen als genoeg gegeten
                // todo: fruit entity maken (met collision, gaat zelf weg naar botsing)
                // todo: alle dots gegeten? Level gewonnen, da door naar nieuw levelm waarbij pacman en spook sneller gaan. (ook ander fruit met meer punten)
            }
            case POWERPELLETGEGEGETEN -> {
                spookStatus = SpookStatus.VLUCHTEN;
                punten.setValue(punten.getValue() + 10);
                // todo: vertel spookjes!
                // todo: timer
            }
            case SPOOKGEPAKT -> {
                spookStatus = SpookStatus.DOOD;
                aantalDodeSpoken++;
                // todo: spookgeluidjes (in de scene, niet spookentity) op basis van spookStatus setSpookStatus()??
            }
            case SPOOKINHUIS -> {
                aantalDodeSpoken--;
                if (aantalDodeSpoken == 0) {
                    spookStatus = SpookStatus.VLUCHTEN;
                }
            }
            case FRUITGEPAKT -> {
                // Krijg punten van fruit in level.
                // todo: elke level meer punten!
                // todo: levels bijhouden.
                punten.setValue(punten.getValue() + 200); // tijdelijk vast aantal van 200.
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
