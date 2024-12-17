package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static MySqlConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/your_database_name";
    private String username = "your_username";
    private String password = "your_password";

    private MySqlConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            throw new SQLException("MySQL Driver not found.");
        }
    }

    public static MySqlConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySqlConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
