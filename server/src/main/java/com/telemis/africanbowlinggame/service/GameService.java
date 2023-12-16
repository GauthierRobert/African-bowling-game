package com.telemis.africanbowlinggame.service;

import com.telemis.africanbowlinggame.model.AfricanBowlingFrame;
import com.telemis.africanbowlinggame.model.AfricanBowlingGame;
import com.telemis.africanbowlinggame.model.GameException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.telemis.africanbowlinggame.model.AfricanBowlingGame.createState;
import static com.telemis.africanbowlinggame.service.FrameService.MAX_PINS;

@Service
public class GameService {

    public static final int MAX_FRAMES = 5;
    private final FrameService frameService;

    private AfricanBowlingGame state = createState();

    public GameService(FrameService frameService) {
        this.frameService = frameService;
    }

    public AfricanBowlingGame newGame() {
        state = createState();
        return state;
    }

    /**
     * @param fallenPins number of fallen pins for this throw
     * @return game status based on previous stored status
     */
    public AfricanBowlingGame lancer(int fallenPins) {
        if (isGameOver()) {
            throw new GameException("The game is over");
        }

        for (AfricanBowlingFrame frame : state.getFrames()) {
            if (frameService.hasIncompleteBonusScores(frame)) {
                frame.getBonusScores().add(fallenPins);
            }
            if (frameService.hasIncompleteThrowScores(frame)) {
                checkFallenPinsIsAnAuthorizedNumber(frame.getThrowScores(), fallenPins);
                frame.getThrowScores().add(fallenPins);
                break;
            }
        }
        return state;
    }

    private void checkFallenPinsIsAnAuthorizedNumber(List<Integer> currentScores, int fallenPins) {
        if (currentScores.stream().reduce(0, Integer::sum) + fallenPins > MAX_PINS) {
            newGame();
            throw new GameException("The player is cheating. The number of pins in the frame exceeds the allowed maximum: " + MAX_PINS + ". Game is restarted");
        }
    }

    private boolean isGameOver() {
        AfricanBowlingFrame lastFrame = state.getFrames().get(MAX_FRAMES - 1);
        return !frameService.hasIncompleteThrowScores(lastFrame) && !frameService.hasIncompleteBonusScores(lastFrame);
    }
}
