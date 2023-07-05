package team4.KitchenManager.Controller;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class QuantityController {
    private DatabaseConnector Connector;

    public QuantityController() {
        // không cần truyền tham số, mặc định sẽ dùng mariadb
        Connector = new DatabaseConnector(); 
        }
    public QuantityController(DatabaseConnector connector){
        this.Connector = connector;
        }
    public Map<Ingredient, Integer> GetAll(Dish dish){
        var _quantities = new HashMap<Ingredient, Integer>();
        var _ingredientsController = new IngredientController();
        try {
            Statement _statement = Connector.getConnector().createStatement();
            ResultSet _result = _statement.executeQuery("SELECT * FROM quantities WHERE dish_id = " + dish.getID());
            while (_result.next()) {
                _quantities.put(_ingredientsController.getAll(_result.getString(1)), _result.getInt(2));
                }
            _result.close();
            _statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _quantities;
        }
    public int CalculateRemaining(Dish dish){
        var _remaining = 100000000;
        var _ingredientsController = new IngredientController();
        try {
            Statement _statement = Connector.getConnector().createStatement();
            ResultSet _result = _statement.executeQuery("SELECT * FROM quantities WHERE dish_id = " + dish.getID());
            while (_result.next()) {
                var _temp = Math.round(_ingredientsController.getAll(_result.getString(2)).getInStock() / _result.getInt(3));
                if (_temp < _remaining){
                    _remaining = _temp;
                    }
                }
            _result.close();
            _statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return _remaining;
        }
}
