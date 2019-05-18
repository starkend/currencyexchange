import {Component, OnInit} from '@angular/core';
import {Symbol} from '../shared/model/symbol.model';
import {Quote} from '../shared/model/quote.model';
import {Rate} from '../shared/model/rate.model';
import {SymbolsService} from '../shared/service/symbols.service';

@Component({
  selector: 'app-quotes',
  templateUrl: './quotes.component.html',
  styleUrls: ['./quotes.component.css']
})
export class QuotesComponent implements OnInit {

  selectedSymbol: Symbol;
  symbolsMap: Map<string, Array<string>>;
  quotePair: Symbol;
  symbol2: string;
  symbolToList: Array<string>;
  quote: Quote;
  rate: Rate;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getSymbolsMap();
    this.quotePair = new Symbol('','');
    this.quote = new Quote();
    this.rate = new Rate();

  }

  getSymbolsMap() {
    this.symbolsService.getSymbolsMap().subscribe( data => {
      this.symbolsMap = data;
    });
  }

  submitQuote() {
    this.symbolsService.getRate(new Symbol(this.quotePair.symbol1, this.quotePair.symbol2))
      .subscribe(data => {
        this.rate = data;
      });
  }

  quotePair2Change(symbol2: string) {
    this.quotePair.symbol2 = symbol2;
  }

  quotePair1Change(selectedSymbol: object) {
    this.quotePair.symbol1 = selectedSymbol['key'];
    this.symbolToList = selectedSymbol['value'];
  }

}
