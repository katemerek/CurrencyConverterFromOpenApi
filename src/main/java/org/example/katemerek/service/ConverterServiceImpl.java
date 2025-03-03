package org.example.katemerek.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.katemerek.util.RequestException;
import org.example.katemerek.model.ShortResponse;
import org.example.katemerek.enumiration.CurrenciesEnum;
import org.example.katemerek.model.ConverterResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.*;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConverterServiceImpl implements ConverterService {

    @Override
    public Optional<ConverterResponse> get(String apikey, String baseCurrency, String code, Double quantity) {
        checkValidCurrencies(baseCurrency, code);
        try {
            String response = getResponseCurrency(apikey, baseCurrency, code);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response);
            ShortResponse shortResponse = new ShortResponse();
            shortResponse.setCode(jsonNode.get("data").get(code).get("code").asText());
            shortResponse.setValue(jsonNode.get("data").get(code).get("value").asDouble());
            Double amount = shortResponse.getValue();
            ConverterResponse converterResponse = new ConverterResponse(
                    baseCurrency,
                    shortResponse.getCode(),
                    shortResponse.getValue(),
                    quantity,
                    quantity * amount
            );

            return Optional.of(converterResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponseCurrency(String apikey, String baseCurrency, String code) {
        RestClient restClient = RestClient.builder()
                .baseUrl("https://api.currencyapi.com/v3/")
                .build();
        return restClient.get()
                .uri(uriBuilder -> {
                            URI build = uriBuilder.path("latest")
                                    .queryParam("apikey", apikey)
                                    .queryParam("base_currency", baseCurrency)
                                    .queryParam("code", code)
                                    .build();
                            System.out.println(build);
                            return build;
                        }
                ).retrieve()
                .body(String.class);
    }

    private void checkValidCurrencies(String baseCurrency, String code) {
        try {
            CurrenciesEnum.valueOf(CurrenciesEnum.class, baseCurrency);
            CurrenciesEnum.valueOf(CurrenciesEnum.class, code);
        } catch (IllegalArgumentException e) {
            throw new RequestException(baseCurrency, code);
        }
    }

}
