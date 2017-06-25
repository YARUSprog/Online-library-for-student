
package com.mycompany.OnlineLibraryForStudent.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *
 * @author YARUS
 */
@Configuration
@PropertySource({"classpath:application.properties", "classpath:local.properties"})
public class DataConfig {
    private static final Logger log = LoggerFactory.getLogger(DataConfig.class);

    private static final String PROPERTY_DATA_BASE_NAME = "databaseName";

    private static final String SPRING_DATASOURCE_LOCAL_USERNAME = "spring.datasource.local.username";
    private static final String SPRING_DATASOURCE_LOCAL_PASSWORD = "spring.datasource.local.password";
    private static final String SPRING_DATASOURCE_LOCAL_JDBC_URL = "spring.datasource.local.jdbcUrl";
    private static final String SPRING_DATASOURCE_LOCAL_DATABASE_NAME = "spring.datasource.local.databaseName";

    private static final String SPRING_DATASOURCE_HIKARI_USERNAME = "spring.datasource.hikari.username";
    private static final String SPRING_DATASOURCE_HIKARI_PASSWORD = "spring.datasource.hikari.password";
    private static final String SPRING_DATASOURCE_HIKARI_JDBC_URL = "spring.datasource.hikari.jdbcUrl";
    private static final String SPRING_DATASOURCE_HIKARI_DATABASE_NAME = "spring.datasource.hikari.databaseName";
    private static final String SPRING_DATASOURCE_HIKARI_POOL_NAME = "spring.datasource.hikari.pool-name";
    private static final String SPRING_DATASOURCE_HIKARI_DATA_SOURCE_CLASS_NAME = "spring.datasource.hikari.dataSourceClassName";
    private static final String SPRING_DATASOURCE_HIKARI_CONNECTION_TEST_QUERY = "spring.datasource.hikari.connection-test-query";
    private static final String SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE = "spring.datasource.hikari.maximum-pool-size";
    private static final String SPRING_DATASOURCE_HIKARI_MINIMUM_IDLE = "spring.datasource.hikari.minimum-idle";
    private static final String SPRING_DATASOURCE_HIKARI_IDLE_TIMEOUT = "spring.datasource.hikari.idle-timeout";
    private static final String SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT = "spring.datasource.hikari.connection-timeout";
    private static final String SPRING_DATASOURCE_HIKARI_MAX_LIFETIME = "spring.datasource.hikari.max-lifetime";

    @Resource
    private Environment env;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        log.debug("DataSource configuration - start.");
        HikariConfig config = new HikariConfig();

        if (env.acceptsProfiles("!production")) {
            config.setUsername(env.getProperty(SPRING_DATASOURCE_LOCAL_USERNAME));
            config.setPassword(env.getProperty(SPRING_DATASOURCE_LOCAL_PASSWORD));
            config.setJdbcUrl(env.getProperty(SPRING_DATASOURCE_LOCAL_JDBC_URL));
            config.addDataSourceProperty(PROPERTY_DATA_BASE_NAME, env.getProperty(SPRING_DATASOURCE_LOCAL_DATABASE_NAME));
            log.debug("DataSource configuration - local profile.");
        } else {
            config.setUsername(env.getProperty(SPRING_DATASOURCE_HIKARI_USERNAME));
            config.setPassword(env.getProperty(SPRING_DATASOURCE_HIKARI_PASSWORD));
            config.setJdbcUrl(env.getProperty(SPRING_DATASOURCE_HIKARI_JDBC_URL));
            config.addDataSourceProperty(PROPERTY_DATA_BASE_NAME, env.getProperty(SPRING_DATASOURCE_HIKARI_DATABASE_NAME));
            log.debug("DataSource configuration - production profile.");
        }

        config.setPoolName(env.getProperty(SPRING_DATASOURCE_HIKARI_POOL_NAME));
        config.setDataSourceClassName(env.getProperty(SPRING_DATASOURCE_HIKARI_DATA_SOURCE_CLASS_NAME));
        config.setConnectionTestQuery(env.getProperty(SPRING_DATASOURCE_HIKARI_CONNECTION_TEST_QUERY));
        config.setMaximumPoolSize(Integer.valueOf(env.getProperty(SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE)));
        config.setMinimumIdle(Integer.valueOf(env.getProperty(SPRING_DATASOURCE_HIKARI_MINIMUM_IDLE)));
        config.setIdleTimeout(Long.valueOf(env.getProperty(SPRING_DATASOURCE_HIKARI_IDLE_TIMEOUT)));
        config.setConnectionTimeout(Long.valueOf(env.getProperty(SPRING_DATASOURCE_HIKARI_CONNECTION_TIMEOUT)));
        config.setMaxLifetime(Long.valueOf(env.getProperty(SPRING_DATASOURCE_HIKARI_MAX_LIFETIME)));

        log.debug("DataSource configuration - end.");
        return new HikariDataSource(config);
    }
    
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        return registrationBean;
    }
}