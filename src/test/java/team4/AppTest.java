package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import team4.KitchenManager.DatabaseConnector;
import team4.KitchenManager.DAO.DAO;
import team4.KitchenManager.DatabaseConnector.Url;
import team4.KitchenManager.Model.Customers;
import team4.KitchenManager.Model.Dishes;
import team4.KitchenManager.Model.Employees;

public class AppTest {
    Employees _testEmployees;
    Customers _testcustomer;
    Dishes _testdishes;
    DatabaseConnector _testdb;
    DAO<Employees> _testdao;
    public AppTest(){
        _testEmployees = new Employees(07, "Pham Danh Hien", "0849070703", "Staff", 10000000);
        _testcustomer = new Customers(07, "Pham Danh Hien", "0849070703");
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        _testdao = new DAO<>();
        }
    @Test
    public void ConectionTest(){
        assertEquals(_testdb.getConnectionState(), true);
        }
    @Test
    public void CustomersTest(){
        assertEquals(_testcustomer.getID(), 07);
        }
    @Test
    public void DAOTest() throws SQLException{
        _testdao.Add(_testEmployees);
        assertEquals(_testdao.getConnector().getConnectionState(), _testdb.getConnectionState());
        }
    }
