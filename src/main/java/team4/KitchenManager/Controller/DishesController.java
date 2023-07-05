package team4.KitchenManager.Controller;

import team4.KitchenManager.Model.Dish;
import team4.KitchenManager.Model.IngredientQuantity;
import team4.KitchenManager.DAO.DatabaseConnector;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DishesController {

    DatabaseConnector conn = null;
    public DishesController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
    }
    public DishesController(DatabaseConnector con){
        this.conn = con;
    }
    public List<Dish> getAll() {
        List<Dish> _list = new ArrayList<>();
        String sql = "SELECT * FROM `dishes` ORDER BY `id` ASC";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var _dish = new Dish();

                List<IngredientQuantity> _recipe = new ArrayList<>();

                _dish.setID(rs.getString("id"));
                _dish.setName(rs.getString("name"));
                _dish.setPrice(rs.getInt("price"));
                _dish.setDescription(rs.getString("description"));
                _dish.setImagePath(rs.getString("image_path"));

                String sql2 = "SELECT * FROM quantities WHERE `dish_id` = "+rs.getString("id");
                var rs2 = conn.getConnector().prepareStatement(sql2).executeQuery();

                while (rs2.next()) {
                    var _ingredientQuantity = new IngredientQuantity();
                    var _get = new IngredientController().getAll(rs2.getString("ingredient_id"));
                    _ingredientQuantity.setID(String.valueOf(rs2.getRow()));
                    _ingredientQuantity.setIngredient(_get);
                    _ingredientQuantity.setQuantity(rs2.getInt("quantity"));
                    _recipe.add(_ingredientQuantity);
                }

                _dish.setRecipe(_recipe);
                _list.add(_dish);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
    public Dish getAll(String id) {
        var _dishes = getAll();
        for (Dish _dish: _dishes) {
            if (_dish.getID().equals(id)) {
                return _dish;
            }
        }
        return null;
    }
    public boolean addDishes(Dish d) {
        boolean _ok = false;
        String sql = "INSERT INTO dishes (name, price, image_path, description) VALUES (?, ?, ?, ?);";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1,d.getName());
            ps.setInt(2,d.getPrice());
            ps.setString(3,d.getImagePath());
            ps.setString(4, d.getDescription());
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

    public boolean updateDishes(Dish d) {
        boolean _ok = false;
        String sql = "UPDATE dishes SET `name`=?,`price`=?, `image_path`=?, `description`=? WHERE `id`=?;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setInt(2,d.getPrice());
            ps.setString(3,d.getImagePath());
            ps.setString(4, d.getDescription());
            ps.setString(5,d.getID());
            int _dishes_count = ps.executeUpdate();
            if (_dishes_count > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }
    public List<Dish> findByName(String query) {
        List<Dish> _list = new ArrayList<>();
        String sql = "SELECT * FROM `dishes` WHERE dishes.name LIKE '%"+query+"%' ORDER BY id ASC";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var _dish = new Dish();

                List<IngredientQuantity> _recipe = new ArrayList<>();

                _dish.setID(rs.getString("id"));
                _dish.setName(rs.getString("name"));
                _dish.setPrice(rs.getInt("price"));
                _dish.setDescription(rs.getString("description"));
                _dish.setImagePath(rs.getString("image_path"));

                String sql2 = "SELECT * FROM quantities WHERE `dish_id` = "+rs.getString("id");
                var rs2 = conn.getConnector().prepareStatement(sql2).executeQuery();

                while (rs2.next()) {
                    var _ingredientQuantity = new IngredientQuantity();
                    var _get = new IngredientController().getAll(rs2.getString("ingredient_id"));
                    _ingredientQuantity.setID(String.valueOf(rs2.getRow()));
                    _ingredientQuantity.setIngredient(_get);
                    _ingredientQuantity.setQuantity(rs2.getInt("quantity"));
                    _recipe.add(_ingredientQuantity);
                }

                _dish.setRecipe(_recipe);
                _list.add(_dish);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }

    public List<Dish> sortBy(String by, boolean desc) {
        List<Dish> _list = new ArrayList<>();
        String sql = "SELECT * FROM `dishes`;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var _dish = new Dish();

                List<IngredientQuantity> _recipe = new ArrayList<>();

                _dish.setID(rs.getString("id"));
                _dish.setName(rs.getString("name"));
                _dish.setPrice(rs.getInt("price"));
                _dish.setDescription(rs.getString("description"));
                _dish.setImagePath(rs.getString("image_path"));

                String sql2 = "SELECT * FROM quantities WHERE `dish_id` = "+rs.getString("id");
                var rs2 = conn.getConnector().prepareStatement(sql2).executeQuery();

                while (rs2.next()) {
                    var _ingredientQuantity = new IngredientQuantity();
                    var _get = new IngredientController().getAll(rs2.getString("ingredient_id"));
                    _ingredientQuantity.setID(String.valueOf(rs2.getRow()));
                    _ingredientQuantity.setIngredient(_get);
                    _ingredientQuantity.setQuantity(rs2.getInt("quantity"));
                    _recipe.add(_ingredientQuantity);
                }

                _dish.setRecipe(_recipe);
                _list.add(_dish);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        Comparator<Dish> _sortedList = new Comparator<>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                switch (by) {
                    case "price":
                        return Integer.valueOf(o1.getPrice()).compareTo(Integer.valueOf(o2.getPrice()));
                    case "name":
                        return o1.getName().compareTo(o2.getName());
                    default:
                        throw new RuntimeException("only support sort by name or price.");
                }
            }
        };
        Collections.sort(_list, _sortedList);
        if (desc) {
            Collections.reverse(_list);
        }
        return _list;
    }

    public int calculateRemaining(String id) throws Exception {
        int _remaining = 0;
        String _sql = "SELECT dishes.id,\n" +
                "dishes.name,\n" +
                "quantities.ingredient_id, \n" +
                "quantities.quantity AS ingre_quantity, \n" +
                "ingredients.in_stock,\n" +
                "ingredients.Id AS ingre_id, \n" +
                "ingredients.name AS ingre_name\n" +
                "FROM dishes \n" +
                "INNER JOIN quantities ON dishes.id = quantities.dish_id \n" +
                "INNER JOIN ingredients ON ingredients.id = quantities.ingredient_id\n" +
                "WHERE dishes.id = ?;";
        try {
            var ps = conn.getConnector().prepareStatement(_sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            rs.last();    // moves cursor to the last row
            int size = rs.getRow(); // get row id
            if (size == 0) {
                throw new Exception("dish not found");
            }

            if (rs.first()) {
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
