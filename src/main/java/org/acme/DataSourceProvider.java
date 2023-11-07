/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.acme;

import io.quarkus.arc.DefaultBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author ctoska
 */
@ApplicationScoped
@DefaultBean
public class DataSourceProvider {

    private final DataSource dataSource;

    public DataSourceProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    void init() {
        // Do nothing, but this ensures that the data source is available for injection.
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
