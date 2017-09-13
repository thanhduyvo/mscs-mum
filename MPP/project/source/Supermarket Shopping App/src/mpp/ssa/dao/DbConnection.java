package mpp.ssa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mpp.ssa.util.ConfigurationHelper;

public class DbConnection {

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(ConfigurationHelper.getConfiguration("CONNECTION_STRING"));
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        if(conn == null) {
            this.open();
        }

        return conn;
    }
}