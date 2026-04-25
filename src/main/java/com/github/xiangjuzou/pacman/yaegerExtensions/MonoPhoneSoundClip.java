package com.github.xiangjuzou.pacman.yaegerExtensions;
import com.github.hanyaeger.api.media.SoundClip;

/// A soundclip that can't start twice at the same time.
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
