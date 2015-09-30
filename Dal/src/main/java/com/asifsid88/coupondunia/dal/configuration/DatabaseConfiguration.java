package com.asifsid88.coupondunia.dal.configuration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Database Configuration:
 * The configurations are picked up from Environment variables. So the client who is using DataAccessLayer (DAL) should have
 * mechanism to feed these values (in web application can be fed using `Environment` tag in web.xml
 * in stand-alone application or fat-jar, we can feed using properties file or any other config manager or can also be
 * supplied as -DvariableName
 *
 */
@Configuration
@ComponentScan("com.asifsid88.coupondunia.dal")
@Log4j2
public class DatabaseConfiguration {

    private DataSource couponDuniaDataSource;

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

    /*
    Initialize datasource to be used by system
     */
    private DataSource initializeDataSourceFromSystemProperty() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
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