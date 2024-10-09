package org.example.forex.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "forex.service.avsads")
@Configuration
@Data
public class ForexConfig {

    private String key;

}
