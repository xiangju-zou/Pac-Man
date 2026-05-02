package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.Tekst;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import javafx.scene.paint.Color;

public class GameNextLevel extends DynamicScene implements TimerContainer, TimerCallback {
    private final PacManGame pacManGame;

    public GameNextLevel(PacManGame pacManGame) {
        this.pacManGame = pacManGame;
    }

    @Override
    public void setupTimers() {
        var timer = new SingleTimer(0, 3000, this);
        addTimer(timer);
    }

    @Override
    public void setupScene() {
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public void setupEntities() {
        var next = new Tekst(new Coordinate2D(450,450),"NEXT LEVEL");
        addEntity(next);
    }

    @Override
    public void onTimeReached(int id) {
        pacManGame.setActiveScene(1);

    }
}
