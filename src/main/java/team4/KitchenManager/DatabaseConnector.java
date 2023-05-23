package team4.KitchenManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Data;

@Data
public class DatabaseConnector {
    public class Url{
        public static final String MariaDB = "jdbc:mariadb://localhost:3306/kitchenmanager";
        public static final String MySql = "jdbc:m://localhost:3306/kitchenmanager";
        }
    private Boolean ConnectionState = true;
    private Connection Connector;

    public DatabaseConnector(String url, String userName, String password){
        try{
            this.setConnector(DriverManager.getConnection(url, userName, password));
            System.out.println("Connecting to Database through: " + url);
            }
        catch (SQLException CannotGetTable){
            System.out.println("Cannot connect to " + url + " at state " + CannotGetTable.getSQLState());
            this.ConnectionState = false;
            }
        }
    }
