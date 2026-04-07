package com.github.xiangjuzou.pacman.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.xiangjuzou.pacman.PacMan;
import com.github.xiangjuzou.pacman.entities.Buttons.StartButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameStart extends StaticScene {
    private PacMan pacMan;

    public GameStart(PacMan pacMan) {
        this.pacMan = pacMan; // fixed: match the field name
    }

    @Override
    public void setupScene() {
        //setBackgroundAudio("audio/ocean.mp3");
        //setBackgroundImage("backgrounds/background1.jpg");
    }

    @Override
    public void setupEntities() {
        // Game title text
        var pacManText = new TextEntity(
                new Coordinate2D(getWidth() / 2, getHeight() / 2),
                "Pac Man"
        );
        pacManText.setAnchorPoint(AnchorPoint.BOTTOM_CENTER);
        pacManText.setFill(Color.BLACK);
        pacManText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 56));
        addEntity( pacManText);

        // Start button
        var playGameText = new StartButton(
                new Coordinate2D(getWidth() / 2, getHeight() / 2),
                pacMan
        );
        playGameText.setAnchorPoint(AnchorPoint.TOP_CENTER);
        addEntity(playGameText);
    }
}