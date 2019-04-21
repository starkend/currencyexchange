// src/app/shared/models/convert-currency.model.ts

export class ConvertCurrency {

  fromCurrency: string;
  toCurrency: string;
  quantity: number;

  constructor(fromCurrency: string, toCurrency: string, quantity: number) {
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
    this.quantity = quantity;
  }

}
