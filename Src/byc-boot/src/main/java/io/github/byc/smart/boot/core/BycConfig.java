package io.github.byc.smart.boot.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : bamboo
 */
@Component
@ConfigurationProperties(prefix = "io.github.byc-smart")
public class BycConfig {
    private String modelPackage = "io.github.byc-smart";

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }
}
