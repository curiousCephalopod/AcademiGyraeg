/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.academigyraeg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A simple data source for getting database connections. Sourced from
 * blackboard.
 */
public class SimpleDataSource {

    /**
     * Initialises the data source.
     *
     * @param stream the stream that contains the database driver, url, username
     * and password
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void init(InputStream stream) throws IOException, ClassNotFoundException {
        // Retrieve the properties
        Properties props = new Properties();
        props.load(stream);

        // Set the settings from the properties
        String driver = props.getProperty("jdbc.driver");
        url = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");

        Class.forName(driver);
    }

    /**
     * @throws java.sql.SQLException * Gets a connection to the database.
     *
     * @return the database connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    private static String url;
    private static String username;
    private static String password;
}
