package com.fairy.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yihaibo on 2019-04-01.
 */
@Configuration
public class ProfileConfig {
    @Value("${spring.profiles.active}")
    @Getter
    private String profileActive;

    public boolean isDev() {
        return "dev".equals(profileActive);
    }
}
