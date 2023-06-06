package team4.KitchenManager.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Data;

@Data
public class DatabaseConnector {
    public static class Url{
        public static final String MariaDB = "jdbc:mariadb://localhost:3306/kitchen";
        public static final String MySql = "jdbc:mysql://localhost:3306/kitchen";
        public static final String SQLServer = "jdbc:sqlserver://localhost:1433;databaseName=Kitchen;";
        }
    private Boolean ConnectionState;
    private Connection Connector;

    public DatabaseConnector(){
        this.Connect(Url.MariaDB, "root", "");
        }
    public DatabaseConnector(String url, String userName, String password){
        this.Connect(url, userName, password);
        }
        
    public void Connect(String url, String userName, String password){
        try{
            this.ConnectionState = false;
            this.setConnector(DriverManager.getConnection(url, userName, password));
            System.out.println("Connecting to Database through: " + url);
            }
        catch (SQLException CannotGetTable){
            System.out.println("Cannot connect to " + url + " at state " + CannotGetTable.getSQLState());
            CannotGetTable.printStackTrace();
            this.ConnectionState = false;
            return;
            }
        this.ConnectionState = true;
        System.out.println("Connected to database: " + url);
        }
    }
