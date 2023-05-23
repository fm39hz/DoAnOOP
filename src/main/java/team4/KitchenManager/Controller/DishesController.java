package team4.KitchenManager.Controller;

import team4.KitchenManager.Model.Dishes;
import team4.KitchenManager.Model.Quantity;
import team4.KitchenManager.Model.Ingredient;
import team4.KitchenManager.DAO.DatabaseConnector;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DishesController {
    DatabaseConnector conn = null;
    public DishesController() {
        conn = new DatabaseConnector();
    }
    public List<Dishes> getAllDishes() {

        List<Dishes> _list = new ArrayList<>();
        List<Quantity> _listQuantity = new ArrayList<>();
        Ingredient _ingredient = new Ingredient();
        Quantity _quantity = new Quantity();
        Dishes _dishes = new Dishes();
//         chua co database
//        String sql = "SELECT * FROM dishes LEFT JOIN ON dishes.quantity = quantity.id";
//        try {
//            CallableStatement cs = conn.getConnector().prepareCall(sql);
//            ResultSet rs = cs.executeQuery();
//            while (rs.next()) {
//                int _id = rs.getInt("id");
//                String _name = rs.getString("name");
//                int _cost = rs.getInt("cost");
//                int _price = rs.getInt("price");
//                int _quantity = rs.getInt("quantity");
//
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
        _ingredient.setName("Ten nguyen lieu");
        _ingredient.setCost(10000);
        _ingredient.setInStock(100);
        _ingredient.setID(1);

        HashMap<Ingredient, Integer> _hashmap = new HashMap<>();
        _hashmap.put(_ingredient,99);
        _quantity.setTable(_hashmap);
        _quantity.setID(1);
        _listQuantity.add(_quantity);

        _dishes.setID(1);
        _dishes.setName("Ten dishes");
        _dishes.setPrice(10000);
        _dishes.setCost(100000);
        _dishes.setQuantities(_listQuantity);
        _list.add(_dishes);
        return _list;
    }
    public int addDishes(Dishes d) {
        int _dishes_count = 0;
        String sql = "INSERT INTO dishes (id,name,cost,quantity)" + "VALUES (?,?,?,?)";
        try {
            CallableStatement cs = conn.getConnector().prepareCall(sql);
            cs.setInt(1, d.getID());
            cs.setString(2, d.getName());
            cs.setInt(3, d.getCost());
//            cs.setInt(4,d.getQuantities()); //id quantity
            _dishes_count = cs.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _dishes_count;
    }

    public boolean removeDishes(int id) {
        boolean _ok = false;
        String sql = "DELETE FROM dishes WHERE id=?";
        try {
            CallableStatement cs = conn.getConnector().prepareCall(sql);
            cs.setInt(1, id);
            int result = cs.executeUpdate();
            if (result > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }

    public int updateDishes(Dishes d) {
        int _dishes_count = 0;
        String sql = "UPDATE dishes SET id=?,name=?,cost=?,quantity=? ";
        try {
            CallableStatement cs = conn.getConnector().prepareCall(sql);
            cs.setInt(1, d.getID());
            cs.setString(2, d.getName());
            cs.setInt(3, d.getCost());
//            cs.setInt(4,d.getQuantities()); //id quantity
            _dishes_count = cs.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _dishes_count;
    }
    public List<Dishes> findDishes(String name) {
        List<Dishes> _list = new ArrayList<>();
        Dishes _dishes = new Dishes();
        String sql = "SELECT * FROM dishes WHERE name=?";
        try {
            CallableStatement cs = conn.getConnector().prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int _id = rs.getInt("id");
                String _name = rs.getString("name");
                int _cost = rs.getInt("cost");
//                int _quantity = rs.getInt("quantity");
                List<Quantity> _quantity = new ArrayList<>();
                _dishes.setID(_id);
                _dishes.setName(_name);
                _dishes.setCost(_cost);
                _dishes.setQuantities(_quantity);
                _list.add(_dishes);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
}