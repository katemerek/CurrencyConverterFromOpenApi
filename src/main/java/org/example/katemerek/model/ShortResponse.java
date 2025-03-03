package org.example.katemerek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShortResponse {
    private String code;
    private Double value;
    }

