package it.cnr.irea.edi.template_manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "edi")
public class JsonLdConfig {
    @Value("${edi.defaults.context}")
    public String jsonLdContext;
}
