import { Component, OnInit } from '@angular/core';
import { SymbolsService} from "../shared/service/symbols.service";
import { Symbol} from "../shared/model/symbol.model";
import {Quote} from "../shared/model/quote.model";

@Component({
  selector: 'app-symbols-list',
  templateUrl: './symbols-list.component.html',
  styleUrls: ['./symbols-list.component.css']
})
export class SymbolsListComponent implements OnInit {
  symbols: Array<any>;
  symbolsMap: Map<string, Array<string>>;
  savedSymbols: Array<any>;
  displayedColumns: Array<string>;
  savedSymbolColumns: Array<string>;
  symbolMapColumns: Array<string>;
  selectedSymbol: Object;
  quotePair: Symbol;
  symbol2: string;
  symbolToList: Array<string>;
  quote: Quote;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getSavedSymbols();
    this.getAllSymbols();
    this.getSymbolsMap();
    this.displayedColumns = ['symbol1', 'symbol2', 'addButton'];
    this.savedSymbolColumns = ['savedSymbol1', 'savedSymbol2'];
    this.symbolMapColumns = ['fromSymbol', 'toSymbol'];
    this.quotePair = new Symbol("","");
    this.quote = new Quote();
  }

  getAllSymbols() {
    this.symbolsService.getAllSymbols().subscribe( data => {
      this.symbols = data;
    });
  }

  getSymbolsMap() {
    this.symbolsService.getSymbolsMap().subscribe( data => {
       this.symbolsMap = data;
    });
  }

  getSavedSymbols() {
    this.symbolsService.getSavedSymbols().subscribe(data => {
      this.savedSymbols = data;
    });
  }

  addSymbol(symbol: Symbol) {
    this.symbolsService.addSymbol(symbol)
      .subscribe(()=> {
        this.getSavedSymbols();
      });
  }

  submitQuote() {
    console.log("*****************" + this.quotePair === undefined ? "__" : this.quotePair );
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
