package org.example.forex.service;

import org.example.forex.dto.ConverterResponseDto;
import org.example.forex.models.ConverterResponse;
import org.example.forex.enumeration.Currency;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ConverterService {
    Optional<ResponseEntity<ConverterResponseDto>> get(String apikey, Currency baseCurrency, Currency currencies, Double quantity);
}
