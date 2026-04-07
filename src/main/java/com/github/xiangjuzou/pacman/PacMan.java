package com.github.xiangjuzou.pacman;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.xiangjuzou.pacman.scenes.GameStart;

public class PacMan extends YaegerGame {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Pac-Man");
        setSize(new Size(800, 600));
    }

    @Override
    public void setupScenes() {
        addScene(0, new GameStart(this));
    }
}

