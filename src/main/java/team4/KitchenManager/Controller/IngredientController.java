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
                String _id = rs.getString("id");
                Date _indate = rs.getDate("date_in");
                int _instock = rs.getInt("in_stock");
                int _cost=rs.getInt("cost");
                _list.add(new Ingredient(_id,"",_indate,123,123));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _list;
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
        String sql = "DELETE FROM ingredient WHERE `id`=?";
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

    public int updateIngredient(Ingredient d) {
        int _ingredient_count = 0;
        String sql = "UPDATE dishes SET `name`=?,`date`=?, `instock` =?, `cost` =?  WHERE `id`=?;";
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ps.setString(1, d.getName());
            ps.setDate(2, d.getInDate());
            ps.setString(3, d.getID());
            ps.setInt(4, d.getInStock());
            ps.setInt(5, d.getCost());
            _ingredient_count = ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return _ingredient_count;
    }
    public List<Ingredient> sortBy(String by, boolean desc) {
        // tao danh sach
        List<Ingredient> _list = new ArrayList<>();
        String sql = "this.sql_join_query"; //lenh sql
        try {
            var ps = conn.getConnector().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        //sap xep
        Comparator<Ingredient> _sortedList = new Comparator<>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                switch (by) {
                    case "price":
                        return Integer.valueOf(o1.getCost()).compareTo(Integer.valueOf(o2.getCost()));
                    case "name":
                        return o1.getInDate().compareTo(o2.getInDate());
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
}