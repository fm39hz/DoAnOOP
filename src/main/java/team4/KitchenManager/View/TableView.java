package team4.KitchenManager.View;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import team4.KitchenManager.DAO.MainDAO;
import team4.KitchenManager.Model.*;
import team4.KitchenManager.Service.ComponentTable;

public class TableView extends JTable{
    private MainDAO DAO;
    private List<Object> ModelList;
    public class ModelOf{
        public final char Employee = 'e';
        public final char Customer = 'c';
        public final char Dish = 'd';
        public final char Ingredient = 'i';
        public final char Quantity = 'q';
        public final char Order = 'o';
        }
    public TableView(){
        this.DAO = new MainDAO();
        this.ModelList = new ArrayList<>();
        }
    public ComponentTable<?> Add(int id, char typeConfig) throws SQLException{
        var _tempObject = new Object();
        Class<?> _classType = null;
        switch (typeConfig){
            case 'e':
                _tempObject = new Employees();
                _classType = Employees.class;
            case 'c':
                _tempObject = new Customers();
                _classType = Customers.class;
            case 'd':
                _tempObject = new Dishes();
                _classType = Dishes.class;
            case 'i':
                _tempObject = new Ingredient();
                _classType = Ingredient.class;
            case 'q':
                _tempObject = new IngredientQuantity();
                _classType = IngredientQuantity.class;
            case 'o':
                _tempObject = new Order();
                _classType = Order.class;
            }
            this.ModelList.add(DAO.Get(_tempObject, id));
        return new ComponentTable<Object>(_classType, ModelList);
        }
    }
