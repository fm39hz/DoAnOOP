package team4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import team4.KitchenManager.DAO.DatabaseConnector;
import team4.KitchenManager.DAO.DatabaseConnector.Url;
import team4.KitchenManager.Controller.*;

public class ControllerTest{
    private DatabaseConnector _testdb;
    public ControllerTest(){
        _testdb = new DatabaseConnector(Url.MariaDB, "fm39hz", "D@ylahien2k3");
        }
    @Test
    public void ConnectionTest(){
        assertEquals(_testdb.getConnectionState(), true);
        }
    @Test
    public void DishesTest(){
        var _dishes = new DishesController(_testdb);
        var _getAll = _dishes.getAll();
            for (var _dish: _getAll) {
                System.out.println(_dish.getID()+"|"+_dish.getName()+"|"+_dish.getPrice());
                }
        assertEquals(_getAll.size(), 2);
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
    public void AttendancesTest(){
        var _attendances = new AttendanceController(_testdb);
        var _testDay = Date.valueOf(LocalDate.of(2023, 06, 01));
        assertEquals(_attendances.GetEmployeeOnDay(_testDay).size(), 2);
        }
    @Test
    public void EmployeesTest(){
        var _employees = new EmployeeController(_testdb);
        var _getAll = _employees.getAll();
            for (var _employee : _getAll){
                System.out.println(_employee.getId() + "|" + _employee.getFirstName() + " " + _employee.getLastName());
                }
        assertEquals(_getAll.size(), 12);
        }
    @Test
    public void InvoicesTest(){
        var _invoices = new InvoiceController(_testdb);
        var _getAll = _invoices.getAll();
            for (var _invoice : _getAll){
                System.out.println(_invoice.getID() + "|" + _invoice.getCustomer().getLastName() + ": " + _invoice.getCustomerFeedback());
                }
        assertEquals(_getAll.size(), 2);
        }
    @Test
    public void CustomersTest(){
        var _customers = new CustomerController(_testdb);
        var _getAll = _customers.getAll();
            for (var _customer : _getAll){
                System.out.println(_customer.getInformation());
                }
        assertEquals(_getAll.size(), 14);
        }
    }