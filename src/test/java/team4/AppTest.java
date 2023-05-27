package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import team4.KitchenManager.DAO.MainDAO;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.*;

public class AppTest {
    Employees _testEmployees;
    Customers _testcustomer;
    Dishes _testdishes;
    DatabaseConnector _testdb;
    MainDAO _testdao;
    public AppTest(){
        _testEmployees = new Employees(07, "Pham Danh Hien", "0849070703", "Staff", 10000000);
        _testcustomer = new Customers(07, "Pham Danh Hien", "0849070703");
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        _testdao = new MainDAO();
        }
    // @Test
    // public void ConectionTest(){
    //     assertEquals(_testdb.getConnectionState(), true);
    //     }
    // @Test
    // public void CustomersTest(){
    //     assertEquals(_testcustomer.getID(), 07);
    //     }
    @Test
    public void DAOTest() throws SQLException{
        var _temp = _testdao.Get(new Ingredient(), 7);
            if (_temp == null){
                _testdao.Add(_temp);
                }
        assertEquals(_testdao.getConnector().getConnectionState(), _testdb.getConnectionState());
        _testdao.Remove(_temp);
        _testdao.Remove(_temp.toString(), 7);
        }
    }
