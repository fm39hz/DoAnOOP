// package team4.KitchenManager.Controller;


// import team4.KitchenManager.Model.Dish;
// import team4.KitchenManager.Model.IngredientQuantity;
// import team4.KitchenManager.Model.Ingredient;
// import team4.KitchenManager.DAO.DatabaseConnector;
// // import team4.KitchenManager.DAO.MainDAO;
// import javax.swing.*;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class DishesController{
//     DatabaseConnector conn = null;
//     // MainDAO dao = null;
//     public DishesController() {
//         // không cần truyền tham số, mặc định sẽ dùng mariadb
//         conn = new DatabaseConnector();
//     }

//     public class ReturnData {
//         private String id;
//         private String name;
//         private int price;
//         private int estimated_remaining;
//         private int sold;
//         private int cost;
//         public ReturnData(String id, String name, int price, int estimated_remaining, int sold, int cost) {
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
//         Dish _dishes = new Dish();
//         List<IngredientQuantity> _listQuantity = new ArrayList<>();
//         String _query = "SELECT * FROM dishes INNER JOIN quantities ON dishes.quantity_id = quantities.id";
//         int _estimated_remaining = this.calculateRemaining(_dishes.getID());
//         int _sold = 0;
//         int _cost = 0;
//         _list.add(new ReturnData(_dishes.getID(),_dishes.getName(),_dishes.getPrice(),_estimated_remaining,_sold,_cost));
//         return _list;
//     }
//     public int addDishes(Dish d) {
//         int _dishes_count = 0;
//         String sql = "INSERT INTO dishes (name, cost, price) VALUES (?, ?, ?);";
//         try {
//             var ps = conn.getConnector().prepareStatement(sql);
//             ps.setString(1,d.getName());
//             ps.setInt(2,d.getPrice());
//             ps.setInt(3, d.getPrice());
//             _dishes_count = ps.executeUpdate();
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _dishes_count;
//     }

//     public boolean removeDishes(int id) {
//         boolean _ok = false;
//         String sql = "DELETE FROM dishes WHERE `id`=?";
//         try {
//             var ps = conn.getConnector().prepareStatement(sql);
//             ps.setInt(1, id);
//             int result = ps.executeUpdate();
//             if (result > 0) {
//                 _ok = true;
//             }
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _ok;
//     }

//     public int updateDishes(Dish d) {
//         int _dishes_count = 0;
//         String sql = "UPDATE dishes SET (name, cost, price, id) `name`=?,`cost`=?,`price`=? WHERE `id`=?;";
//         try {
//             var ps = conn.getConnector().prepareStatement(sql);
//             ps.setString(1, d.getName());
//             ps.setInt(2,d.getPrice());
//             ps.setInt(3,d.getPrice());
//             ps.setString(4,d.getID());
// //            cs.setInt(4,d.getQuantities()); //id quantity
//             _dishes_count = ps.executeUpdate();
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _dishes_count;
//     }
//     public List<Dish> findByName(String name) {
//         List<Dish> _list = new ArrayList<>();
//         Dish _dishes = new Dish();
//         List<IngredientQuantity> _list_quantity = new ArrayList<>();
//         Ingredient _ingredient = new Ingredient();
//         IngredientQuantity _ingredient_quantity = new IngredientQuantity();
//         String sql = "SELECT dishes.id,\n" +
//                 "dishes.name,\n" +
//                 "dishes.cost, \n" +
//                 "dishes.price, \n" +
//                 "quantities.ingredient_id, \n" +
//                 "quantities.quantity AS ingre_quantity, \n" +
//                 "ingredients.Id AS ingre_id, \n" +
//                 "ingredients.name AS ingre_name, \n" +
//                 "ingredients.date_in,\n" +
//                 "ingredients.cost AS ingre_cost\n" +
//                 "FROM dishes \n" +
//                 "INNER JOIN quantities ON dishes.id = quantities.dishes_id \n" +
//                 "INNER JOIN ingredients ON ingredients.id = quantities.ingredient_id \n" +
//                 "WHERE dishes.name LIKE '%'+?+'%';";

//         try {
//             var ps = conn.getConnector().prepareCall(sql);
//             ResultSet rs = ps.executeQuery();
//             while (rs.next()) {
//                 String _id = rs.getString("id");
//                 String _name = rs.getString("name");
//                 int _cost = rs.getInt("cost");
//                 int _price = rs.getInt("price");
//                 int _quantity = rs.getInt("ingre_quantity");
//                 String _ingredient_id = rs.getString("ingre_id");
//                 String _ingredient_name = rs.getString("ingre_name");
//                 Date _date_in = rs.getDate("date_in");
//                 int _ingredient_cost = rs.getInt("ingre_cost");


//                 _dishes.setID(_id);
//                 _dishes.setName(_name);
//                 _dishes.setPrice(_cost);
//                 _dishes.setPrice(_price);

//                 _ingredient.setID(_ingredient_id);
//                 _ingredient.setName(_ingredient_name);
//                 _ingredient.setInDate(_date_in);
//                 _ingredient.setCost(_ingredient_cost);
//                 _list_quantity.add(new IngredientQuantity("1",_ingredient,_quantity));
//                 _dishes.setRecipe(_list_quantity);
//                 _list.add(_dishes);
//             }
//         } catch (SQLException ex) {
//             JOptionPane.showMessageDialog(null, ex);
//         }
//         return _list;
//     }

//     public List<Dish> sortBy(String by) {
//         List<Dish> _list = new ArrayList<>();
//         Dish _dishes = new Dish();
//         String _query = "SELECT * FROM dishes INNER JOIN quantities ON dishes.quantity_id = quantities.id";
//         /* TODO */
//         return _list;
//     }

//     public int calculateRemaining(String id) {
//         Dish _dishes = new Dish();
//         Ingredient _ingredient = new Ingredient();
//         IngredientQuantity _quantity = new IngredientQuantity();
//         int _remaining = 0;
// //        int _remaining = _dishes.getQuantities().get(1).getQuantity() / _quantity.getQuantity();
// //        for (IngredientQuantity quantities:_dishes.getQuantities()) {
// //            int _calculate = _ingredient.getInStock() / quantities.getQuantity();
// //            if (_remaining < _calculate) {
// //                _remaining = _calculate;
// //            }
// //        }
//         /* WIP */
//         return _remaining;
//     }
// }

