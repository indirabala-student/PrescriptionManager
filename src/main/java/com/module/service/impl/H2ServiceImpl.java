package com.module.service.impl;

import com.module.service.IH2Service;
import com.module.util.logging.AppLogger;

import java.sql.*;
import java.util.logging.Logger;

public class H2ServiceImpl implements IH2Service {

    private static final Logger logger = AppLogger.getLogger(H2ServiceImpl.class);

    @Override
    public Connection runH2DB() {
        try {
            String jdbcUrl = "jdbc:h2:mem:testdb"; // In-memory DB
            String username = "sa";
            String password = "";
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            return connection;
        }catch (SQLException exception){
            logger.severe("H2 Database cannot be started due to SQL Exception");
        }
        return null;
    }

    @Override
    public boolean makeStatement(Connection connection, String sqlString) {
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sqlString);
            return true;
        }catch (SQLException exception){
            logger.severe("Statement cannot be initialized due to SQL Exception");
        }
        return false;
    }

    @Override
    public boolean makePreparedStatement(Connection connection, String sql, Object... params) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.severe("Error executing prepared statement: " + e.getMessage());
            return false;
        }
    }

}
