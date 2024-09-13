package org.example.forex;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//вспоминала как парсить джейсоны, в том числе вложенные
public class TestJson {
    public static void main(String[] args) throws JsonProcessingException {
String jsonExample = """
        {
        "meta":{
            "last_updated_at":"2023-08-02T23:59:59Z"
        },
        "data":{
            "ADA":{
                "code":"ADA",
                        "value":3.3342788788
            }
        }
        }""";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonExample);
        ShortResponse shortResponse = new ShortResponse();
        shortResponse.setCode(jsonNode.get("data").get("ADA").get("code").asText());
        shortResponse.setValue(jsonNode.get("data").get("ADA").get("value").asDouble());
        Double amount = shortResponse.getValue()*500;
        System.out.println(amount);

    }
}
