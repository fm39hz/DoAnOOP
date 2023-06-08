package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import team4.KitchenManager.Controller.DishesController;
//import team4.KitchenManager.DAO.MainDAO;


import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.Dish;
// import team4.KitchenManager.Model.*;

import javax.swing.*;

public class AppTest {
    // Ingredient _testIngredient;
//    DatabaseConnector _testdb;

//    MainDAO _testdao;
    private DatabaseConnector Connector;
    public AppTest() {
//        _testEmployees = new Employees(07, "Pham Danh Hien", "0849070703", "Staff", 10000000);
//        _testcustomer = new Customers(07, "Pham Danh Hien", "0849070703");
//        _testIngredient = new Ingredient(600, "Rice", Date.valueOf("2022-07-07"), 70, 100);

//    public AppTest() {
//
//            _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
//        }
//        @Test
//
//        public void DAOTest () throws SQLException {
//            // var _quantity = new IngredientQuantity(1, _testIngredient, 20);
//            // _testdao.Add(_quantity);
//            //Kiểm tra trạng thái kết nối tới database
////        assertEquals(_testdao.getConnectionState(), true);
////        IngredientQuantity _tempValue = (IngredientQuantity) _testdao.Get(new IngredientQuantity(), 1);
////            List<DishesController.ReturnData> list = new ArrayList<>();
//            this.Connector = new DatabaseConnector();
//            boolean _ok = false;
//            String result = null;
//            String sql = "SELECT dishes.id,\n" +
//                    "dishes.name,\n" +
//                    "dishes.cost, \n" +
//                    "dishes.price, \n" +
//                    "quantities.ingredient_id, \n" +
//                    "quantities.quantity AS ingre_quantity, \n" +
//                    "ingredients.Id AS ingre_id, \n" +
//                    "ingredients.name AS ingre_name, \n" +
//                    "ingredients.date_in,\n" +
//                    "ingredients.cost AS ingre_cost\n" +
//                    "\n" +
//                    "FROM dishes \n" +
//                    "INNER JOIN quantities ON dishes.id = quantities.dishes_id \n" +
//                    "INNER JOIN ingredients ON ingredients.id = quantities.ingredient_id \n" +
//                    "WHERE dishes.name LIKE '%C%' ;\n";
//            try {
//                var ps = Connector.getConnector().prepareStatement(sql);
////            ps.setInt(1, 216);
//                result = ps.executeUpdate();
//            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
//            }
//            System.out.println(_ok);
////        System.out.println(_tempValue.);
//            // _testdao.Add(_testEmployees);
////        Lấy ra dữ liệu từ database tương ứng với class được truyền vào
////        var _temp = _testdao.Get(new Employees(), 7);
////            //Khi không tìm thấy đối tượng thỏa mãn sẽ trả về null
////            if (_temp == null){
////                //Nếu không tìm thấy đối tượng trong database thì sẽ thêm đối tượng
////                _testdao.Add(_testEmployees);
////                //Lần này khi lấy sẽ không trả về null nữa
////                _temp = _testdao.Get(new Employees(), 7);
////                }
////            //Xóa đối tượng
////            _testdao.Remove(_temp);
//        }
//        @Test
//        public void PIETest () {
//            var _tempStrings = PropertyInfoExtractor.GetObjectPropertyNames(Employees.class);
//            var _tempTypes = PropertyInfoExtractor.GetObjectPropertyTypes(Employees.class);
//            var _tempValue = PropertyInfoExtractor.InvokeGetter(Employees.class, _tempStrings[3], _testEmployees);
//            var i = 0;
//            for (String _name : _tempStrings) {
//                System.out.println(_name + " with class " + _tempTypes[i++]);
//            }
//            System.out.println(_tempValue);
//            assertEquals(_tempValue != null, true);
//        }
//        @Test
//        public void ComponentTableTest () {
//            var _long = new Employees(01, "Ngo Tien Long", "0912222003", "Staff", 12000000);
//            Employees.class.getTypeName();
//            var _empList = new ArrayList<>();
//            _empList.add(_testEmployees);
//            _empList.add(_long);
//            var _tempTable = new ComponentTable<>(Employees.class, _empList);
//            System.out.println(_tempTable.getValueAt(0, 4));
//
//            public void ConectionTest () {
//                assertEquals(_testdb.getConnectionState(), true);
//
//            }
//        }
    }
    @Test
    public void Dishes() throws Exception {
        var _dishes = new DishesController();
//        var _getAll = _dishes.sortBy("nhame",false);
//
//        for (Dish _dish: _getAll) {
//            System.out.println(_dish.getID()+"-"+_dish.getName()+"-"+_dish.getPrice());
//        }
        var _remain = _dishes.calculateRemaining("200");
        System.out.println(_remain);
    }
}
