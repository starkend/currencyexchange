import { Component, OnInit } from '@angular/core';
import { SymbolsService} from "../shared/symbols.service";

@Component({
  selector: 'app-symbols-list',
  templateUrl: './symbols-list.component.html',
  styleUrls: ['./symbols-list.component.css']
})
export class SymbolsListComponent implements OnInit {
  symbols: Array<any>;

  constructor(private symbolsService: SymbolsService) { }

  ngOnInit() {
    this.symbolsService.getAll().subscribe(data => {
      this.symbols = data;
    })
  }

}
