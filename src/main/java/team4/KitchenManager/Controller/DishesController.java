package team4.KitchenManager.Controller;

import org.jetbrains.annotations.NotNull;
import team4.KitchenManager.Object.Dishes;
import team4.KitchenManager.Object.Quantity;
import team4.KitchenManager.Object.Ingredient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DishesController {

    private Ingredient ingredient = new Ingredient();
    private Quantity quantity = new Quantity();

    Connection conn;
    public DishesController() {
        this.conn = DatabaseConnector.getConnection();
    }
    public List<Dishes> getAllDishes() {

        List<Dishes> _list = new ArrayList<>();
        List<Quantity> _listQuantity = new ArrayList<>();
        // chua co database
//        String sql = "select * from dishes;";
//        try {
//            CallableStatement cs = conn.prepareCall(sql);
//            ResultSet rs = cs.executeQuery();
//            while (rs.next()) {
//                int _id = rs.getInt("id");
//                String _Name = rs.getString("name");
//                int _Cost = rs.getInt("cost");
//                int _Price = rs.getInt("price");
//                int _quantity = rs.getInt("quantity");
//                _lst.add(new Dishes(1,"name",10000,_lstq));
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
        this.ingredient.setName("Ten nguyen lieu");
        this.ingredient.setCost(10000);
        this.ingredient.setInStock(100);
        this.ingredient.setID(1);

        HashMap<Ingredient, Integer> _hashmap = new HashMap<>();
        _hashmap.put(this.ingredient,99);
        this.quantity.setTable(_hashmap);
        this.quantity.setID(1);
        _listQuantity.add(this.quantity);

        _list.add(new Dishes(1,"name",10000,_listQuantity));
        return _list;
    }
    public int addDishes(Dishes d) {
        int _dishes_count = 0;
        String sql = "insert into dishes (id,name,cost,quantity)" + "values (?,?,?,?)";
        try {
            CallableStatement cs = conn.prepareCall(sql);
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
        String sql = "delete from dishes where id=?";
        try {
            CallableStatement cs = conn.prepareCall(sql);
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
        String sql = "update dishes set id=?,name=?,cost=?,quantity=?";
        try {
            CallableStatement cs = conn.prepareCall(sql);
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
        String sql = "select * from dishes where name=?";
        try {
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                int _id = rs.getInt("id");
                String _name = rs.getString("name");
                int _cost = rs.getInt("cost");
//                int _quantity = rs.getInt("quantity");
                List<Quantity> _quantity = new ArrayList<>();
                _list.add(new Dishes(_id,_name,_cost,_quantity));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
}
