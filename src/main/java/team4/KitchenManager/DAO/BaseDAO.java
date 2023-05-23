package team4.KitchenManager.DAO;

import team4.KitchenManager.DatabaseConnector;
import team4.KitchenManager.DatabaseConnector.Url;

public class BaseDAO {
    DatabaseConnector Connector;
    public BaseDAO(){
        Connector = new DatabaseConnector(Url.MariaDB, "root", "");
        }
}
