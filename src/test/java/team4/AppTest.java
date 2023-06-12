package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import team4.KitchenManager.Controller.IngredientController;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import team4.KitchenManager.Controller.*;
import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;

public class AppTest {
    // Ingredient _testIngredient;
    DatabaseConnector _testdb;

    public AppTest() {
       _testdb = new DatabaseConnector(Url.MariaDB, "root", "");
    }

//     @Test
//     public void IngredientsTest() {
//         //khoi tao doi tuong
//         var _ingredients = new IngredientController();
//         // goi doi tuong
//         String dateString = "2023-06-09";
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//         try {
//             java.util.Date utilDate = dateFormat.parse(dateString);
//             long timeInMillis = utilDate.getTime();
//             Date sqlDate = new Date(timeInMillis);
//             System.out.println(sqlDate);
//             var _getAll = _ingredients.addIngredient(new Ingredient("null", "adsasd", sqlDate, 39393, 29292));
//         } catch (ParseException e) {
//             e.printStackTrace();
//         }
//         var Date = new Date(2023, 6, 10);
//         System.out.println(Date);
//     }
// }
    public AppTest(){
        _testdb = new DatabaseConnector(Url.MariaDB, "fm39hz", "D@ylahien2k3");
        }
    @Test
    public void ConectionTest(){
        assertEquals(_testdb.getConnectionState(), true);
        }
    @Test
    public void DishesTest(){
        var _dishes = new DishesController(_testdb);
        var _getAll = _dishes.getAll();
            for (var _dish: _getAll) {
                System.out.println(_dish.getID()+"|"+_dish.getName()+"|"+_dish.getPrice());
                }
        assertEquals(_getAll.get(0).getID(), "200");
        }
    @Test
    public void IngredientsTest(){
        var _ingredients = new IngredientController(_testdb);
        var _getAll = _ingredients.getIngredients();
            for (var _ingredient : _getAll){
                System.out.println(_ingredient.getID() + "|" + _ingredient.getName() + "|" + _ingredient.getInDate());
                }
        assertEquals(_getAll.size(), 11);
        }
    }
