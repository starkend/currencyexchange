import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import { catchError} from "rxjs/operators";
import { Symbol} from "../model/symbol.model";

@Injectable({
  providedIn: 'root'
})
export class SymbolsService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/symbolsAngularList');
  }

  getSavedSymbols(): Observable<any> {
    return this.http.get('//localhost:8080/savedSymbols');
  }

  addSymbol(symbol: Symbol): Observable<Symbol>  {
    // console.log("In Symbols Service, Symbol 1: " + symbol.symbol1 + ", 2: " + symbol.symbol2);
    return this.http.post<Symbol>('//localhost:8080/addSymbol', symbol );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };
}
