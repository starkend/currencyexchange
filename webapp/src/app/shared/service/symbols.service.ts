import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
// import { catchError} from 'rxjs/operators';
import { Symbol} from '../model/symbol.model';
import {Quote} from '../model/quote.model';
import {ConvertCurrency} from '../model/convert-currency.model';
import {ConvertedRate} from '../model/converted-rate.model';

@Injectable({
  providedIn: 'root'
})
export class SymbolsService {

  constructor(private http: HttpClient) { }

  getAllSymbols(): Observable<any> {
    return this.http.get('//localhost:8080/symbols');
  }

  getSymbolsMap(): Observable<any> {
    return this.http.get<Map<string, Array<string>>>('//localhost:8080/symbolsMap');
  }

  getSavedSymbols(): Observable<any> {
    return this.http.get('//localhost:8080/savedSymbols');
  }

  addSymbol(symbol: Symbol): Observable<Symbol>  {
    return this.http.post<Symbol>('//localhost:8080/addSymbol', symbol );
  }

  getQuote(symbol: Symbol): Observable<Quote>  {
    return this.http.post<Quote>('//localhost:8080/retrieveQuote', symbol);
  }

  getCurrencyConversion(convertCurrency: ConvertCurrency): Observable<ConvertedRate>  {
    return this.http.post<ConvertedRate>('//localhost:8080/convertCurrency', convertCurrency);
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
  }
}
