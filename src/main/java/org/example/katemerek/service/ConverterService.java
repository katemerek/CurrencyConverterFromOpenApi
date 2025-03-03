package org.example.katemerek.service;

import org.example.katemerek.model.ConverterResponse;


import java.util.Optional;

public interface ConverterService {
    Optional<ConverterResponse> get(String apikey, String baseCurrency, String code, Double quantity);
}
