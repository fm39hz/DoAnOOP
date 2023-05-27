package team4.KitchenManager.DAO;

import java.lang.reflect.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import team4.KitchenManager.Model.HasID;

public class MainDAO{
    private DatabaseConnector Connector;
    public MainDAO(){
        this.Connector = new DatabaseConnector();
        }
    public MainDAO(String url, String userName, String password){
        this.Connector = new DatabaseConnector(url, userName, password);
        }
    public DatabaseConnector getConnector(){
        return this.Connector;
        }
    public Boolean getConnectionState(){
        return this.Connector.getConnectionState();
        }
    public void Add(Object object) throws SQLException {
        var _class = object.getClass();
        var _property = _class.getDeclaredFields();
        var _query  = "INSERT INTO " + object.toString() + " (" + PostStringBuilder(_property);
        var _statement = Connector.getConnector().prepareStatement(_query);
            for (int i = 0; i < _property.length; i++) {
                try {
                    var _value = _class.getMethod(GetGetterMethod(_property[i].getName())).invoke(object);
                    _statement = SetValue(_value, _statement, i + 1);
                    } catch (Exception e) {
                        System.out.print("Exception occur at " + _property[i].getName());
                        e.printStackTrace();
                        }
                }
            _statement.executeQuery();
        }
    public void Remove(Object object) throws SQLException {
        try{
            var _query  = "DELETE FROM " + object.toString() + " WHERE id =?";
            var _statement = Connector.getConnector().prepareStatement(_query);
                if (object instanceof HasID){
                    var _hasIDInstance = (HasID)object;
                        _statement.setInt(1, _hasIDInstance.GetInstanceID());
                    }
                _statement.executeQuery();
            }
        catch (Exception e){
            e.printStackTrace();
            }
        }
    public void Remove(String tableName, int id) throws SQLException{
        try{
            var _query = "DELETE FROM " + tableName + "WHERE id = ?";
            var _statement = Connector.getConnector().prepareStatement(_query);
                _statement.setInt(1, id);
                _statement.executeQuery();
            }
        catch (Exception exception){
            exception.printStackTrace();
            }
        }
    public <T> T Get(T tempObject, int id) throws SQLException{
        var _class = tempObject.getClass();
        var _property = _class.getDeclaredFields();
        var _query  = "SELECT * FROM " + tempObject.toString() + " WHERE id = ?";
        var _statement = Connector.getConnector().prepareStatement(_query);
            _statement.setInt(1, id);
        var _resultSet = _statement.executeQuery();
            while(_resultSet.next()){
                for (int i = 0; i < _property.length; i++){
                    try{
                        var _result = GetValue(_property[i].getType(), _resultSet, i + 1);
                        _class.getMethod(GetSetterMethod(_property[i].getName()), _property[i].getType()).invoke(tempObject, _result);
                        } catch (Exception exception){
                            exception.printStackTrace();
                            return null;
                            }
                    }
                System.out.println("Got 1 item satisfied at: " + tempObject);
                }
        return tempObject;
        }
    //Các hàm static để sử dụng nội bộ trong class
    private static PreparedStatement SetValue(Object value, PreparedStatement statement, int index) throws SQLException {
        var _propertyType = value.getClass();
            if (_propertyType == String.class){
                statement.setString(index, (String)value);
                }
            else if (_propertyType == Integer.class){
                statement.setInt(index, (int)value);
                }
        return statement;
        }
    private static <T> T GetValue(Class<T> propertyType, ResultSet resultSet, int index) throws SQLException {
        if (propertyType == String.class){
            return (T)resultSet.getString(index);
            }
        else if (propertyType == int.class){
            Integer _value = resultSet.getInt(index);
            return (T)_value;
            }
        return null;
        }
    private static String PostStringBuilder(Field[] property){
        var _sqlString = "";
            for (int i = 0 ; i < property.length; i++){
                _sqlString += property[i].getName();
                if (i < property.length - 1){
                    _sqlString += ", ";
                    }
                else {
                    _sqlString += ") VALUES (";
                    }
                }
            for (int i = 0 ; i < property.length; i++){
                _sqlString += "?";
                if (i < property.length - 1){
                    _sqlString += ", ";
                    }
                else {
                    _sqlString += ");";
                    }
                }
        return _sqlString;
        }
    private static String GetGetterMethod(String fieldName) {
        return "get" + MatchNamingRules(fieldName);
        }
    private static String GetSetterMethod(String fieldName) {
        return "set" + MatchNamingRules(fieldName);
        }
    private static String MatchNamingRules(String fieldName){
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
    }