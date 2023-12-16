package com.telemis.africanbowlinggame.api;

import com.telemis.africanbowlinggame.model.AfricanBowlingGame;
import com.telemis.africanbowlinggame.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;


@RestController
public class GamesApiImpl implements GamesApi {

    private final GameService gameService;

    public GamesApiImpl(GameService gameService) {
        this.gameService = gameService;
    }

    @Override

    public ResponseEntity<AfricanBowlingGame> lancer(Integer body) {
        return ok(gameService.lancer(body));
    }

    @Override
    public ResponseEntity<AfricanBowlingGame> createGame() {
        return ok(gameService.newGame());
    }
}
