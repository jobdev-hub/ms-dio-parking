package com.jobdev.msdioparking.domain.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "springdoc.swagger-ui")
public class SpringDocSwaggerUiSetting {
    private String path;
}
