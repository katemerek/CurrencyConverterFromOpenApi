package org.example.forex;
//обработка ошибок неверного количества конвертируемой валюты
public class QuantityException extends RuntimeException {
    public QuantityException(Double quantity) {
        super("Invalid value for quantity: " + quantity);
    }

}
