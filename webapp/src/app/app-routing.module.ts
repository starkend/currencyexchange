import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SymbolsListComponent} from "./symbols-list/symbols-list.component";
import {QuotesComponent} from "./quotes/quotes.component";
import {HomeComponent} from "./home/home.component";
import {NavigationComponent} from "./navigation/navigation.component";

const appRoutes: Routes = [

  { path: 'symbols-list', component: SymbolsListComponent },
  { path: 'quotes', component: QuotesComponent },
  { path: 'home', component: HomeComponent }
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
