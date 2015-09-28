package com.asifsid88.coupondunia.dal.configuration;

/**
 * Properties file `keys`
 */
interface PropertyName {
    String JDBC_DRIVER = "jdbc.driver";
    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";
}

/**
 * DEFAULT Properties value
 */
interface PropertyDefaultValue {
    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/couponduniadb?useUnicode=true&characterEncoding=UTF-8";
    String JDBC_USERNAME = "coupondunia";
    String JDBC_PASSWORD = "(0uPoNDuN!A";
}
