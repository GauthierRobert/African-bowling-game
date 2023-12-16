package com.telemis.africanbowlinggame.service;

import com.telemis.africanbowlinggame.model.AfricanBowlingGame;
import com.telemis.africanbowlinggame.model.GameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class GameServiceTest {

    @Autowired
    private GameService gameService;
    @Autowired
    private FrameService frameService;

    @BeforeEach
    public void setUp() {
        gameService.newGame();
    }

    @Test
    void lancer() {

        gameService.lancer(10);
        gameService.lancer(5);
        gameService.lancer(5);
        gameService.lancer(5);
        gameService.lancer(5);
        gameService.lancer(15);
        gameService.lancer(5);
        gameService.lancer(5);
        gameService.lancer(3);
        gameService.lancer(1);
        AfricanBowlingGame game = gameService.lancer(5);

        Assertions.assertThat(game.getFrames().size()).isEqualTo(5);
        Assertions.assertThat(game.getFrames().get(4).getThrowScores().size()).isEqualTo(2);
        Assertions.assertThat(game.getFrames().get(4).getThrowScores().get(0)).isEqualTo(1);
        Assertions.assertThat(game.getFrames().get(4).getThrowScores().get(1)).isEqualTo(5);
        Assertions.assertThat(game.getFrames().get(4).getBonusScores().size()).isEqualTo(0);
    }

    @Test
    void lancer_perfect_fullStrike() {
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        AfricanBowlingGame game = gameService.lancer(15);

        Assertions.assertThat(game.getFrames().size()).isEqualTo(5);
        Assertions.assertThat(game.getFrames().get(4).getThrowScores().get(0)).isEqualTo(15);
        Assertions.assertThat(game.getFrames().get(4).getBonusScores().get(0)).isEqualTo(15);
        Assertions.assertThat(game.getFrames().get(4).getBonusScores().get(1)).isEqualTo(15);
        Assertions.assertThat(game.getFrames().get(4).getBonusScores().get(2)).isEqualTo(15);

        Assertions.assertThat(game.getFrames().stream().map(africanBowlingFrame ->
                africanBowlingFrame.getThrowScores().stream().reduce(0, Integer::sum) +
                        africanBowlingFrame.getBonusScores().stream().reduce(0, Integer::sum))
                .reduce(0, Integer::sum)).isEqualTo(300);
    }

    @Test
    void lancer_Cheater_to_much_lancer() {
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);
        gameService.lancer(15);

        RuntimeException exception = assertThrows(GameException.class, () -> gameService.lancer(15));
        assertEquals("The game is over", exception.getMessage());
    }

    @Test
    void lancer_Cheater_higher_than_15() {
        RuntimeException exception = assertThrows(GameException.class, () -> gameService.lancer(16));
        assertEquals("The player is cheating. The number of pins in the frame exceeds the allowed maximum: 15. Game is restarted", exception.getMessage());
    }


    @Test
    void lancer_Cheater_higher_then_recovered() {
        gameService.lancer(15);
        gameService.lancer(11);

        RuntimeException exception = assertThrows(GameException.class, () -> gameService.lancer(6));
        assertEquals("The player is cheating. The number of pins in the frame exceeds the allowed maximum: 15. Game is restarted", exception.getMessage());

        AfricanBowlingGame game = gameService.lancer(3);

        Assertions.assertThat(game.getFrames().get(0).getThrowScores().size()).isEqualTo(1);
        Assertions.assertThat(game.getFrames().get(0).getBonusScores().size()).isEqualTo(0);

    }
}