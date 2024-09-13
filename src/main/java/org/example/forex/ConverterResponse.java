package org.example.forex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
@Data
public class ConverterResponse {
    private String base_currency;

    private String code;

    private Double value;

    private Double quantity;

    private Double totalAmount;

    public ConverterResponse(String base_currency, String code, Double value, Double quantity, Double totalAmount) {
        this.base_currency = base_currency;
        this.code = code;
        this.value = value;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String ToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

       String json = mapper.writeValueAsString(ConverterResponse.this);
        return json;
    }
}


