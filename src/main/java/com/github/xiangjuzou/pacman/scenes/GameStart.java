package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.Plaatje;
import javafx.scene.paint.Color;

public class GameStart extends KeyboardScene {

    public GameStart(PacManGame pacManGame) {
        super(pacManGame,0);
    }

    @Override
    public void setupScene() {
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public void setupEntities(){
        super.setupEntities();

        var logoLocatie = new Coordinate2D(getWidth() / 2 - 1017/2D, getHeight() / 2-200);
        var logo = new Plaatje("sprites/logo.png", logoLocatie);
        addEntity(logo);
    }
}