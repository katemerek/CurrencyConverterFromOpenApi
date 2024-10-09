package org.example.forex.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.example.forex.config.ForexConfig;
import org.example.forex.domain.ConverterResponse;
import org.example.forex.enumeration.Currency;
import org.example.forex.service.ConverterService;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForexController {
    private final HttpMessageConverters messageConverters;
    private final ForexConfig forexConfig;
    private final ConverterService converterService;

    @GetMapping(value = "/converter")
    private ConverterResponse Request(@RequestParam(name = "apikey") String apikey, @Parameter(description = "Базовая валюта") @RequestParam(name = "base_currency") Currency baseCurrency, @RequestParam(name = "currencies") Currency currencies, @RequestParam(name = "quantity") Double quantity) {
        return converterService.get(apikey, baseCurrency, currencies, quantity)
                .orElseThrow(() -> new RuntimeException("Could not find converter for currency " + baseCurrency.toString()));
    }

}




