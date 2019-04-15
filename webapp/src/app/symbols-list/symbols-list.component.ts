import {Component, OnInit} from '@angular/core';
import {SymbolsService} from "../shared/service/symbols.service";
import {Symbol} from "../shared/model/symbol.model";

@Component({
  selector: 'app-symbols-list',
  templateUrl: './symbols-list.component.html',
  styleUrls: ['./symbols-list.component.css']
})
export class SymbolsListComponent implements OnInit {
  symbols: Array<any>;

  savedSymbols: Array<any>;
  displayedColumns: Array<string>;
  savedSymbolColumns: Array<string>;


  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getSavedSymbols();
    this.getAllSymbols();
    this.displayedColumns = ['symbol1', 'symbol2', 'addButton'];
    this.savedSymbolColumns = ['savedSymbol1', 'savedSymbol2'];
  }

  getAllSymbols() {
    this.symbolsService.getAllSymbols().subscribe( data => {
      this.symbols = data;
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
}
