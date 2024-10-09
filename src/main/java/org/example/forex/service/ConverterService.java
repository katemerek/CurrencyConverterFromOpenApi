package org.example.forex.service;

import org.example.forex.domain.ConverterResponse;
import org.example.forex.enumeration.Currency;

import java.util.Optional;

public interface ConverterService {
    Optional<ConverterResponse> get(String apikey, Currency baseCurrency, Currency currencies, Double quantity);
}
