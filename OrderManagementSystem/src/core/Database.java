package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance = null;
    private Connection connection  = null;
    private final String DB_URL = "jdbc:mysql://localhost:3306/order_management_system";
    private final String DB_USER =  "root";
    private final String DB_PASSWORD = "";

    private Database() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC sürücüsü bulunamadı. Projeye mysql-connector JAR ekleyin.");
            e.printStackTrace(System.err);
        } catch (SQLException e) {
            System.err.println("MySQL bağlantısı kurulamadı. URL/username/password değerlerini kontrol edin.");
            e.printStackTrace(System.err);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection() == null || instance.getConnection().isClosed()){
                instance = new Database();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        
        if (instance == null || instance.getConnection() == null) {
            throw new RuntimeException("Database connection is not available. Check MySQL server, JDBC driver, and credentials.");
        }
        return instance.getConnection();
    }
}

