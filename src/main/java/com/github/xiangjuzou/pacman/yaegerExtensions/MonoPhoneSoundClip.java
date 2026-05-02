package com.github.xiangjuzou.pacman.yaegerExtensions;
import com.github.hanyaeger.api.media.SoundClip;

// Een soundclip die niet twee keer tegelijk kan worden afgespeeld
public class MonoPhoneSoundClip extends SoundClip {
    private boolean playing = false;

    public MonoPhoneSoundClip(String path) {
        super(path);
    }

    public MonoPhoneSoundClip(String path, int cycleCount) {
        super(path, cycleCount);
    }

    @Override
    public void play(){
        if (!playing) {
            super.play();
        }

        playing = true;
    }

    @Override
    public void stop() {
        playing = false;
        super.stop();
    }
}
