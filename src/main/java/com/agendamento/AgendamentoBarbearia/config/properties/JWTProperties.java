package com.agendamento.AgendamentoBarbearia.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.config.security")
@Getter @Setter
public class JWTProperties {
    private String secret;
}
