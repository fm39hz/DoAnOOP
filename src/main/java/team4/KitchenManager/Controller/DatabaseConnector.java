package team4.KitchenManager.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection conn = null;

    static {
        String database_name = "kitchen";
        String url = "jdbc:mysql://localhost:3306/"+database_name;
        String username = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            // Kết nối thành công
        } catch (SQLException e) {
            // Xử lý lỗi kết nối
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }


}
