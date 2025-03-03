package org.example.katemerek.util;

/**
 Обработка ошибок неверного кода валюты для конвертации.
 */

public class RequestException extends RuntimeException {
    public RequestException(String base_currency, String code) {
        super("Invalid Value for base_currency = " + base_currency + " or currencies = " + code);
    }
}
