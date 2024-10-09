package org.example.forex.util;
//обработка ошибок неверного кода валюты для конвертации

public class RequestException extends RuntimeException {
    public RequestException(String base_currency, String currencies) {
        super("Invalid Value for base_currency = " + base_currency + " or currencies = " + currencies);
    }
}
