package com.telemis.africanbowlinggame.model;

import java.util.ArrayList;
import java.util.List;

public class AfricanBowlingFrame {

    List<Integer> throwScores = new ArrayList<>();
    List<Integer> bonusScores = new ArrayList<>();

    public AfricanBowlingFrame() {
    }

    public AfricanBowlingFrame(List<Integer> throwScores, List<Integer> bonusScores) {
        this.throwScores = throwScores;
        this.bonusScores = bonusScores;
    }

    public List<Integer> getThrowScores() {
        return throwScores;
    }

    public List<Integer> getBonusScores() {
        return bonusScores;
    }

}
