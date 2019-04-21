import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {Router} from '@angular/router';

import {AppComponent} from './app.component';
import {SymbolsListComponent} from './symbols-list/symbols-list.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatGridListModule, MatInputModule,
  MatListModule,
  MatMenuModule,
  MatSelectModule,
  MatSidenavModule,
  MatTableModule,
  MatToolbarModule
} from '@angular/material';
import {FlexLayoutModule} from '@angular/flex-layout';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {QuotesComponent} from './quotes/quotes.component';
import {HomeComponent} from './home/home.component';
import {NavigationComponent} from './navigation/navigation.component';
import { ConvertCurrencyComponent } from './convert-currency/convert-currency.component';

@NgModule({
  declarations: [
    AppComponent,
    SymbolsListComponent,
    QuotesComponent,
    HomeComponent,
    NavigationComponent,
    ConvertCurrencyComponent
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
    MatToolbarModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(router: Router) {

  }
}
