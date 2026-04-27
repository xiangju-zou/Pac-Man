package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.timers.SingleTimer;
import com.github.xiangjuzou.pacman.timers.TimerCallback;
import com.github.xiangjuzou.pacman.entities.Tekst;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

import java.util.Set;

public abstract class KeyboardScene extends DynamicScene implements KeyListener, TimerContainer, TimerCallback {
    private final PacManGame pacManGame;
    private final int sceneId;

    public KeyboardScene(PacManGame pacManGame, int sceneId) {
        this.pacManGame = pacManGame;
        this.sceneId = sceneId;
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.contains(KeyCode.SPACE)) {
            Platform.runLater(() -> {
                pacManGame.setActiveScene(1);
            });
        }
    }

    @Override
    public void setupTimers() {
        if (sceneId != 0) {
            var timeoutTimer = new SingleTimer(1, 3 * 60000, this);
            addTimer(timeoutTimer);
        }
    }

    @Override
    public void onTimeReached(int id) {
        pacManGame.setActiveScene(0);
    }

    @Override
    public void setupEntities() {
        // Voeg Start spel met spatiebalk toe
        var locatie = new Coordinate2D(350, getHeight() - 200);
        var startText = new Tekst(locatie,"Druk op spatiebalk");
        addEntity(startText);
    }
}