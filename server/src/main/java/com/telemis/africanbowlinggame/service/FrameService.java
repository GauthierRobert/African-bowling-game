package com.telemis.africanbowlinggame.service;

import com.telemis.africanbowlinggame.model.AfricanBowlingFrame;
import org.springframework.stereotype.Service;

@Service
public class FrameService {

    public static final int MAX_PINS = 15;

    public boolean hasIncompleteBonusScores(AfricanBowlingFrame frame) {
        return (isSpare(frame) && frame.getBonusScores().size() < 2) ||
                (isStrike(frame) && frame.getBonusScores().size() < 3);
    }

    public boolean hasIncompleteThrowScores(AfricanBowlingFrame frame) {
        return !(frame.getThrowScores().size() == 3 || (isStrike(frame)) ||
                (isSpare(frame)));
    }

    private boolean isSpare(AfricanBowlingFrame frame) {
        return (frame.getThrowScores().size() == 2 || frame.getThrowScores().size() == 3) &&
                frame.getThrowScores().stream().reduce(0, Integer::sum).equals(MAX_PINS);
    }

    private boolean isStrike(AfricanBowlingFrame frame) {
        return frame.getThrowScores().size() == 1 &&
                frame.getThrowScores().stream().reduce(0, Integer::sum).equals(MAX_PINS);
    }
}
