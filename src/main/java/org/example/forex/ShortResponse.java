package org.example.forex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unchecked")
public class ShortResponse {

    private String code;

    private Double value;

    public String ToJson2() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(ShortResponse.this);
        return json;
    }
}
