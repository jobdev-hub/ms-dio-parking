package com.jobdev.msdioparking.domain.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "parking-value-setting")
public class ParkingValueSetting {
    private Double oneHourValue;
    private Double additionalPerHourValue;
    private Double dayValue;
}
