package org.example.forex.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ConverterResponse {
    private String base_currency;
    private String code;
    private Double value;
    private Double quantity;
    private Double totalAmount;
}


