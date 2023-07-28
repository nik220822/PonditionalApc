package com.nicode.conditionalApp;

import com.nicode.conditionalApp.profilies.DevProfile;
import com.nicode.conditionalApp.profilies.ProductionProfile;
import com.nicode.conditionalApp.profilies.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JC {
    @Bean
    @ConditionalOnProperty(value = "profile", havingValue = "DevProfile")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(value = "profile", havingValue = "ProductionProfile")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
