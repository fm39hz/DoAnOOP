package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
// import team4.KitchenManager.Model.*;

public class AppTest {
    // Ingredient _testIngredient;
    DatabaseConnector _testdb;
    public AppTest(){
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        }
    @Test
    public void ConectionTest(){
        assertEquals(_testdb.getConnectionState(), true);
        }
    }
