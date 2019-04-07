import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";

import { AppComponent } from './app.component';
import { SymbolsListComponent } from './symbols-list/symbols-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule, MatTableModule, MatGridListModule, MatSelectModule, MatCardModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    SymbolsListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    MatGridListModule,
    FlexLayoutModule,
    MatSelectModule,
    MatCardModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
