package org.example.forex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;

@RestController

public class ForexController {
    private final HttpMessageConverters messageConverters;

    public ForexController(HttpMessageConverters messageConverters) {
        this.messageConverters = messageConverters;
    }
//    Серым цветом части кода для работы с таблицой валют из БД
    //    private final ExchangeValueService exchangeValueService;


//    @Autowired
//    private HttpServletRequest httpServletRequest;

//    @Autowired
//    public ForexController(ExchangeValueService exchangeValueService) {this.exchangeValueService = exchangeValueService;
//    }


    @RequestMapping(value = "/converter", method = RequestMethod.GET)
    private String Request(@RequestParam(name = "base_currency") String base_currency, @RequestParam(name = "currencies") String currencies, @RequestParam(name = "quantity") Double quantity) {
        ArrayList<String> validCurrency = new ArrayList<>();
        String[] valutes = {"EUR", "GBP", "USD", "INR", "JPY", "RUB", "SGD", "THB", "TRY", "ZAR"}; //список приемлимых валют
        validCurrency.addAll(List.of(valutes));
        try {
            boolean hasBaseCurrency = validCurrency.contains(base_currency);//проверка валют на приемлимость
            boolean hasCurrency = validCurrency.contains(currencies);
            if (!hasBaseCurrency || !hasCurrency) {
                throw new RequestException(base_currency, currencies); //выбрасываем исключение, если одна из валют не прошла проверку
            }
            if (quantity <= 0) {
                throw new QuantityException(quantity);
            } //проверка на приемлимые значения количества валюты для конвертации. Иначе выбрасываем исключение


            URL url = new URL("https://api.currencyapi.com/v3/latest?apikey=cur_live_yJX8pKp6yPOOOHGqyjZiZEN0sdH8LpuzM8b9yk37&base_currency=" + base_currency + "&currencies=" + currencies); //не забываем передать параметры, которые пойдут в запрос к апишке
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // Set timeout as per needs
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json"); //Indicates that the request body format is JSON.
            con.setRequestProperty("Content-Type", "application/json");//Sets output type to JSON.
            InputStream inputStream = con.getInputStream();
            byte[] res = new byte[2048];
            int i = 0;
            StringBuilder response = new StringBuilder();
            while ((i = inputStream.read(res)) != -1) {
                response.append(new String(res, 0, i));
            }
            inputStream.close();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.toString()); // json parsing
            ShortResponse shortResponse = new ShortResponse();
            shortResponse.setCode(jsonNode.get("data").get(currencies).get("code").asText()); //извлекаем данные из json в экземпляр shortResponse
            shortResponse.setValue(jsonNode.get("data").get(currencies).get("value").asDouble());
            Double amount = shortResponse.getValue();
            ConverterResponse converterResponse = new ConverterResponse(
                    base_currency,
                    shortResponse.getCode(),
                    shortResponse.getValue(),
                    quantity,
                    quantity * amount
            );
            return converterResponse.ToJson();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


//    @GetMapping("/all")
//        public ResponseEntity<List<ExchangeValue>> read() {
//            final List<ExchangeValue> exchangeValues = exchangeValueService.readAll();
//
//            return exchangeValues != null &&  !exchangeValues.isEmpty()
//                    ? new ResponseEntity<>(exchangeValues, HttpStatus.OK)
//                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }


//        public String read(@RequestParam(name = "from") String from, @RequestParam(name = "to") String to, @RequestParam BigDecimal quantity) throws JsonProcessingException {
//            final ExchangeValue exchangeValue = exchangeValueService.read(from, to);
//            ConverterResponse cr = new ConverterResponse(
//                    exchangeValue.getFrom(),
//                    exchangeValue.getTo(),
//                    exchangeValue.conversionMultiple,
//                    quantity,
//                    quantity.multiply(exchangeValue.conversionMultiple)
//                );
//
//            return cr.ToJson();



