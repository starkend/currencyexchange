import { Component, OnInit } from '@angular/core';
import {SymbolsService} from '../shared/service/symbols.service';
import {Rate} from '../shared/model/rate.model';

@Component({
  selector: 'app-rates',
  templateUrl: './rates.component.html',
  styleUrls: ['./rates.component.css']
})
export class RatesComponent implements OnInit {

  symbols: Array<any>;
  selectedSymbol: String;
  rates: Array<Rate>;
  displayedColumns: Array<string>;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.displayedColumns = ['symbol', 'rate'];
    this.getSymbolsList();
  }

  getSymbolsList() {
    this.symbolsService.getSymbolsList().subscribe( data => {
      this.symbols = data;
    });
  }

  submitRate(baseSymbol: String) {
    this.symbolsService.getRatesForSymbol(baseSymbol).subscribe( data => {
      this.rates = data;
    })
  }

}
