package com.module.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface IH2Service {

    public Connection runH2DB() throws SQLException;

    public boolean makeStatement(Connection connection, String sqlString);

    public boolean makePreparedStatement(Connection connection, String sqlString, Object... params);
}
