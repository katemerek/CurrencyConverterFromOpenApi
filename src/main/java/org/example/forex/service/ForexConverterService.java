package org.example.forex.service;

import lombok.RequiredArgsConstructor;
import org.example.forex.dto.ConverterResponseDto;
import org.example.forex.mapper.ResponseMapper;
import org.example.forex.enumeration.Currency;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.*;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class ForexConverterService implements ConverterService {

    private final ResponseMapper responseMapper;

    private final RestClient.Builder restClientBuilder;

    @Override
    public Optional<ResponseEntity<ConverterResponseDto>> get(String apikey, Currency baseCurrency, Currency currencies, Double quantity) {

        RestClient restClient = restClientBuilder.baseUrl("https://api.currencyapi.com/v3/").build();

        return Optional.of(restClient.get()
                .uri(uriBuilder -> {
                    URI build = uriBuilder.path("latest")
                                    .queryParam("apikey", apikey)
                                    .queryParam("base_currency", baseCurrency)
                                    .queryParam("currencies", currencies)
                                    .build();
                    System.out.println(build);
                    return build;
                        }
                ).accept(APPLICATION_JSON)
                .retrieve()
                .toEntity(ConverterResponseDto.class));
//                .map(responseMapper::toConverterResponse));
    }
}
