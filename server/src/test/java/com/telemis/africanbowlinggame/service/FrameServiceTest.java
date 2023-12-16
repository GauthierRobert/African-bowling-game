package com.telemis.africanbowlinggame.service;

import com.telemis.africanbowlinggame.model.AfricanBowlingFrame;
import com.telemis.africanbowlinggame.model.AfricanBowlingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameServiceTest {

    private FrameService frameService;

    @BeforeEach
    public void setUp() {
        frameService = new FrameService();
    }

    @Test
    public void hasIncompleteBonusScores_SPARE() {
        AfricanBowlingFrame frame = new AfricanBowlingFrame(List.of(3, 7, 5), List.of(5));

        assertThat(frameService.hasIncompleteBonusScores(frame)).isTrue();
    }

    @Test
    public void hasCompleteBonusScores_SPARE() {
        AfricanBowlingFrame frame = new AfricanBowlingFrame(List.of(3, 7, 5), List.of(5, 6));

        assertThat(frameService.hasIncompleteBonusScores(frame)).isFalse();
    }

    @Test
    public void hasIncompleteBonusScores_STRIKE() {
        AfricanBowlingFrame frame = new AfricanBowlingFrame(List.of(15), List.of(5));

        assertThat(frameService.hasIncompleteBonusScores(frame)).isTrue();
    }

    @Test
    public void hasCompleteBonusScores_STRIKE() {
        AfricanBowlingFrame frame = new AfricanBowlingFrame(List.of(15), List.of(5, 6, 1));

        assertThat(frameService.hasIncompleteBonusScores(frame)).isFalse();
    }

    @Test
    public void hasNoBonusScores() {
        AfricanBowlingFrame frame = new AfricanBowlingFrame(List.of(3, 7, 1), emptyList());

        // If not spare or strike. Empty bonus is considered complete
        assertThat(frameService.hasIncompleteBonusScores(frame)).isFalse();
    }

}