package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import team4.KitchenManager.Controller.DishesController;
import team4.KitchenManager.Controller.IngredientController;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.Dish;
// import team4.KitchenManager.Model.*;

public class AppTest {
    // Ingredient _testIngredient;
    DatabaseConnector _testdb;
    public AppTest(){
        _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
        }
    @Test
    public void ConectionTest(){
        assertEquals(_testdb.getConnectionState(), true);
        }
    @Test
    public void DishesTest() throws Exception {
        var _dishes = new DishesController();
        var _getAll = _dishes.sortBy("name",false);
            for (Dish _dish: _getAll) {
                System.out.println(_dish.getID()+"-"+_dish.getName()+"-"+_dish.getPrice());
                }
        var _remain = _dishes.calculateRemaining("200");
            assertEquals(_remain, 30000);
            System.out.println(_remain);
        }
    @Test
    public void IngredientsTest(){
        var _ingredients = new IngredientController();
        var _getAll = _ingredients.getIngredients();
            for (var _ingredient : _getAll){
                System.out.println(_ingredient.getID() + _ingredient.getName());
                }
        }
}