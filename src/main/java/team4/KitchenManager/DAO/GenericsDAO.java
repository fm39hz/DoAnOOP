package team4.KitchenManager.DAO;

import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GenericsDAO <T> {
    DatabaseConnector Connector;
    List<T> ItemList = new ArrayList<>();
    public GenericsDAO(){
        Connector = new DatabaseConnector();
        }
    
    public void Add(T object) throws SQLException {
        Class<?> _class = object.getClass();
        Field[] _property = _class.getDeclaredFields();
        String _sqlString  = "INSERT INTO " + object.toString() + " (" + PostStringBuilder(_property);
        PreparedStatement _statement = Connector.getConnector().prepareStatement(_sqlString);
            System.out.println(_sqlString);
            for (int i = 0; i < _property.length; i++) {
                try {
                    Object _value = _class.getMethod(GetGetterMethodName(_property[i].getName())).invoke(object);
                    _statement = SetValue(_value.getClass(), _value, _statement, i + 1);
                    } catch (Exception e) {
                    System.out.print("Exception occur at " + _property[i].getName());
                    e.printStackTrace();
                    }
                }
        }

    private PreparedStatement SetValue(Class<? extends Object> propertyType, Object value, PreparedStatement statement, int index) throws SQLException {
        if (propertyType == String.class){
            statement.setString(index, (String)value);
            }
        else if (propertyType == Integer.class){
            statement.setInt(index, (int)value);
            }
        return statement;
        }

    private String PostStringBuilder(Field[] _property){
        String _sqlString = "";
            for (int i = 0 ; i < _property.length; i++){
                _sqlString += _property[i].getName();
                if (i < _property.length - 1){
                    _sqlString += ", ";
                    }
                else {
                    _sqlString += ") VALUES (";
                    }
                }
            for (int i = 0 ; i < _property.length; i++){
                _sqlString += "?";
                if (i < _property.length - 1){
                    _sqlString += ", ";
                    }
                else {
                    _sqlString += ");";
                    }
                }
        return _sqlString;
        }

    private String GetGetterMethodName(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
    // private String GetSetterMethodName(String fieldName) {
    //     return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    //     }
}