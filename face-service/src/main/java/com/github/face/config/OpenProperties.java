package com.github.face.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wo
 */
@Data
@Component
@ConfigurationProperties(prefix = "open")
public class OpenProperties {
    private List<String> uri;
}
