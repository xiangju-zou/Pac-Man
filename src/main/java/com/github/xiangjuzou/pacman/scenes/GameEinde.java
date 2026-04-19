package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.xiangjuzou.pacman.PacManGame;
import com.github.xiangjuzou.pacman.entities.Tekst;
import javafx.scene.paint.Color;

public class GameEinde extends KeyboardScene {

    public GameEinde(PacManGame pacManGame) {
        super(pacManGame,2);
    }

    @Override
    public void setupScene() {
        setBackgroundColor(Color.BLACK);
    }

    @Override
    public void setupEntities() {
        super.setupEntities();

        var puntenText = new Tekst(new Coordinate2D(getWidth() / 2 -175,  200), "High score");
        puntenText.setFill(Color.YELLOW);
        addEntity(puntenText);
    }
}