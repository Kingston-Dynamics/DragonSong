package com.kdyncs.dragonsong.playfab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "playfab")
public class PlayFabConfiguration {

    private String key;
    private String host;
    private String title;

    private static final Logger log = LogManager.getLogger();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        log.trace("Playfab Key is {}", key);
        this.key = key;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        log.trace("Playfab Host is {}", host);
        this.host = host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        log.trace("Playfab Title is {}", title);
        this.title = title;
    }
}
