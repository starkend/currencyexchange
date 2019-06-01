import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
// import { catchError} from 'rxjs/operators';
import { Symbol} from '../model/symbol.model';
import {Quote} from '../model/quote.model';
import {ConvertCurrency} from '../model/convert-currency.model';
import {Rate} from "../model/rate.model";
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SymbolsService {

  constructor(private http: HttpClient) { }

  getAllSymbols(): Observable<any> {
    return this.http.get(environment.apiUrl + '/symbols');
  }

  getSymbolsList(): Observable<any> {
    return this.http.get(environment.apiUrl + '/symbolsList');
  }

  getSymbolsMap(): Observable<any> {
    return this.http.get<Map<string, Array<string>>>(environment.apiUrl + '/symbolsMap');
  }

  getSavedSymbols(): Observable<any> {
    return this.http.get(environment.apiUrl + '/savedSymbols');
  }

  addSymbol(symbol: Symbol): Observable<Symbol>  {
    return this.http.post<Symbol>(environment.apiUrl + '/addSymbol', symbol );
  }

  getQuote(symbol: Symbol): Observable<Quote>  {
    return this.http.post<Quote>(environment.apiUrl + '/retrieveQuote', symbol);
  }

  getRate(symbol: Symbol): Observable<Rate>  {
    return this.http.post<Rate>(environment.apiUrl + '/retrieveRate', symbol);
  }

  getRatesForSymbol(baseSymbol: String): Observable<any>  {
    return this.http.post<any>(environment.apiUrl + '/getRatesForSymbol', baseSymbol);
  }

  getCurrencyConversion(convertCurrency: ConvertCurrency): Observable<string>  {
    return this.http.post<string>(environment.apiUrl + '/convertCurrency', convertCurrency);
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
