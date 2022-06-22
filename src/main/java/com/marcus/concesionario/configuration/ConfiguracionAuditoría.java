package com.marcus.concesionario.configuration;


import com.marcus.concesionario.upload.storage.StorageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@EnableConfigurationProperties(StorageProperties.class)
public class ConfiguracionAuditoría {

}
