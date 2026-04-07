package com.github.xiangjuzou.pacman.entities.Buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import com.github.xiangjuzou.pacman.PacMan;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartButton extends TextEntity implements MouseButtonPressedListener, MouseEnterListener, MouseExitListener {
    private PacMan pacMan;

    public StartButton(Coordinate2D initialPosition, PacMan pacMan) {
        super(initialPosition, "Play game");
        this.pacMan = pacMan;
        setFill(Color.BLUE);
        setFont(Font.font("Roboto", FontWeight.BOLD, 30));
    }

    @Override
    public void onMouseButtonPressed(final MouseButton button, final Coordinate2D coordinate2D) {
        pacMan.setActiveScene(1);
    }

    @Override
    public void onMouseEntered() {
        setFill(Color.YELLOW);
        setCursor(Cursor.HAND);
    }

    @Override
    public void onMouseExited() {
        setFill(Color.BLUE);
        setCursor(Cursor.DEFAULT);
    }
}
