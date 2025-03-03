package org.example.forex.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.forex.mapper.ResponseMapper;
import org.example.forex.models.ConverterResponse;

@Data
@RequiredArgsConstructor
public class ConverterResponseDto {
    private String base_currency;
    private String code;
    private Double value;


}
