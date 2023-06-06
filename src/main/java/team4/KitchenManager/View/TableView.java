package team4.KitchenManager.View;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import team4.KitchenManager.DAO.MainDAO;
import team4.KitchenManager.Model.*;
import team4.KitchenManager.Service.ComponentTable;

public class TableView extends JTable{
    private MainDAO DAO;
    private List<Object> ModelList;
    private ComponentTable<Object> Table;
    public class ModelOf{
        public static final char Employee = 'e';
        public static final char Customer = 'c';
        public static final char Dish = 'd';
        public static final char Ingredient = 'i';
        public static final char Quantity = 'q';
        public static final char Order = 'o';
        }
    public TableView(){
        this.DAO = new MainDAO();
        this.ModelList = new ArrayList<>();
        }
    public TableView(ComponentTable<Object> modelTable){
        super(modelTable);
        }
    public void FetchData(int id, char typeConfig){
        var _tempObject = new Object();
        var _value = new Object();
        switch (typeConfig){
            case 'e':
                _tempObject = new Employees();
                break;
            case 'c':
                _tempObject = new Customers();
                break;
            case 'd':
                _tempObject = new Dishes();
                break;
            case 'i':
                _tempObject = new Ingredient();
                break;
            case 'q':
                _tempObject = new IngredientQuantity();
                break;
            case 'o':
                _tempObject = new Order();
                break;
            }
            try {
                _value = DAO.Get(_tempObject, id);
                    if (_value != null){
                        this.ModelList.add(_value);
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    public ComponentTable<Object> GetTable(char typeConfig){
        Class<?> _classType = null;
        switch (typeConfig){
            case 'e':
                _classType = Employees.class;
                break;
            case 'c':
                _classType = Customers.class;
                break;
            case 'd':
                _classType = Dishes.class;
                break;
            case 'i':
                _classType = Ingredient.class;
                break;
            case 'q':
                _classType = IngredientQuantity.class;
                break;
            case 'o':
                _classType = Order.class;
                break;
            }
            this.Table = new ComponentTable<>(_classType, ModelList);
        return this.Table;
        }
    }
