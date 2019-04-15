import {Component, OnInit} from '@angular/core';
import {Symbol} from "../shared/model/symbol.model";
import {Quote} from "../shared/model/quote.model";
import {SymbolsService} from "../shared/service/symbols.service";

@Component({
  selector: 'app-quotes',
  templateUrl: './quotes.component.html',
  styleUrls: ['./quotes.component.css']
})
export class QuotesComponent implements OnInit {

  selectedSymbol: Object;
  symbolsMap: Map<string, Array<string>>;
  quotePair: Symbol;
  symbol2: string;
  symbolToList: Array<string>;
  quote: Quote;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getSymbolsMap();
    this.quotePair = new Symbol("","");
    this.quote = new Quote();

  }

  getSymbolsMap() {
    this.symbolsService.getSymbolsMap().subscribe( data => {
      this.symbolsMap = data;
    });
  }

  submitQuote() {
    this.symbolsService.getQuote(this.quotePair)
      .subscribe(data => {
        this.quote = data;
      })
  }

  quotePair2Change(symbol2: string) {
    this.quotePair.symbol2 = symbol2;
  }

  quotePair1Change(selectedSymbol: Object) {
    this.quotePair.symbol1 = selectedSymbol["key"];
    this.symbolToList = selectedSymbol["value"];
  }

}
