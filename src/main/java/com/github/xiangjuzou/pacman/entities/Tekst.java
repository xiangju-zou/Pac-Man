package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.CustomFont;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;

public class Tekst extends TextEntity {
    public Tekst(Coordinate2D initialLocation, String text) {
        super(initialLocation, text);

        var customFont = new CustomFont("fonts/emulogic.ttf", 36);
        setFill(Color.WHITE);
        setFont(customFont);
    }
}
