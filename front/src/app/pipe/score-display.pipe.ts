// bonus-display.pipe.ts
import {Pipe, PipeTransform} from '@angular/core';
import {AfricanBowlingFrame} from "../model/africanBowlingFrame";

@Pipe({
  name: 'scoreDisplay'
})
export class ScoreDisplayPipe implements PipeTransform {
  transform(score: number | undefined, frame: AfricanBowlingFrame, index: number): string | undefined {
    if (frame.throwScores.reduce((acc, current) => acc + current, 0) === 15 && frame.throwScores.length === 1 && index === 1 ) {
      return 'X';
    } else if (frame.throwScores.reduce((acc, current) => acc + current, 0) === 15 && frame.throwScores.length === index ) {
      return '/';
    } else if (!!score || score == 0) {
      return score.toString();
    } else {
      return undefined;
    }
  }
}
