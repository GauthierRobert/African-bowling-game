package com.telemis.africanbowlinggame.api;

import com.telemis.africanbowlinggame.model.AfricanBowlingGame;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Validated
public interface GamesApi {

    @Operation(summary = "Update the game based on throw value")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AfricanBowlingGame.class))),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))})
    @RequestMapping(value = "/games",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<AfricanBowlingGame> lancer(@Parameter(in = ParameterIn.DEFAULT, description = "fallenPins",
            schema = @Schema()) @Valid @RequestBody Integer body);


    @Operation(summary = "Create a new Game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response")})
    @RequestMapping(value = "/games",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<AfricanBowlingGame> createGame();

}