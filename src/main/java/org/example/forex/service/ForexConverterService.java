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
    public Optional<ConverterResponse> get(String apikey, Currency baseCurrency, Currency currencies, Double quantity) {

        RestClient restClient = restClientBuilder.baseUrl("https://api.currencyapi.com/v3/").build();

        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> {
                    URI build = uriBuilder.path("latest")
                                    .queryParam("apikey", apikey)
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
