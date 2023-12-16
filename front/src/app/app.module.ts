import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BowlingGameComponent} from './bowling-game/bowling-game.component';
import {GameApiService} from "./api/game-api.service";
import {HttpClientModule} from "@angular/common/http";
import {ScoreDisplayPipe} from "./pipe/score-display.pipe";

@NgModule({
  declarations: [
    AppComponent,
    BowlingGameComponent,
    ScoreDisplayPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [GameApiService, ScoreDisplayPipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
