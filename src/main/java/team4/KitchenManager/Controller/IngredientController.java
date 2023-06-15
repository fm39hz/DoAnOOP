package team4.KitchenManager.Controller;
import team4.KitchenManager.Model.Ingredient;
import team4.KitchenManager.DAO.DatabaseConnector;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IngredientController {

    DatabaseConnector conn = null;

    public IngredientController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        conn = new DatabaseConnector();
    }
    public IngredientController(DatabaseConnector con){
        this.conn = con;
        }
    public List<Ingredient> getAll(){
        List<Ingredient> _list = new ArrayList<>();
        String sql = "SELECT * FROM ingredients;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String _id = rs.getString("Id");
                String _name = rs.getString("name");
                Date _indate = rs.getDate("date_in");
                int _instock = rs.getInt("in_stock");
                int _cost=rs.getInt("cost");
                _list.add(new Ingredient(_id,_name,_indate,_instock,_cost));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
    public Ingredient getAll(String id) {
        String sql = "SELECT * FROM ingredients WHERE `Id` = ?";
        var _ingredient = new Ingredient();
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                _ingredient.setID(rs.getString("Id"));
                _ingredient.setName(rs.getString("name"));
                _ingredient.setInDate(rs.getDate("date_in"));
                _ingredient.setInStock(rs.getInt("in_stock"));
                _ingredient.setCost(rs.getInt("cost"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ingredient;
    }
    public boolean addIngredient(Ingredient d) {
        boolean _ok = false;
        String sql = "INSERT INTO ingredients (name, date_in, in_stock, cost) VALUES (?, ?, ?, ?);";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setDate(2, d.getInDate());
            ps.setInt(3, d.getInStock());
            ps.setInt(4, d.getCost());
            int result = ps.executeUpdate();
            if (result > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }

    public boolean removeIngredient(int id) {
        boolean _ok = false;
        String sql = "DELETE FROM ingredients WHERE `id`=?";
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

    public boolean updateIngredient(Ingredient d) {
        boolean _ok = false;
        String sql = "UPDATE `ingredients` SET `name`=?,`date_in`=?, `in_stock` =?, `cost` =?  WHERE `Id`=?;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setDate(2, d.getInDate());
            ps.setInt(3, d.getInStock());
            ps.setInt(4, d.getCost());
            ps.setInt(5,Integer.valueOf(d.getID()));
            int _count = ps.executeUpdate();
            if (_count > 0) {
                _ok = true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ok;
    }
    public List<Ingredient> sortBy(String by, boolean desc) {
        // tao danh sach
        List<Ingredient> _list = new ArrayList<>();
        String sql = "SELECT * FROM ingredients"; //lenh sql
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var _id = rs.getString("Id");
                var _name = rs.getString("name");
                var _date_in = rs.getDate("date_in");
                var _in_stock = rs.getInt("in_stock");
                var _cost = rs.getInt("cost");
                _list.add(new Ingredient(_id,_name,_date_in,_in_stock,_cost));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        //sap xep
        Comparator<Ingredient> _sortedList = new Comparator<>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                switch (by) {
                    case "cost":
                        return Integer.valueOf(o1.getCost()).compareTo(Integer.valueOf(o2.getCost()));
                    case "date_in":
                        return o1.getInDate().compareTo(o2.getInDate());
                    case "usage":
                        return 0; //TODO
                    default:
                        throw new RuntimeException("what do you want to sort?");
                }
            }
        };
        Collections.sort(_list, _sortedList);
        if (desc) {
            Collections.reverse(_list);
        }
        return _list;
    }

    public List<Ingredient> findByName(String name) {
        List<Ingredient> _list = new ArrayList<>();
        String _sql = "SELECT * FROM ingredients WHERE `name` LIKE '%"+name+"%'";
        try {
            var ps = conn.getConnector().prepareStatement(_sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                var _id = rs.getString("Id");
                var _name = rs.getString("name");
                var _date_in = rs.getDate("date_in");
                var _in_stock = rs.getInt("in_stock");
                var _cost = rs.getInt("cost");
                _list.add(new Ingredient(_id,_name,_date_in,_in_stock,_cost));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
    }
}