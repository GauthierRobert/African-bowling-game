package com.telemis.africanbowlinggame.model;

import java.util.List;

public class AfricanBowlingGame {

    private final List<AfricanBowlingFrame> frames;

    private AfricanBowlingGame(List<AfricanBowlingFrame> framesByStep) {
        this.frames = framesByStep;
    }

    public static AfricanBowlingGame createState() {
        return new AfricanBowlingGame(List.of(new AfricanBowlingFrame(),
                new AfricanBowlingFrame(), new AfricanBowlingFrame(),
                new AfricanBowlingFrame(), new AfricanBowlingFrame()));
    }


    public List<AfricanBowlingFrame> getFrames() {
        return frames;
    }
}
