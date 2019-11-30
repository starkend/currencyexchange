import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SymbolsListComponent} from './symbols-list/symbols-list.component';
import {QuotesComponent} from './quotes/quotes.component';
import {HomeComponent} from './home/home.component';
import {ConvertCurrencyComponent} from './convert-currency/convert-currency.component';
import {RatesComponent} from './rates/rates.component';
import {HistoricalRatesComponent} from './historical-rates/historical-rates.component';

const appRoutes: Routes = [

  { path: 'symbols-list', component: SymbolsListComponent },
  { path: 'quotes', component: QuotesComponent },
  { path: 'convert-currency', component: ConvertCurrencyComponent },
  { path: 'rates', component: RatesComponent },
  { path: 'historical-rates', component: HistoricalRatesComponent },
  { path: 'home', component: HomeComponent },
  { path: '', component: HomeComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      {
        enableTracing: false // <-- debugging purposes only
      }
    )
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
