package io.github.byc.smart.boot.mybatis;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author : bamboo
 */
@Configuration
@MapperScan({"${io.github.byc-smart.mapper.scan}"})
public class MyBatisConfig {
    @Bean(name = {"databaseIdProvider"})
    public DatabaseIdProvider getDatabaseIdProvider() {
        VendorDatabaseIdProvider provider = new VendorDatabaseIdProvider();

        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("PostgreSQL", "postgresql");

        provider.setProperties(properties);

        return provider;
    }

    @Bean(name = {"configurationCustomizer"})
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return cfg -> {
            cfg.setMapUnderscoreToCamelCase(true);
            cfg.setJdbcTypeForNull(JdbcType.NULL);
        };
    }
}
