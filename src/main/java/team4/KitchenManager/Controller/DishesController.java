package team4.KitchenManager.Controller;

import team4.KitchenManager.Model.Dish;
import team4.KitchenManager.Model.IngredientQuantity;
import team4.KitchenManager.Model.Ingredient;
import team4.KitchenManager.DAO.DatabaseConnector;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishesController {

    DatabaseConnector conn = null;
    public DishesController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
    }

    private String sql_join_query = "SELECT dishes.id,\n" +
            "dishes.name,\n" +
            "dishes.price,\n" +
            "quantities.ingredient_id,\n" +
            "quantities.quantity AS ingre_quantity,\n" +
            "ingredients.date_in AS ingre_date_in,\n" +
            "ingredients.in_stock AS ingre_in_stock,\n" +
            "ingredients.cost AS ingre_cost,\n" +
            "ingredients.name AS ingre_name\n" +
            "FROM dishes\n" +
            "INNER JOIN quantities ON dishes.id = quantities.dishes_id\n" +
            "INNER JOIN ingredients ON ingredients.id = quantities.ingredient_id\n";

//    public class ReturnData {
//        private String id;
//        private String name;
//        private int price;
//        private int estimated_remaining;
//        private int sold;
//        private int cost;
//        public ReturnData(String id, String name, int price, int estimated_remaining, int sold, int cost) {
//            this.id = id;
//            this.name = name;
//            this.price = price;
//            this.estimated_remaining = estimated_remaining;
//            this.sold = sold;
//            this.cost = cost;
//        }
//    }
    public List<Dish> getAll() {
        List<Dish> _list = new ArrayList<>();
        List<IngredientQuantity> _list_quantity = new ArrayList<>();
        String sql = this.sql_join_query + "ORDER BY `id` ASC";
        int _ingredient_quantity_id = 1;

        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int _previous_id = 0;
            while (rs.next()) {
                Ingredient _ingredient = new Ingredient();

                String _id = rs.getString("id");
                String _name = rs.getString("name");
                int _price = rs.getInt("price");
                String _ingredient_id = rs.getString("ingredient_id");
                String _ingre_name = rs.getString("ingre_name");
                Date _ingre_in_date = rs.getDate("ingre_date_in");
                int _ingre_in_stock = rs.getInt("ingre_in_stock");
                int _ingre_cost = rs.getInt("ingre_cost");
                int _ingre_quantity = rs.getInt("ingre_quantity");

                _ingredient.setID(_ingredient_id);
                _ingredient.setName(_ingre_name);
                _ingredient.setInDate(_ingre_in_date);
                _ingredient.setInStock(_ingre_in_stock);
                _ingredient.setCost(_ingre_cost);

                if (_previous_id == Integer.parseInt(_id)) {
                    _ingredient_quantity_id ++;
                    _list_quantity.add(new IngredientQuantity(String.valueOf(_ingredient_quantity_id),_ingredient,_ingre_quantity));
                    continue;
                } else {
                    _list_quantity = new ArrayList<>();
                    _ingredient_quantity_id = 1;
                    _list_quantity.add(new IngredientQuantity(String.valueOf(_ingredient_quantity_id),_ingredient,_ingre_quantity));
                }
                _list.add(new Dish(_id,_name,_price,"description",_list_quantity));
                _previous_id = Integer.parseInt(_id);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
    public boolean addDishes(Dish d) {
        boolean _ok = false;
        String sql = "INSERT INTO dishes (name, price) VALUES (?, ?);";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1,d.getName());
            ps.setInt(2,d.getPrice());
            int result = ps.executeUpdate();
            if (result > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }

    public boolean removeDishes(int id) {
        boolean _ok = false;
        String sql = "DELETE FROM dishes WHERE `id`=?";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }

    public int updateDishes(Dish d) {
        int _dishes_count = 0;
        String sql = "UPDATE dishes SET `name`=?,`price`=? WHERE `id`=?;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setInt(2,d.getPrice());
            ps.setString(3,d.getID());
            _dishes_count = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _dishes_count;
    }
    public List<Dish> findByName(String query) {
        List<Dish> _list = new ArrayList<>();
        String sql = this.sql_join_query + " WHERE `dishes.name` LIKE \'%?%\';";

        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1,query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient _ingredient = new Ingredient();
                List<IngredientQuantity> _list_quantity = new ArrayList<>();

                String _id = rs.getString("id");
                String _name = rs.getString("name");
                int _price = rs.getInt("price");
                String _ingredient_id = rs.getString("ingredient_id");
                int _ingre_quantity = rs.getInt("ingre_quantity");
                String _ingre_name = rs.getString("ingre_name");
                Date _ingre_in_date = rs.getDate("ingre_date_in");
                int _ingre_in_stock = rs.getInt("ingre_in_stock");
                int _ingre_cost = rs.getInt("ingre_cost");

                _ingredient.setID(_ingredient_id);
                _ingredient.setName(_ingre_name);
                _ingredient.setInDate(_ingre_in_date);
                _ingredient.setInStock(_ingre_in_stock);
                _ingredient.setCost(_ingre_cost);
                _list_quantity.add(new IngredientQuantity("1",_ingredient,_ingre_quantity));

                _list.add(new Dish(_id,_name,_price,"description",_list_quantity));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }

    public List<Dish> sortBy(String by, int sort) {
        List<Dish> _list = new ArrayList<>();
        String _sort = "";
        switch (sort) {
            case 1:
                _sort = "ASC";
                break;
            case 2:
                _sort = "DESC";
                break;
            default:
                return null;
        }
        String sql = this.sql_join_query + "ORDER BY ? ?";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1,"by");
            ps.setString(2, _sort);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient _ingredient = new Ingredient();
                List<IngredientQuantity> _list_quantity = new ArrayList<>();

                String _id = rs.getString("id");
                String _name = rs.getString("name");
                int _price = rs.getInt("price");
                String _ingredient_id = rs.getString("ingredient_id");
                int _ingre_quantity = rs.getInt("ingre_quantity");
                String _ingre_name = rs.getString("ingre_name");
                Date _ingre_in_date = rs.getDate("ingre_date_in");
                int _ingre_in_stock = rs.getInt("ingre_in_stock");
                int _ingre_cost = rs.getInt("ingre_cost");

                _ingredient.setID(_ingredient_id);
                _ingredient.setName(_ingre_name);
                _ingredient.setInDate(_ingre_in_date);
                _ingredient.setInStock(_ingre_in_stock);
                _ingredient.setCost(_ingre_cost);
                _list_quantity.add(new IngredientQuantity("1",_ingredient,_ingre_quantity));

                _list.add(new Dish(_id,_name,_price,"discription",_list_quantity));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }

    public int calculateRemaining(String id) {
        int _remaining = 0;
        String _sql = "SELECT dishes.id,\n" +
                "dishes.name,\n" +
                "quantities.ingredient_id, \n" +
                "quantities.quantity AS ingre_quantity, \n" +
                "ingredients.in_stock,\n" +
                "ingredients.Id AS ingre_id, \n" +
                "ingredients.name AS ingre_name\n" +
                "FROM dishes \n" +
                "INNER JOIN quantities ON dishes.id = quantities.dishes_id \n" +
                "INNER JOIN ingredients ON ingredients.id = quantities.ingredient_id\n" +
                "WHERE dishes.id = ?;";
        try {
            var ps = conn.getConnector().prepareStatement(_sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.isFirst()) {
                _remaining = rs.getInt("in_stock") / rs.getInt("ingre_quantity");
            }
            while (rs.next()) {
                int _compare = rs.getInt("in_stock") / rs.getInt("ingre_quantity");
                if (_compare < _remaining) {
                    _remaining = _compare;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _remaining;
    }
}
