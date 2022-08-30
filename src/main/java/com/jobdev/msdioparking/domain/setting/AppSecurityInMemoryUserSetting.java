package com.jobdev.msdioparking.domain.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app-security-in-memory.user")
public class AppSecurityInMemoryUserSetting {
    private String name;
    private String password;
    private String roles;
}
