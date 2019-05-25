import {Component, OnInit} from '@angular/core';
import {Symbol} from '../shared/model/symbol.model';
import {SymbolsService} from '../shared/service/symbols.service';
import {ConvertCurrency} from '../shared/model/convert-currency.model';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-convert-currency',
  templateUrl: './convert-currency.component.html',
  styleUrls: ['./convert-currency.component.css']
})

export class ConvertCurrencyComponent implements OnInit {

  selectedSymbol: Symbol;
  symbolsMap: Map<string, Array<string>>;
  convertPair: ConvertCurrency;

  symbol2: string;
  symbolToList: Array<string>;

  convertedRate: string;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getSymbolsMap();
    this.convertPair = new ConvertCurrency('','',0);
    this.convertedRate = '';
  }

  getSymbolsMap() {
    this.symbolsService.getSymbolsMap().subscribe( data => {
      this.symbolsMap = data;
    });
  }

  submitCurrencyConversion() {
    this.symbolsService.getCurrencyConversion(this.convertPair)
      .subscribe(data => {
        this.convertedRate = data;
      });
  }

  currencyPair2Change(toCurrency: string) {
    this.convertPair.toCurrency = toCurrency;
  }

  currencyPair1Change(selectedSymbol: object) {
    this.convertPair.fromCurrency = selectedSymbol['key'];
    this.symbolToList = selectedSymbol['value'];
  }

}
