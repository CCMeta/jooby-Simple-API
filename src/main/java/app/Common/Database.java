package app.Common;

import java.sql.*;
import java.util.Map;

import io.jooby.Jooby;

public class Database {

    private static Connection connection;

    static {
        init();
    }

    private static void init() {
        var db_config = new Jooby().getEnvironment().getConfig();
        try {
            connection = DriverManager.getConnection(
                    db_config.getString("db_url"),
                    db_config.getString("db_user"),
                    db_config.getString("db_password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String select(String sql, Map<Integer, Object> parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (parameters != null) {
            for (Integer key : parameters.keySet()) {
                preparedStatement.setObject(key, parameters.get(key));
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString(1);
    }
}
