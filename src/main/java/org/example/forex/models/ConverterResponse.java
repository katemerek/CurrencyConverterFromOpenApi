package org.example.forex.models;

import lombok.Data;

@Data

public class ConverterResponse {
    private String base_currency;
    private String code;
    private Double value;
    private Double quantity;
    private Double totalAmount;

    public ConverterResponse(String base_currency, String code, Double value, Double quantity) {
        this.base_currency = base_currency;
        this.code = code;
        this.value = value;
        this.quantity = quantity;
    }
}


