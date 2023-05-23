package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import team4.KitchenManager.DatabaseConnector;
import team4.KitchenManager.DatabaseConnector.Url;

public class AppTest 
{
    DatabaseConnector _testdb;
    @Test
    public void ConectionTest()
    {
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        assertEquals(_testdb.getConnectionState(), true);
    }
}
