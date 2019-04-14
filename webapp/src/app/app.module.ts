import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from "@angular/common/http";
import {Router, RouterModule, Routes} from "@angular/router";

import { AppComponent } from './app.component';
import { SymbolsListComponent } from './symbols-list/symbols-list.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule, MatTableModule, MatGridListModule, MatSelectModule, MatCardModule,
  MatSidenavModule, MatMenuModule, MatListModule, MatDialogModule, MatToolbarModule
} from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule} from "@angular/forms";
import { AppRoutingModule} from "./app-routing.module";
import { QuotesComponent } from './quotes/quotes.component';
import { HomeComponent } from './home/home.component';
import { NavigationComponent } from './navigation/navigation.component';

@NgModule({
  declarations: [
    AppComponent,
    SymbolsListComponent,
    QuotesComponent,
    HomeComponent,
    NavigationComponent
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
    MatSidenavModule,
    MatMenuModule,
    MatListModule,
    FormsModule,
    AppRoutingModule,
    MatDialogModule,
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(router: Router) {

  }
}
