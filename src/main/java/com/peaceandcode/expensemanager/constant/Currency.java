package com.peaceandcode.expensemanager.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
  EUR("€"),
  USD("$"),
  JPY("¥"),
  BGN("лв"),
  CZK("Kč"),
  DKK("kr"),
  GBP("£"),
  HUF("Ft"),
  PLN("zł"),
  RON("lei"),
  SEK("kr"),
  CHF("CHF"),
  ISK("kr"),
  NOK("kr"),
  HRK("kn"),
  RUB("₽"),
  TRY("₺"),
  AUD("A$"),
  BRL("R$"),
  CAD("C$"),
  CNY("¥"),
  HKD("HK$"),
  IDR("Rp"),
  ILS("₪"),
  INR("₹"),
  KRW("₩"),
  MXN("$"),
  MYR("RM"),
  NZD("NZ$"),
  PHP("₱"),
  SGD("S$"),
  THB("฿"),
  ZAR("R");

  private final String symbol;
}
