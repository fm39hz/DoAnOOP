package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Model.Employee;
import team4.KitchenManager.Controller.*;

public class ControllerTest{
    private DatabaseConnector _testdb;
    public ControllerTest(){
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
        var _getAll = _ingredients.getAll();
            for (var _ingredient : _getAll){
                System.out.println(_ingredient.getID() + "|" + _ingredient.getName() + "|" + _ingredient.getInDate());
                }
        assertEquals(_getAll.size(), 11);
        }
    @Test
    public void InvoiceTest(){
        var _attendances = new AttendanceController(_testdb);
        assertEquals(_attendances.CountAttendances(), 13);
        }
    }