package org.example.forex.service;

import lombok.RequiredArgsConstructor;
import org.example.forex.domain.ConverterResponse;
import org.example.forex.enumeration.Currency;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForexConverterService implements ConverterService {

    private final RestClient.Builder restClientBuilder;

    @Override
    public Optional<ConverterResponse> get(Currency baseCurrency, Currency currencies, Double quantity) {

        RestClient restClient = restClientBuilder.baseUrl("https://api.currencyapi.com/v3/").build();

        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> {
                    URI build = uriBuilder.path("latest")
                                    .queryParam("apikey", "cur_live_yJX8pKp6yPOOOHGqyjZiZEN0sdH8LpuzM8b9yk37")
                                    .queryParam("base_currency", baseCurrency)
                                    .queryParam("currencies", currencies)
                                    .build();
                    System.out.println(build);
                    return build;
                        }
                ).retrieve()
                .body(ConverterResponse.class));
    }
}
