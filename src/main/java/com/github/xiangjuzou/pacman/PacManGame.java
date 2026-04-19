package com.github.xiangjuzou.pacman;

import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;
import com.github.xiangjuzou.pacman.scenes.GameLevel;
import com.github.xiangjuzou.pacman.scenes.GameEinde;
import com.github.xiangjuzou.pacman.scenes.GameStart;


public class PacManGame extends YaegerGame {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setupGame() {
        setGameTitle("Pac-Man");
        setSize(new Size(56*16*1.5, 62*16));    // 1344 x 992 (speelveld 896 x 992, score veld 448 x 992)
    }

    @Override
    public void setupScenes() {
       addScene(0, new GameStart(this));
       addScene(1, new GameLevel(this));
       addScene(2, new GameEinde(this));
    }
}


