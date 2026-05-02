package com.github.xiangjuzou.pacman.timers;

import com.github.hanyaeger.api.Timer;

public class DelayedTimer extends Timer {
    private final TimerCallback callback;
    private final int id;

    public DelayedTimer(int id, long intervalInMs, TimerCallback callback) {
        super(intervalInMs);

        this.id = id;
        this.callback = callback; // Callback is een interface met methode
        pause(); // Pauzeer de timer
    }

    @Override
    public void onAnimationUpdate(long timestamp) {
        callback.onTimeReached(id);
        pause();
    }
}
