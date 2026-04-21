package com.github.xiangjuzou.pacman.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.CustomFont;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;

public class ValueEntity extends TextEntity {

    private final String description;
    private int value;

    public int getValue() { return value;}
    public void setValue(int newValue) { value = newValue; updateText();}

    public ValueEntity(Coordinate2D initialLocation, String text, int initialValue) {
        super(initialLocation, text);

        var customFont = new CustomFont("fonts/emulogic.ttf", 24);
        setFill(Color.WHITE);
        setFont(customFont);
        value = initialValue;
        description = text;
        updateText();
    }

    public void increase() { value++; updateText();}
    public void decrease() { value--; updateText();}

    private void updateText() {
        setText(description + "\r\n" + value);
    }
}
