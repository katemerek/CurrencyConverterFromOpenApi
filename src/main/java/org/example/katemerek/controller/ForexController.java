package org.example.katemerek.controller;

import jakarta.validation.constraints.Min;
import org.example.katemerek.model.ConverterResponse;
import org.example.katemerek.service.ConverterService;
import org.example.katemerek.service.ConverterServiceImpl;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForexController {
    private final ConverterService converterService;

    public ForexController() {
        this.converterService = new ConverterServiceImpl();
    }

    @GetMapping(value = "/converter")
    private ConverterResponse Request(@RequestParam(name = "apikey") String apikey, @RequestParam(name = "base_currency") String baseCurrency, @RequestParam(name = "currencies") String code, @RequestParam(name = "quantity") @Min (1) Double quantity) {
        return converterService.get(apikey, baseCurrency, code, quantity)
                .orElseThrow(() -> new RuntimeException("Could not find converter for currency " + baseCurrency.toString()));
    }
}



