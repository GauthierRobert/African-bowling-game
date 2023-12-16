import {Component, OnInit} from '@angular/core';
import {GameApiService} from "../api/game-api.service";
import {AfricanBowlingGame} from "../model/africanBowlingGame";
import {AfricanBowlingFrame} from "../model/africanBowlingFrame";

@Component({
  selector: 'app-bowling-game',
  templateUrl: './bowling-game.component.html',
  styleUrls: ['./bowling-game.component.scss'],
})
export class BowlingGameComponent implements OnInit {

  game: AfricanBowlingGame | undefined;

  constructor(private readonly api: GameApiService) {

  }

  ngOnInit(): void {
    this.api.startNewGame().subscribe((value: AfricanBowlingGame) => {
      this.game = value;
    }, ((error: any) => alert(error.error)));
  }

  lancer(): void {
    if (!!this.game) {
      const pinsStillUp = this.getPinsStillUp(this.game);
      let pinsDown = this.generateRandomNumber(pinsStillUp);
      this.api.lancer(pinsDown).subscribe((value: AfricanBowlingGame) => {
        this.game = value;
      }, ((error: any) => {
        alert(error.error)
      }));
    }
  }

  private getPinsStillUp(game: AfricanBowlingGame): number {
    let currentFrame = game.frames.find(
      (value) =>
        value.throwScores.length !== 0 &&
        value.throwScores.length < 3 &&
        value.throwScores.reduce((acc, current) => acc + current, 0) < 15);
    if (!!currentFrame) {
      return 15 - currentFrame.throwScores.reduce((acc, current) => acc + current, 0);
    }
    let lastFrame = game.frames[4];
    if (lastFrame.throwScores.reduce((acc, current) => acc + current, 0) === 15 || lastFrame.throwScores.length === 3) {
      if (lastFrame.bonusScores[0] == 15 || lastFrame.bonusScores[1] == 15 || lastFrame.bonusScores.reduce((acc, current) => acc + current, 0) === 15) {
        return 15;
      }
      return 15 - lastFrame.bonusScores.reduce((acc, current) => acc + current, 0);
    }
    return 15;
  }

  generateRandomNumber(max: number): number {
    return Math.floor(Math.random() * (max + 1));
  }

  getTotalScore() {
    if (!!this.game) {
      return this.game.frames.map(frame => this.getFrameScore(frame)).reduce((acc, current) => acc + current, 0);
    }
    return 0;

  }

  getFrameScore(frame: AfricanBowlingFrame): number {
    return frame.throwScores.reduce((acc, current) => acc + current, 0) + frame.bonusScores.reduce((acc, current) => acc + current, 0);
  }
}
