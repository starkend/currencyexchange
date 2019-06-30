import { Component, OnInit } from '@angular/core';
import {SymbolsService} from "../shared/service/symbols.service";

@Component({
  selector: 'app-historical-rates',
  templateUrl: './historical-rates.component.html',
  styleUrls: ['./historical-rates.component.css']
})
export class HistoricalRatesComponent implements OnInit {
  historicalRates: Array<any>;
  displayedColumns: Array<string>;
  subColumns: Array<string>;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.getHistoricalRates();
    this.displayedColumns = ['idColumn','base','rates'];
    this.subColumns = ['date'];

  }

  getHistoricalRates() {
    this.symbolsService.getHistoricalRates().subscribe( data => {
      this.historicalRates = data;
    });
  }
}
