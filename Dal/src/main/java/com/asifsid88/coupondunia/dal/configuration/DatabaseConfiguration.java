package com.asifsid88.coupondunia.dal.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ComponentScan("com.asifsid88.coupondunia.dal")
@Log4j2
public class DatabaseConfiguration {

    private SystemPropertyConfiguration systemPropertyConfiguration;

    private DataSource couponDuniaDataSource;

    @Autowired
    public void setSystemPropertyConfiguration(@Qualifier("dalEnvironment") SystemPropertyConfiguration systemPropertyConfiguration) {
        this.systemPropertyConfiguration = systemPropertyConfiguration;
    }

    @Bean
    public DataSource dataSource() {
        return couponDuniaDataSource;
    }

    @Bean
    public JdbcTemplate template() {
        return new JdbcTemplate(dataSource());
    }

    @Autowired
    public void init() {
        couponDuniaDataSource = initializeDataSourceFromSystemProperty();
    }

    private DataSource initializeDataSourceFromSystemProperty() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        log.info("in DBConfig: from sysem property -- {}" , System.getProperty(PropertyName.JDBC_DRIVER));

        dataSource.setDriverClassName(System.getProperty(PropertyName.JDBC_DRIVER, PropertyDefaultValue.JDBC_DRIVER));
        dataSource.setUrl(System.getProperty(PropertyName.JDBC_URL, PropertyDefaultValue.JDBC_URL));
        dataSource.setUsername(System.getProperty(PropertyName.JDBC_USERNAME, PropertyDefaultValue.JDBC_USERNAME));
        dataSource.setPassword(System.getProperty(PropertyName.JDBC_PASSWORD, PropertyDefaultValue.JDBC_PASSWORD));

        return dataSource;
    }

    @PostConstruct
    public void logEnvironment() {
        log.info("Start using DatabaseConfiguration");
    }
}