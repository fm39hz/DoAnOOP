// package team4.KitchenManager.Controller;

// import team4.KitchenManager.Model.Dishes;
// import team4.KitchenManager.Model.IngredientQuantity;
// import team4.KitchenManager.Model.Ingredient;
// import team4.KitchenManager.DAO.DatabaseConnector;
// import team4.KitchenManager.DAO.MainDAO;
// import javax.swing.*;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class DishesController {

//     DatabaseConnector conn = null;
//     MainDAO dao = null;
//     public DishesController() {
//         conn = new DatabaseConnector(DatabaseConnector.Url.MariaDB,"root","");
//         dao = new MainDAO(conn);
//     }

//     public class ReturnData {
//         private int id;
//         private String name;
//         private int price;
//         private int estimated_remaining;
//         private int sold;
//         private int cost;
//         public ReturnData(int id, String name, int price, int estimated_remaining, int sold, int cost) {
//             this.id = id;
//             this.name = name;
//             this.price = price;
//             this.estimated_remaining = estimated_remaining;
//             this.sold = sold;
//             this.cost = cost;
//         }
//     }
//     public List<ReturnData> getAll() {
//         List<ReturnData> _list = new ArrayList<>();
//         Ingredient _ingredient = new Ingredient();
//         IngredientQuantity _quantity = new IngredientQuantity();
//         Dishes _dishes = new Dishes();
//         List<IngredientQuantity> _listQuantity = new ArrayList<>();
//         int _estimated_remaining = this.calculateRemaining(_dishes.getID());
//         int _sold = 0;
//         int _cost = 0;
//         _list.add(new ReturnData(_dishes.getID(),_dishes.getName(),_dishes.getPrice(),_estimated_remaining,_sold,_cost));
//         return _list;
//     }
//     public int addDishes(Dishes d) {
//         int _dishes_count = 0;
//         String sql = "INSERT INTO dishes (id,name,cost,quantity)" + "VALUES (?,?,?,?)";
//         try {
//             CallableStatement cs = conn.getConnector().prepareCall(sql);
//             cs.setInt(1, d.getID());
//             cs.setString(2, d.getName());
//             cs.setInt(3, d.getCost());
// //            cs.setInt(4,d.getQuantities()); //id quantity
//             _dishes_count = cs.executeUpdate();
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _dishes_count;
//     }

//     public boolean removeDishes(int id) {
//         boolean _ok = false;
//         String sql = "DELETE FROM dishes WHERE id=?";
//         try {
//             CallableStatement cs = conn.getConnector().prepareCall(sql);
//             cs.setInt(1, id);
//             int result = cs.executeUpdate();
//             if (result > 0) {
//                 _ok = true;
//             }
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _ok;
//     }

//     public int updateDishes(Dishes d) {
//         int _dishes_count = 0;
//         String sql = "UPDATE dishes SET id=?,name=?,cost=?,quantity=? ";
//         try {
//             CallableStatement cs = conn.getConnector().prepareCall(sql);
//             cs.setInt(1, d.getID());
//             cs.setString(2, d.getName());
//             cs.setInt(3, d.getCost());
// //            cs.setInt(4,d.getQuantities()); //id quantity
//             _dishes_count = cs.executeUpdate();
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _dishes_count;
//     }
//     public List<Dishes> findByName(String name) {
//         List<Dishes> _list = new ArrayList<>();
//         Dishes _dishes = new Dishes();
//         String sql = "SELECT * FROM dishes WHERE name=?";
//         try {
//             CallableStatement cs = conn.getConnector().prepareCall(sql);
//             ResultSet rs = cs.executeQuery();
//             while (rs.next()) {
//                 int _id = rs.getInt("id");
//                 String _name = rs.getString("name");
//                 int _cost = rs.getInt("cost");
// //                int _quantity = rs.getInt("quantity");
//                 List<IngredientQuantity> _quantity = new ArrayList<>();
//                 _dishes.setID(_id);
//                 _dishes.setName(_name);
//                 _dishes.setCost(_cost);
//                 _dishes.setQuantities(_quantity);
//                 _list.add(_dishes);
//             }
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _list;
//     }

//     public List<Dishes> sortBy(String by) {
//         List<Dishes> _list = new ArrayList<>();
//         Dishes _dishes = new Dishes();
//         /* TODO */
//         return _list;
//     }

//     public int calculateRemaining(int id) {
//         Dishes _dishes = new Dishes();
//         Ingredient _ingredient = new Ingredient();
//         IngredientQuantity _quantity = new IngredientQuantity();
//         int _remaining = _dishes.getQuantities().get(1).getQuantity() / _quantity.getQuantity();
//         for (IngredientQuantity quantities:_dishes.getQuantities()) {
//             int _calculate = _ingredient.getInStock() / quantities.getQuantity();
//             if (_remaining < _calculate) {
//                 _remaining = _calculate;
//             }
//         }
//         /* WIP */
//         return _remaining;
//     }
// }