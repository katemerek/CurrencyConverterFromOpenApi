# Currency Converter from CurrencyApi

## Description
The application based on open service for getting current exchange rates "CurrencyApi". You enter the origin currency, the final currency, the amount of money and get the result.

### Description of request
To use this application you need to send the following parameters in your request:

- the key for accessing CurrencyApi is generated on https://currencyapi.com/ free on 30 days (apikey),
- origin currency (base_currency),
- final currency (code),
- amount of currency for conversion (quantity).

<ins>Available types of currencies for conversion</ins>: {EUR, GBP, USD, INR, JPY, RUB, SGD, THB, TRY, ZAR}. To use other currencies you need to add them to the file [CurrenciesEnum] (src/main/java/org/example/katemerek/enumiration/CurrenciesEnum.java).

**Example response from CurrencyApi:**
{"meta":{ "last_updated_at":"2023-08-02T23:59:59Z" }, "data":{ "ADA":{ "code":"ADA", "value":3.3342788788 } } }

### Description response from Currency Converter
The response contains:

- origin currency (base_currency),
- final currency (code),
- conversion factor (value),
- amount of currency for conversion (quantity),
- total amount of new currency (totalAmount).

**Example response from Currency Converter**
{"base_currency":"USD", "code": "EUR", "value":"0.9022601691, "quantity":100, "totalAmount":90.22601691}