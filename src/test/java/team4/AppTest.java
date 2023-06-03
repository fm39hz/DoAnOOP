package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
// import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import team4.KitchenManager.DAO.MainDAO;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.*;
import team4.KitchenManager.Service.ComponentTable;
import team4.KitchenManager.Service.PropertyInfoExtractor;

public class AppTest {
    Employees _testEmployees;
    Customers _testcustomer;
    Ingredient _testIngredient;
    Dishes _testdishes;
    DatabaseConnector _testdb;
    MainDAO _testdao;
    public AppTest(){
        _testEmployees = new Employees(07, "Pham Danh Hien", "0849070703", "Staff", 10000000);
        _testcustomer = new Customers(07, "Pham Danh Hien", "0849070703");
        _testIngredient = new Ingredient(7, "Rice", Date.valueOf("2022-07-07"), 70, 100);
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        _testdao = new MainDAO(_testdb);
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
        //Kiểm tra trạng thái kết nối tới database
        assertEquals(_testdao.getConnectionState(), true);
        _testdao.Add(_testEmployees);
        //Lấy ra dữ liệu từ database tương ứng với class được truyền vào
        var _temp = _testdao.Get(new Employees(), 7);
            //Khi không tìm thấy đối tượng thỏa mãn sẽ trả về null
            if (_temp == null){
                //Nếu không tìm thấy đối tượng trong database thì sẽ thêm đối tượng
                _testdao.Add(_testEmployees);
                //Lần này khi lấy sẽ không trả về null nữa
                _temp = _testdao.Get(new Employees(), 7);
                }
            //Xóa đối tượng
            _testdao.Remove(_temp);
        }
    @Test
    public void PIETest(){
        var _tempStrings = PropertyInfoExtractor.GetObjectPropertyNames(Employees.class);
        var _tempTypes = PropertyInfoExtractor.GetObjectPropertyTypes(Employees.class);
        var _tempValue = PropertyInfoExtractor.InvokeGetter(Employees.class, _tempStrings[3], _testEmployees);
        var i = 0;
            for (String _name : _tempStrings){
                System.out.println(_name + " with class " + _tempTypes[i++]);
                }
            System.out.println(_tempValue);
        assertEquals(_tempValue != null, true);
        }
    @Test
    public void ComponentTableTest(){
        var _long = new Employees(01, "Ngo Tien Long", "0912222003", "Staff", 12000000);
            Employees.class.getTypeName();
        var _empList = new ArrayList<>();
            _empList.add(_testEmployees);
            _empList.add(_long);
        var _tempTable = new ComponentTable<>(Employees.class, _empList);
            System.out.println(_tempTable.getValueAt(0, 4));
        }
    }
